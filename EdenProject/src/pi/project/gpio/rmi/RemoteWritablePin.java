/*
 * InterfaceGPIO
 * pi.project.gpio.rmi                      24 janv. 2013
 * RemoteWritablePin.java
 */
package pi.project.gpio.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface d'accès distant au pin inscriptible
 * @author Maxime Raynal
 *
 */
public interface RemoteWritablePin extends Remote {
	/**
	 * Inscription distante d'un pin
	 * @param state L'état à inscrire
	 * @throws RemoteException 
	 */
	public void remoteWrite(boolean state) throws RemoteException;
}
