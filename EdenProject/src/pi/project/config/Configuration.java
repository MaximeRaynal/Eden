/*
 * EdenProject
 * pi.project.config                      25 févr. 2013
 * Configuration.java
 */
package pi.project.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.yaml.snakeyaml.Yaml;

/**
 * Design Pattern Singleton, gère la configuration de Eden, basé sur des
 *  fichiers YAML et XML, il permet entre autres de récupérer les adresses des 
 * différents chemin vers les différents dossier. 
 * @author maxime
 *
 */
public class Configuration {
	
	/**
	 * Fait un lien entre une clef et une valeur, la clef représente un 
	 *  service, la valeur représente un chemin sous forme Stirng
	 */
	private Map<String, String> paths;
	
	/**
	 * Le stockage de la configuration
	 */
	private ConfigurationBean conf;
	
	/**
	 * L'instance Singleton
	 */
	private static volatile Configuration instance;
	
	/**
	 * Constructeur privée pour empêcher l'instanciation du Singleton par 
	 *  l'utilisateur
	 */
	private Configuration() {
		paths = new HashMap<String, String>();
		
		Yaml c = new Yaml();
		
		try {
			conf = c.loadAs(new FileReader("main_conf.yaml"), 
													ConfigurationBean.class);
		} catch (FileNotFoundException e) {
			//Logger.e("Impossible de charger la configuration principale" 
			//												+ e.getMessage());
		}
		
		
		try {
			Document directory = new SAXBuilder().build(conf.
														getDirectoryFilePath());
			for(Element e : directory.getRootElement().getChildren()) {
				System.out.println();
				paths.put(e.getAttributeValue("id"), e.getChildText("path"));
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne l'instance de du DP singleton
	 * @return L'instance de la classe configuration
	 */
	public final static Configuration getConfiguration() {
		//Le double if évite le synchronized trop coûteux à chaque appel 
		// sauf le premier
		if (instance == null) {
			synchronized (Configuration.class) {
				if (instance == null) {
					instance = new Configuration();
				}
			}
		}
		return instance;
	}
	
	/**
	 * Permet d'obtenir un chemin en fonction de la clef passé en paramètre
	 * @param key La clef définissant quel paramètre on veut accéder
	 * @return Le chemin auquel on souhaite accéder, null si la clefs est 
	 *  inconnue
	 */
	public String path(String key) {
		return conf.getBaseDirectoryPath() + paths.get(key);
	}
}
