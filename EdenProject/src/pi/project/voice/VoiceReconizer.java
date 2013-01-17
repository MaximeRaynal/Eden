/*
 * LibVoice
 * pi.project.voice                      10 janv. 2013
 * VoiceReconizer.java
 */
package pi.project.voice;

import java.io.PipedReader;

/**
 * Interface de reconnaissance vocale pour le projet
 * Au cours des développements du projet je risque de modifier les libs que
 *  j'utilises. Je définie donc un interface pour que les surcouches n'est pas 
 *  besoin d'être modifié en cas de modification de la lib sous jacente
 * Dans le cas ou une API ne remplie pas toute les besoins (ex : chuchoter)
 *  elle doit effectuer l'action la plus proche qu'elle puisse en lieux de. 
 * @author maxime
 *
 */
public interface VoiceReconizer {
	/**
	 * Écoute sur le micro et renvoi marque dans un stream les commandes à 
	 *  exécuter
	 * @return Un flux dans lequel seront inscrit les commandes
	 */
	PipedReader listen();
}
