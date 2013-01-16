/*
 * LibVoice
 * pi.project.voice                      10 janv. 2013
 * Speaker.java
 */
package pi.project.voice;

/**
 * Interface TTS pour le projet.
 * Au cours des développement du projet je risque de modifier les libs que
 *  j'utilises. Je définie donc un interface pour que les surcouches n'est pas 
 *  besoin d'être modifié en cas de modification de la lib soujacente
 * Dans le cas ou une API ne remplie pas toute les besoins (ex : chuchoter)
 *  elle doit effectuer l'action la plus proche qu'elle puisse en lieux de. 
 * @author Maxime Raynal
 */
public interface Speaker {
	
	
	/**
	 * Action de base, sortie normale, sans paramétrage
	 * @param toSay
	 */
	public void say(String toSay);

}
