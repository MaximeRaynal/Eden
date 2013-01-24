/*
 * InterfaceGPIO
 * pi.project.gpio                      24 janv. 2013
 * PinFactory.java
 */
package pi.project.gpio;

import java.util.ArrayList;
import java.util.List;

/**
 * Sert à gérer l'utilisation des pins sur le Raspberry.
 * Dans un premier temps, ils n'est pas concevable que les pins changes 
 *  d'affectation. Cela se fera par la suite.
 * DP Singleton
 */
public final class PinFactory {
	
	private static volatile PinFactory factory;
	
	/**
	 * Liste les pin déjà utilisé
	 */
	private List<LogicPinName> alreadyUse;

	/**
	 * Met en place la factory, aucun pin n'est encore utilisé
	 */
	private PinFactory() {
		alreadyUse = new ArrayList<>();
	}
	
	/**
	 * Méthode d'appel du Singleton, renvoi toujours la même instance de factory
	 * @return L'instance de factory
	 */
	public static PinFactory getFactory() {
		if (factory == null) {
			synchronized (PinFactory.class) {
				if (factory == null) {
					factory = new PinFactory();
				}
			}
		}
		return factory;
	}
	
	/**
	 * Déclare l'utilisation d'un pin
	 * @param name Le nom logique du pin
	 * @param mode Le mode d'utilisation
	 * @return Un pin utilisable, null si il est déjà utilisé
	 */
	public PinGPIO usePin(LogicPinName name, PinMode mode) {
		if (!alreadyUse.contains(name)) {
			if (mode == PinMode.READ) {
				return new InPinGPIO(name);
			} else {
				return new OutPinGPIO(name);
			}
		}
		
		return null;
	}
}
