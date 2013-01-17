/*
 * LibVoice
 * pi.project.voice.googleAPI.recognize                      14 janv. 2013
 * GoogleAPIRecognizer.java
 */
package pi.project.voice.googleAPI.recognize;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

import pi.project.log.Logger;
import pi.project.voice.VoiceReconizer;

/**
 * Reconnaissance vocale par l'API de Google.
 * Les inconvénients principaux sont : c'est un web service, donc dépend de 
 * la connexion internet et la conversion en flac ralentissant le procédé
 * @author Maxime Raynal
 *
 */
public class GoogleAPIRecognizer implements VoiceReconizer {

	/* (non-Javadoc)
	 * @see pi.project.voice.VoiceReconizer#listen()
	 */
	@Override
	public PipedReader listen() {
		
		//Initialisation du micro
		MicSoundCapture microphone = new MicSoundCapture();
		
		PipedWriter commandeStreamOut = new PipedWriter();
		PipedReader commandeStream = null;
		try {
			commandeStream = new PipedReader(commandeStreamOut);
		} catch (IOException e) {
			Logger.w("Impossible d'utiliser un pipe de communication : " + 
																e.getMessage());
		}
		
		microphone.startListening(commandeStreamOut);
		
		return commandeStream;
	}

}
