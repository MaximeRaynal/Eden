/*
 * InterfaceGPIO
 * pi.project.gpio                      5 janv. 2013
 * PinEtat.java
 */
package pi.project.gpio;

/**
 * @author maxime
 *
 */
public enum PinEtat {
	/**
	 * Etat ouvert du pin
	 */
	ON,
	
	/**
	 * Etat ferme du pin
	 */
	OFF;
	
	/**
	 * Constructeur de l'énumération
	 */
	private PinEtat() {
	}

}
