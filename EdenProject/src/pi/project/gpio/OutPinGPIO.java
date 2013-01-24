/*
 * InterfaceGPIO
 * pi.project.gpio                      21 janv. 2013
 * OutPinGPIO.java
 */
package pi.project.gpio;

/**
 * Pin d'écriture, servant à transmettre des informations au composants
 * @author Maxime Raynal
 *
 */
public class OutPinGPIO extends PinGPIO {

	/**
	 * Construit un pin d'écriture à partir de son numéro logique
	 * @param numero Le numéro logique du pin
	 */
	public OutPinGPIO(LogicPinName numero) {
		super(PinMode.WRITE, numero);
	}

	/**
	 * Met le pin à l'état indiqué
	 * @param state L'état du pin
	 */
	public void write(PinState state) {
		controller.writePin(state, numero);
	}
}
