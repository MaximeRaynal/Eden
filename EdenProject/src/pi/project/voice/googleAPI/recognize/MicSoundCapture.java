/*
 * LibVoice
 * pi.project.voice.googleAPI.recognize                      14 janv. 2013
 * MicSoundCapture.java
 */
package pi.project.voice.googleAPI.recognize;

import java.io.File;
import java.io.IOException;
import java.io.PipedWriter;
import java.util.HashMap;
import java.util.Map;
import javaFlacEncoder.FLAC_FileEncoder;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import pi.project.log.Logger;
import pi.project.webAccess.WebPageAccess;

import com.google.gson.Gson;

/**
 * Système d'enregistrement, lors de l'initialisation analyse le niveau courant,
 *  ne déclenche l'enregistrement que si le niveau sonore ne dépasse le volume
 * initial.
 * @author Maxime Raynal
 *
 */
public class MicSoundCapture {
	
	/**
	 * La ligne du microphone standard
	 */
	private TargetDataLine micLine;
	
	/**
	 * Définition fixe du format standard d'enregistrement
	 */
	private static AudioFormat standardFormat = 
									new AudioFormat(16000, 16, 1, true, false);
	
	/**
	 * Un pourcentage (sous la forme 1.xx) à partir duquel on déclenche 
	 *  l'enregistrement
	 */
	public static final float LEVEL_VARIATION = 1.20f;
	
	/**
	 * Initialise l'écoute
	 */
	public MicSoundCapture() {
		DataLine.Info micLineInfo = new DataLine.Info(TargetDataLine.class,
																standardFormat);
		try {
			micLine = (TargetDataLine) AudioSystem.getLine(micLineInfo);
		} catch (LineUnavailableException e) {
			Logger.e("Impossible d'accéder au micro  : " + e.getMessage());
		}
	}
	
	/**
	 * Démarre l'écoute 
	 * @param communicationBuffer Le pipe par lequel on transmettra les données
	 */
	public void startListening(PipedWriter communicationBuffer) {
		try {
			micLine.open(standardFormat);
		} catch (LineUnavailableException e) {
			Logger.e("Impossible d'ouvrir au micro  : " + e.getMessage());
		}
		
		micLine.start();
		
		new Thread(new Capture(communicationBuffer)).start();

	} 
	
	/**
	 * Classe servant à capturer le son arrivant sur le micro. Le son ne doit 
	 * être capturé que si il atteint un certain seuil, il est ensuite 
	 *  interprété, si il correspond à une commande il sera placé sur un buffer 
	 *  de communication inter processus.
	 * @author Maxime Raynal
	 *
	 */
	class Capture implements Runnable {
		
		/**
		 * La durée de capture en milliseconde
		 */
		public static final int CAPTURE_DURATION = 3000; 

		private PipedWriter communicationBufffer;
		
		/**
		 * On initialise l'objet en lui passant le PipedBuffer
		 * @param buffer Le buffer communiquant entre les processus
		 */
		public Capture(PipedWriter buffer) {
			communicationBufffer = buffer;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			Logger.i("Lancement du Thread de capture");
			
			//Le niveau de référence pour déclencher ou non l'analyse
			int referenceLevel = calculateRMSLevel();
			
			//Boucle perpétuelle sauf lancement d'arrêt d'urgence
			while (micLine.isOpen()) {
				int currentLevel = calculateRMSLevel();
				if (currentLevel > referenceLevel * LEVEL_VARIATION) {
					
					new SoundWriter().start();
					synchronized (this) {
						try {
							System.out.println("Enregistrement de la commande");
							wait(CAPTURE_DURATION);
							System.out.println("Fin de l'enregistrement");
						} catch (InterruptedException e) {
							Logger.e("Un problème est survenue dans " +
									"le Thread de capture : " + e.getMessage());
						}
					}
					
					//Si au bout de 5 sec le niveau est toujours nettement au 
					//dessus c'est qu'il y a une source sonore parasite (ex: TV)
					/*if (currentLevel > referenceLevel * LEVEL_VARIATION) {
						System.err.println("Recalibrage du micro");
						referenceLevel = currentLevel;
					} else {*/
						
					System.out.println("Conversion");
					new FLAC_FileEncoder().encode(
							new File(SoundWriter.TMP_PATH + 
											SoundWriter.TMP_FILE_NAME + ".wav"),
										new File(SoundWriter.TMP_PATH + 
										  SoundWriter.TMP_FILE_NAME + ".flac"));
				
					new File(SoundWriter.TMP_PATH + SoundWriter.TMP_FILE_NAME +
															   ".wav").delete();
					
						Map<String, String> urlParam = new HashMap<>();
						
						urlParam.put("xjerr", "1");
						urlParam.put("client", "chromium");
						urlParam.put("lang", "fr");
						
						Map<String, String> reqParam = new HashMap<>();
						
						reqParam.put("Content-Type", 
													"audio/x-flac; rate=16000");
						String s = null;
						try {
							s = WebPageAccess.uploadSimpleFile(
								"http://www.google.com/speech-api/v1/recognize",
									SoundWriter.TMP_PATH + 
											SoundWriter.TMP_FILE_NAME + ".flac",
									urlParam, reqParam);
						} catch (IOException e) {
							Logger.w("Impossible d'uploder le fichier pour la " 
									+ "reconnaissance vocale : " +
																e.getMessage());
						}
						
						SpeechRecognitionAnswer answer = new Gson().fromJson(s, 
								SpeechRecognitionAnswer.class);
						
						Logger.i("Commande reçu : " + 
												   answer.getBetterHypothese());
						
						//Si c'est une commande (débute par Eden)
						//if (answer.getBetterHypothese().matches("^eden")) {
							try {
								communicationBufffer.write(answer.
													      getBetterHypothese()+"\n");
							} catch (IOException e) {
								Logger.w("Impossible de mettre la commande" +
										" dans le pipe : " + e.getMessage());
							}
						//}
						
					//}					
				}
				
			}
			
			Logger.w("Ligne micro fermé");
		}
		
		/**
		 * Représentation entière (0 - 100) du niveau sonore 
		 * Fonction récupérer dans les sources du programme disponible ici :
		 *  http://goo.gl/oRwrt et légèrement modifié
		 * @param audioData Un buffer audio
		 * @return Le niveau sonore.
		 */
		private int calculateRMSLevel()
		{ 
			byte[] audioData = new byte[250];
			micLine.read(audioData, 0, audioData.length);
			// audioData might be buffered data read from a data line
			long lSum = 0;
			for(int i=0; i<audioData.length; i++)
				lSum = lSum + audioData[i];

			double dAvg = lSum / audioData.length;

			double sumMeanSquare = 0d;
			for(int j=0; j<audioData.length; j++) {
				sumMeanSquare = sumMeanSquare + Math.pow(audioData[j] - dAvg,
																			2d);
			}

			double averageMeanSquare = sumMeanSquare / audioData.length;
			return (int)(Math.pow(averageMeanSquare,0.5d) + 0.5);
		}
	}
	
	/**
	 * Classe servant à l'enregistrement des fichiers audio
	 * @author Maxime Raynal
	 *
	 */
	class SoundWriter extends Thread {

		public static final String TMP_PATH = "/tmp/LibVoice/";
		public static final String TMP_FILE_NAME = "speechRecognition";

		public void run() {

			new File(TMP_PATH).mkdirs();

			try {
				AudioSystem.write(new AudioInputStream(micLine), 
						AudioFileFormat.Type.WAVE,
						new File(TMP_PATH + TMP_FILE_NAME + ".wav"));
			} catch (IOException e) {
				Logger.e("Impossible d'enregistrer le fichier audio pour " +
						"l'analyse : " + e.getMessage());
			}
			System.out.println("Conversion");
			new FLAC_FileEncoder().encode(
					new File(TMP_PATH + TMP_FILE_NAME + ".wav"),
								new File(TMP_PATH + TMP_FILE_NAME + ".flac"));
		
			new File(TMP_PATH + TMP_FILE_NAME + ".wav").delete();
		}
	}
}
