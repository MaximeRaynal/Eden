/*
 * InterfaceGPIO
 * pi.project.gpio                      5 janv. 2013
 * PinEtat.java
 */
package pi.project.gpio;

/**
 * Énumération des différents états que peut prendre un Pin.
 * L'état étant binaire il y a 2 valeurs possible ON et OFF
 * @author Maxime Raynal
 *
 */
public enum PinState {
	/**
	 * Etat ouvert du pin, le courant passe, numéro logique 1
	 */
	ON,
	
	/**
	 * Etat ferme du pin, le courant ne passe plus , numéro logique 0
	 */
	OFF;
	
	/**
	 * Constructeur de l'énumération
	 */
	private PinState() {
	}

	/**
	 * Retourne un PinState selon l'état du boolean
	 * @param state L'état boolean d'un pin
	 * @return ON ^ OFF
	 */
	public static PinState fromBoolean(boolean state) {
		return state ? ON : OFF;
	}
	
	/**
	 * Renvoi un entier correspondant à la valeur logique du pin
	 * @return Un entier représentant une valeur logique : 0 ou 1, -1 si null
	 */
	public int toInt() {
		return this == ON ? 1 : 0;
	}
	
	/**
	 * Renvoi un boolean représentant l'état du pin
	 * @return true si l'état est ON false sinon
	 */
	public boolean toBoolean() {
		return this == ON;
	}
}
