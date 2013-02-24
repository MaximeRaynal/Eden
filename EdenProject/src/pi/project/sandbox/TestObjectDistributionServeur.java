/*
 * ObjectDistributionSystem
 * pi.project.objectDistribution.serveur                      24 févr. 2013
 * Test.java
 */
package pi.project.sandbox;

import pi.project.objectDistribution.serveur.ObjectDistributionServeur;

/**
 * Classe de test du concept de distribution d'objet.
 * Lance le serveur pour tester la réception des objets 
 * @author Maxime Raynal
 *
 */
public class TestObjectDistributionServeur {

	/**
	 * Point d'entrée du programme, lance juste le serveur.
	 * Écris sur l'entrée standard pour avoir un repérage
	 * @param args
	 */
	public static void main(String[] args) {
		//Lancement du serveur dans un Thread
		new Thread(new ObjectDistributionServeur()).start();
	}

}
