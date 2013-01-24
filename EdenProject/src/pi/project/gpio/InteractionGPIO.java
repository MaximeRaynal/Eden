/*
 * InterfaceGPIO
 * pi.project.gpio                      5 janv. 2013
 * InteractioGPIO.java
 */
package pi.project.gpio;

/**
 * Définit la structure des classes permettant l'interaction de bas niveau
 * entre les classes d'outil et le système
 * @author Maxime Raynal
 *
 */
public interface InteractionGPIO {
	/**
	 * Déclare un pin en lecture
	 * @param pinName Le numéro logique du pin
	 */
	public void openReadablePin(LogicPinName pinName);
	
	/**
	 * Déclare un pin en écriture
	 * @param pinName Le numéro logique de pin
	 */
	public void openWritablePin(LogicPinName pinName);
	
	/**
	 * Lit l'état d'un pin
	 * @param pinName Le numéro logique du pin
	 * @return L'état du Pin
	 */
	public PinState readPin(LogicPinName pinName);
	
	/**
	 * Change l'état d'un pin
	 * @param state L'état à affecter au pin
	 * @param pinName Le numéro logique du pin
	 */
	public void writePin(PinState state, LogicPinName pinName);
}
