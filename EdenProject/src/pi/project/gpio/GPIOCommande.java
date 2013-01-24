/*
 * InterfaceGPIO
 * pi.project.gpio                      22 janv. 2013
 * GPIOCommande.java
 */
package pi.project.gpio;

import java.io.IOException;
import java.util.Scanner;

/**
 * Classe privée de bas niveau servant à effectuer les interactions avec 
 * les PIN GPIO,
 * Utilise initialement des appel système GPIO, 
 * a vocation à être remplacé par du code JNI avec la libGPIO
 * @author Maxime Raynal
 *
 */
public class GPIOCommande implements InteractionGPIO {

	/**
	 * Appel système basique 
	 */
	private static final String SYSTEM_CALL = "/bin/sh gpio ";
	
	/**
	 * Code renvoyé lorsque le code de retour n'a pas de paramètre
	 */
	private static final int NO_RETURN_CODE = -1;
	
	
	/* (non-Javadoc)
	 * @see pi.project.gpio.InteractionGPIO#openReadablePin(int)
	 */
	@Override
	public void openReadablePin(LogicPinName pinName) {
		exec_cmd("mode " + pinName.toGPIONumber() + " in");
	}


	/* (non-Javadoc)
	 * @see pi.project.gpio.InteractionGPIO#openWritablePin(pi.project.gpio.
	 * LogicPinName)
	 */
	@Override
	public void openWritablePin(LogicPinName pinName) {
		exec_cmd("mode " + pinName.toGPIONumber() + " out");
		
	}


	/* (non-Javadoc)
	 * @see pi.project.gpio.InteractionGPIO#readPin(pi.project.gpio.
	 * LogicPinName)
	 */
	@Override
	public PinState readPin(LogicPinName pinName) {
		return exec_cmd("read " + pinName.toGPIONumber()) == 1 ? 
													PinState.ON : PinState.OFF;
	}



	/* (non-Javadoc)
	 * @see pi.project.gpio.InteractionGPIO#writePin(pi.project.gpio.PinState)
	 */
	@Override
	public void writePin(PinState state, LogicPinName pinName) {
		exec_cmd("write " + pinName.toGPIONumber() + " " + state.toInt());
	}
	
	/**
	 * Exécute une commande système
	 * @param cmd La commande
	 * @return Le code de retour, NO_RETURN_CODE si pas de code de retour
	 */
	private int exec_cmd(String cmd) {
		int ret = NO_RETURN_CODE;
		try {
			Process p = Runtime.getRuntime().exec(SYSTEM_CALL + cmd);
			Scanner sc = new Scanner(p.getInputStream());
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (sc.hasNextInt()) {
				ret = sc.nextInt();
			}
			
			sc.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
