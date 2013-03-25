/*
 * EdenProject
 * pi.project.config                      1 mars 2013
 * ConfigurationBean.java
 */
package pi.project.config;


/**
 * @author maxime
 *
 */
public class ConfigurationBean {

	/**
	 * Chemin vers le fichier XML 
	 */
	private String directoryFilePath;
	
	/**
	 * Chemin vers le fichier spécifiant les API à utiliser
	 */
	private String usedAPIFilePath;
	
	private String baseDirectoryPath;
	
	/**
	 * 
	 */
	public ConfigurationBean() {
		directoryFilePath = "";
		usedAPIFilePath = "";
		baseDirectoryPath = "";
	}

	/**
	 * @return the directoryFilePath
	 */
	public String getDirectoryFilePath() {
		return directoryFilePath;
	}

	/**
	 * @return the usedAPIFilePath
	 */
	public String getUsedAPIFilePath() {
		return usedAPIFilePath;
	}

	/**
	 * @param directoryFilePath the directoryFilePath to set
	 */
	public void setDirectoryFilePath(String directoryFilePath) {
		this.directoryFilePath = directoryFilePath;
	}

	/**
	 * @param usedAPIFilePath the usedAPIFilePath to set
	 */
	public void setUsedAPIFilePath(String usedAPIFilePath) {
		this.usedAPIFilePath = usedAPIFilePath;
	}

	/**
	 * @return the baseDirectoryPath
	 */
	public String getBaseDirectoryPath() {
		return baseDirectoryPath;
	}

	/**
	 * @param baseDirectoryPath the baseDirectoryPath to set
	 */
	public void setBaseDirectoryPath(String baseDirectoryPath) {
		this.baseDirectoryPath = baseDirectoryPath;
	}
	
	

}
