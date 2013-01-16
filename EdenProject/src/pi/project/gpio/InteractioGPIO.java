/*
 * InterfaceGPIO
 * pi.project.gpio                      5 janv. 2013
 * InteractioGPIO.java
 */
package pi.project.gpio;

/**
 * Définit la structure des classes permettants l'interaction de bas niveau
 * entre les classes d'outil et le système
 * @author Maxime Raynal
 *
 */
public interface InteractioGPIO {
	/**
	 * Déclare un pin en lecture
	 * @param p Le numéro logique du pin
	 */
	public void openReadablePin(int p);
	
	/**
	 * Déclare un pin en écriture
	 * @param p Le numéro logique de pin
	 */
	public void openWritablePin(int p);
	
	/**
	 * Stop l'utilisation d'un pin
	 * @param p Le numéro logique du pin
	 */
	public void closePin(int p);
	
	/**
	 * Lit l'état d'un pin
	 * @param p Le numéro logique du pin
	 * @return L'état du Pin
	 */
	public PinEtat readPin(int p);
	
	/**
	 * Change l'état d'un pin
	 * @param e L'état à affecter au pin
	 */
	public void writePin(PinEtat e);
}
