/*
 * LibVoice
 * pi.project.voice.googleAPI.recognize                      14 janv. 2013
 * GoogleAPIRecognizer.java
 */
package pi.project.voice.googleAPI.recognize;

import java.io.InputStream;

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
	public InputStream listen() {
		InputStream commandeStream = null;
		
		//Initialisation du micro
		
		return commandeStream;
	}

}
