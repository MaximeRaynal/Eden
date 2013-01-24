/*
 * InterfaceGPIO
 * pi.project.gpio.rmi                      24 janv. 2013
 * RemoteOutPin.java
 */
package pi.project.gpio.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import pi.project.gpio.OutPinGPIO;
import pi.project.gpio.PinState;

/**
 * Implementation distante d'un pin Readable
 * @author Maxime Raynal
 *
 */
public class RemoteOutPin extends UnicastRemoteObject implements
		RemoteWritablePin {
	
	/**
	 * Identifiant standard de serialization
	 */
	private static final long serialVersionUID = -3945612712259618684L;

	/**
	 * Le pin inscriptible
	 */
	private OutPinGPIO pin;

	/* (non-Javadoc)
	 * @see pi.project.gpio.rmi.RemoteWritablePin#remoteWrite(boolean)
	 */
	@Override
	public void remoteWrite(boolean state) throws RemoteException {
		pin.write(PinState.fromBoolean(state));

	}
	
	/**
	 * 
	 * @throws RemoteException
	 */
	public RemoteOutPin() throws RemoteException {
		
	}

}
