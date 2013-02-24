/*
 * ObjectDistributionSystem
 * pi.project.objectDistribution.client                      9 févr. 2013
 * ReceivableObjectFactory.java
 */
package pi.project.objectDistribution.client;

import pi.project.objectDistribution.data.Reponse;

/**
 * Interface de fabrique restitution des objets transmis par le serveur.
 * @author Maxime Raynal
 */
public interface ReceivableObjectFactory {
	
	/**
	 * Reconstruit un objet à partir de la réponse à la requête
	 * @param <T> Le type de l'objet retourné
	 * @param rep La réponse à la requête lancé par le client
	 * @param type Un pointeur sur la classe de l'objet à retourner
	 * @return L'objet instancié null si un problème est survenue avec la 
	 *  requête 
	 */
	public <T> Object restituteObject(Reponse rep, Class<T> type);
}
