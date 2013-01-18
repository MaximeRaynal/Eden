/*
 * EdenProject
 * pi.project.sandbox                      17 janv. 2013
 * TestFirst.java
 */
package pi.project.sandbox;

import java.util.Scanner;

import pi.project.meteo.Meteo;
import pi.project.meteo.WeatherChannel;
import pi.project.meteo.yahooAPI.YahooWeatherChannel;
import pi.project.voice.Speaker;
import pi.project.voice.VoiceReconizer;
import pi.project.voice.googleAPI.recognize.GoogleAPIRecognizer;
import pi.project.voice.googleAPI.speak.GoogleAPISpeaker;

/**
 * Classe de test,
 * Test la reconnaissance vocale, la synthèse, l'API météo et les Logs 
 * @author maxime
 *
 */
public class TestFirst {
	
	/**
	 * Point d'entrée
	 * Commence par initialiser les services les utilises tours à tours.
	 * @param args
	 */
	public static void main(String[] args) {
		Speaker sp = new GoogleAPISpeaker("fr");
		VoiceReconizer vr = new GoogleAPIRecognizer();
		WeatherChannel wc = new YahooWeatherChannel();
		sp.say("système initialisé");
		Scanner sc = new Scanner(vr.listen());
		System.out.println("Start listening");
		boolean done = false;
		String commande = null;
		while (!done) {
			if (sc.hasNextLine()) {
				System.out.print("Une commande est détécté : ");
				commande = sc.nextLine();
				System.out.println(commande);
				if (commande.contains("temps")) {
					Meteo rodez = new Meteo("619742");
					
					wc.meteoAt(rodez);
					
					sp.say("A " + rodez.getCity() + " il fait " 
										+ rodez.getTemperature() + "degrés");
					done = true;
				} else {
					System.out.println("Unknow command");
				}
			}
		}
		
		sc.close();
	}

}
