/*
 * InterfaceGPIO
 * pi.project.gpio                      5 janv. 2013
 * PinGPIO.java
 */
package pi.project.gpio;

/**
 * Classe définissant les interactions de l'utilisateur avec les pins
 * contient 
 * @author maxime
 *
 */
public abstract class PinGPIO {
	
	/**
	 * Le numéro logique du pin
	 */
	protected LogicPinName numero;
	
	/**
	 * Le mode d'utilisation du pin
	 */
	protected PinMode mode;

	/**
	 * Un référence sur un objet servant à interagir avec les pins
	 */
	protected InteractionGPIO controller;
	
	/**
	 * Constructeur du pin, on indique un mode d'utilisation et un numéro 
	 *  logique pour le pin.
	 * @param m Le mode d'utilisation du pin
	 * @param n Le numéro logique du pin
	 */
	public PinGPIO(PinMode m, LogicPinName n) {
		mode = m;
		numero = n;
		
		if (mode == PinMode.READ) {
			controller.openReadablePin(numero);
		} else if (mode == PinMode.WRITE) {
			controller.openWritablePin(numero);
		}
	}

	/**
	 * @return the numero
	 */
	public LogicPinName getNumero() {
		return numero;
	}

	/**
	 * @return the mode
	 */
	public PinMode getMode() {
		return mode;
	}
	
	

}
