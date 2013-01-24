/*
 * InterfaceGPIO
 * pi.project.gpio.rmi                      24 janv. 2013
 * RemoteInPinGPIO.java
 */
package pi.project.gpio.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface RMI pour les pins de lecture
 * @author Maxime Raynal
 *
 */
public interface RemoteReadablePin extends Remote {
	
	/**
	 * Lit à distance l'état d'un pin
	 * @return L'état du pin
	 * @throws RemoteException
	 */
	public boolean remoteRead() throws RemoteException;
}
