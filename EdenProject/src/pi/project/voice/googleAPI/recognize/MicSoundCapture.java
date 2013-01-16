/*
 * LibVoice
 * pi.project.voice.googleAPI.recognize                      14 janv. 2013
 * MicSoundCapture.java
 */
package pi.project.voice.googleAPI.recognize;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import pi.project.log.Logger;

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
	public static final float levelVariation = 1.20f;
	
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
	 */
	public void startListening() {
		try {
			micLine.open(standardFormat);
		} catch (LineUnavailableException e) {
			Logger.e("Impossible d'ouvrir au micro  : " + e.getMessage());
		}
		
		micLine.start();
		
		float currentLevel = micLine.getLevel();
	}
}
