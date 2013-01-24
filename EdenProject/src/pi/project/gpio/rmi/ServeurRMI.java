/*
 * InterfaceGPIO
 * pi.project.gpio.rmi                      24 janv. 2013
 * ServeurRMI.java
 */
package pi.project.gpio.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Serveur de mise en place pour le RMI sur les Pin
 * @author Maxime Raynal
 *
 */
public class ServeurRMI implements Runnable {
	
	/**
	 * Le nom bindé 
	 */
	public static final String addr = "//eden-raspberrypi:1099/";
	
	/**
	 * Lance le serveur
	 */
	public void lunch() {
		System.setSecurityManager(new RMISecurityManager());
		try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		try {
			Naming.bind(addr+"INPin", new RemoteInPin());
		} catch (MalformedURLException | RemoteException
				| AlreadyBoundException e) {
			e.printStackTrace();
		}
		
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		lunch();
	}

}
