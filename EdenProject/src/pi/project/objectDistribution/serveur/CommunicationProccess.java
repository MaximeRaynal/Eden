/*
 * XMLDistributor
 * pi.project.xml.serveur                      27 janv. 2013
 * XMLCommunicationProccess.java
 */
package pi.project.objectDistribution.serveur;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import pi.project.log.Logger;

/**
 * @author maxime
 *
 */
public class CommunicationProccess implements Runnable {

	/**
	 * Connexion avec le client
	 */
	private Socket clientConnexion;
	
	/**
	 * Constructeur avec affectation de la connexion
	 * @param client La connexion vers le client
	 */
	public CommunicationProccess(Socket client) {
		clientConnexion = client;
		Logger.i("Une connexion à été établi : " + client.getInetAddress());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Scanner sc = null;
		try {
			sc = new Scanner(clientConnexion.getInputStream());
			
			//Attente de l'authetification et de la requête
			do {
				if (sc.hasNext()) {
					
				}
			} while (!clientConnexion.isClosed());
			
			Logger.i("Connexion fermé");
			
		} catch (IOException e) {
			Logger.w("Impossible de lire les message du client " 
									+ clientConnexion.getInetAddress() + " : " 
												+ e.getMessage());
		}
		
	}

}
