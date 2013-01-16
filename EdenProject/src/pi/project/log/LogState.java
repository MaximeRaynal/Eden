/*
 * Loger
 * pi.project.log                      14 janv. 2013
 * LogState.java
 */
package pi.project.log;

/**
 * Énumération définissant les différent type de log et leur importance
 * @author Maxime Raynal
 */
public enum LogState {
	
	/**
	 * Type de log erreur. Une erreur grave à provoqué la fermeture du,
	 *  ou nuit au fonctionnement du programme.
	 */
	ERROR("error"),
	
	/**
	 * Type de log warning. Une erreur est survenue mais ne nuit pas au
	 *  fonctionnement global du programme.
	 */
	WARNING("warning"),
	
	/**
	 * Type de log information. Permet de marquer dans les logs des informations
	 *  sur le déroulement du programme.
	 */
	INFO("info");
	
	private String value;
	
	private LogState(String s) {
		value = s;
	}
	
	/**
	 * Retourne la valeur de l'instance
	 * @return valeur
	 */
	public String getValue() {
		return value;
		
	}

}
