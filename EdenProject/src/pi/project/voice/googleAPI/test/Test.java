/*
 * LibVoice
 * pi.project.voice.googleAPI.test                      11 janv. 2013
 * Test.java
 */
package pi.project.voice.googleAPI.test;

import pi.project.voice.Speaker;
import pi.project.voice.googleAPI.GoogleAPISpeaker;

/**
 * @author maxime
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Speaker sp = new GoogleAPISpeaker("fr");
		
		sp.say("Ceci est une longue phrase pour tester l'API de google !" +
				" Il faut une phrase de plus de 100 caractères pour dépasser la limite initiale et tester le découpage.");

	}

}
