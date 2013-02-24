/*
 * ObjectDistributionSystem
 * pi.project.objectDistribution.serveur                      9 févr. 2013
 * ShippableObjectFactory.java
 */
package pi.project.objectDistribution.serveur;

import pi.project.objectDistribution.data.Request;

/**
 * Interface de pattern Abstract Factory.
 * 
 * Implementation disponible : 
 * <ul>
 * <li>JSON</li>
 * <li>RMI</li>
 * </ul>
 * @author Maxime Raynal
 */
public interface ShippableObjectFactory {
	
	/**
	 * Retourne une String servant d'identifiant
	 * @param serv Le service auquel on veut accéder
	 * @param req La requête indiquant l'objet à récupérer
	 * @return Information permettant d'accéder à l'objet implémenter
	 */
	public String getPackagedObject(Request req);
}
