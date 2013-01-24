/*
 * InterfaceGPIO
 * pi.project.gpio                      21 janv. 2013
 * InPin.java
 */
package pi.project.gpio;

/**
 * Pin de lecture, sert à recueillir des informations envoyé par les composants.
 * @author Maxime Raynal
 *
 */
public class InPinGPIO extends PinGPIO {
	
	/**
	 * Construit un pin de lecture à partir du numéro logique
	 * @param numero Le numéro logique du pin
	 */
	public InPinGPIO(LogicPinName numero) {
		super(PinMode.READ, numero);
	}
	
	/**
	 * Lit et retourne l'état d'un pin
	 * @return L'état ON ^ OFF du pin
	 */
	public PinState read() {
		return controller.readPin(numero);
	}
	
}
