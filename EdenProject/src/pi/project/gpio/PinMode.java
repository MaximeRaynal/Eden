/*
 * InterfaceGPIO
 * pi.project.gpio                      21 janv. 2013
 * PinMode.java
 */
package pi.project.gpio;

/**
 * Énumération des différents mode du pin, il y a deux états possible exclusif :
 *  Lecture et Écriture
 * @author Maxime Raynal
 *
 */
public enum PinMode {
	
	/**
	 * Le mode lecture, pour recueillir des informations
	 */
	READ,
	
	/**
	 * Le mode écriture, pour transmettre des informations
	 */
	WRITE;
	
	/**
	 * 
	 */
	private PinMode() {
		
	}

}
