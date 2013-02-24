/*
 * ObjectDistributionSystem
 * pi.project.objectDistribution.serveur                      17 févr. 2013
 * LogFactoryImplJson.java
 */
package pi.project.objectDistribution.serveur;

import pi.project.objectDistribution.bean.Log;
import pi.project.objectDistribution.bean.ObjectFactory;


/**
 * @author maxime
 *
 */
public class LogFactoryImplJson implements ObjectFactory {
	
	/**
	 * Chemin relatif vers le dossier
	 */
	private static final String LOG_PATH = "./log/";
	
	private static final String LOG_FILE_NAME = "eden_log_";
	
	/**
	 * Pattern de détection et d'analyse de la requête
	 */
	private final static String regexPattern = 
		   			  "Date(\\((([0-3]?)\\d)/([0-1]?\\d)/((\\d\\d)?\\d\\d)\\))";

	/* (non-Javadoc)
	 * @see pi.project.objectDistribution.bean.ObjectFactory
	 * 													#build(java.lang.String)
	 */
	@Override
	public Object build(String information) {
		
		String[] t = information.split(regexPattern);
		
		String path = LOG_PATH + LOG_FILE_NAME + t[2] + "_"
				                               + t[4] + "_"
				                               + t[5] + ".xml";		
		return new Log(path);
	}

}
