/*
 * ObjectDistributionSystem
 * pi.project.objectDistribution.serveur                      9 févr. 2013
 * ObjectFactory.java
 */
package pi.project.objectDistribution.bean;

/**
 * Interface de fabrique permettant de fabriquer les beans à transmettre
 * @author Maxime Raynal
 */
public interface ObjectFactory {
	
	/**
	 * Méthode de création des objets, instancie l'objet à partir des 
	 *  informations transmisent dans la requête
	 * @param information Chaîne de caractère indiquant l'objet à transmettre
	 * @return L'objet instancié
	 */
	public Object build(String information);
}
