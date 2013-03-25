/*
 * ObjectDistributionSystem
 * pi.project.objectDistribution.serveur                      24 févr. 2013
 * Test.java
 */
package pi.project.sandbox;

import pi.project.objectDistribution.bean.Log;
import pi.project.objectDistribution.client.ReceivableObjectFactoryJsonImpl;
import pi.project.objectDistribution.data.Reponse;
import pi.project.objectDistribution.data.Request;
import pi.project.objectDistribution.data.Service;
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
		System.out.println("Lancement du thread de serveur.");
		new Thread(new ObjectDistributionServeur()).start();
		System.out.println("Thread lancé");
		
		System.out.println("Lancement de la requête");
		Request r = new Request("", "Date(25/03/2013);",
				Service.LOG_SERVICE);
		
		System.out.println("Envoie de la requête");
		Reponse rep = r.send();
		
		System.out.println("Refactoring de la reponse");
		Log l = new ReceivableObjectFactoryJsonImpl().restituteObject(rep,
																	Log.class);
		System.out.println("Analyse du retour");
		for (String s : l.getMessage(true, true, true)) {
			System.out.println(s);
		}
		
		System.out.println("Process terminé");
	}
}
