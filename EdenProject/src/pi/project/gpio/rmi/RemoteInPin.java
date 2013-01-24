/*
 * InterfaceGPIO
 * pi.project.gpio.rmi                      24 janv. 2013
 * RemoteInPin.java
 */
package pi.project.gpio.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import pi.project.gpio.InPinGPIO;

/**
 * @author maxime
 *
 */
public class RemoteInPin extends UnicastRemoteObject implements
		RemoteReadablePin {

	/**
	 * Identifiant de sérialisation
	 */
	private static final long serialVersionUID = -4755950528289699036L;
	
	/**
	 * Le pin auquel on accède
	 */
	private InPinGPIO pin;
	
	/* (non-Javadoc)
	 * @see pi.project.gpio.rmi.RemoteReadablePin#remoteRead()
	 */
	@Override
	public boolean remoteRead() throws RemoteException {
		return pin.read().toBoolean();
	}
	
	/**
	 * Constructeur
	 * @throws RemoteException
	 */
	public RemoteInPin() throws RemoteException {
		
	}

}
