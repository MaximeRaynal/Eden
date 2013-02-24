/*
 * ObjectDistribution
 * pi.project.objectDistribution.data                      31 janv. 2013
 * ReponseState.java
 */
package pi.project.objectDistribution.data;

/**
 * @author maxime
 *
 */
public enum ReponseState {
	
	/**
	 * La requête a bien fonctionner
	 */
	OK,
	
	/**
	 * Une erreur indéfinie est survenue
	 */
	ERROR,
	
	/**
	 * L'authentification à échoué
	 */
	AUTHENTIFICATION_ERROR,
	
	/**
	 * La requête n'a pas était comprise
	 */
	MISUNDERSTOOD_ERROR;
	
	/**
	 * On verrouille le constructeur pour l'énumération
	 */
	private ReponseState() {
		
	}

}
