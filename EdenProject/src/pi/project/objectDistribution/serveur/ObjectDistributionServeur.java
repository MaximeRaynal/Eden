/*
 * XMLDistributor
 * pi.project.xml.serveur                      27 janv. 2013
 * XMLDistributorServeur.java
 */
package pi.project.objectDistribution.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import pi.project.log.Logger;

/**
 * Serveur de distribution de flux XML.
 * Dans le cadre du projet Eden, il est très utile d'avoir un serveur, qui peut
 *  distribuer les ressources XML (principal moyen de configuration et 
 *  serialization). Le serveur ) vocation à être lui même un thread du projet.
 * Le serveur est MultiThread, multi utilisateur, la communication se fait au 
 * format JSON.
 * @author Maxime Raynal
 *
 */
public class ObjectDistributionServeur implements Runnable{
	
	private ServerSocket serveur;
	
	/**
	 * Le port utilisé par le serveur.
	 * Le 10 février 98 est la date de sortie de XML 1 
	 */
	public static final int PORT = 10298;
	
	/**
	 * Constructeur, initialisation du socket serveur et bind du port
	 */
	public ObjectDistributionServeur() {
		try {
			serveur = new ServerSocket(PORT);
		} catch (IOException e) {
			Logger.e("Impossible d'initialiser le serveur de distribution : " +
												e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Logger.i("Lancement du serveur de distribution, attente de connexion");
		Socket client = null;
		for (;;) {
			try {
				client = serveur.accept();
				new Thread(new CommunicationProccess(client)).start();
				Logger.i("Connexion accepté de : " + client.getInetAddress());
			} catch (IOException e) {
				Logger.w("Une erreur est survenue lors de l'établissement " +
						"d'une connexion");
			}
		}
	}

}
