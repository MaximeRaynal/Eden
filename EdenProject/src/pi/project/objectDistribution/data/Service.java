/*
 * ObjectDistributionSystem
 * pi.project.objectDistribution.bean                      7 févr. 2013
 * Service.java
 */
package pi.project.objectDistribution.data;

/**
 * Représente les différents service accessible pour le requêtage.
 * @author Maxime Raynal
 *
 */
public enum Service {
	
	/**
	 * Requête sur le service de log.
	 * Le contenu de la requête contient la date dont on demande les logs.
	 * Renvoi un objet de type Log.
	 */
	LOG_SERVICE;
	
	/**
	 * 
	 */
	private Service() {
		
	}

}
