/*
 * ObjectDistributionSystem
 * pi.project.objectDistribution.bean                      7 févr. 2013
 * Log.java
 */
package pi.project.objectDistribution.bean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Représentation en mémoire des logs correspondant à une date.
 * @author Maxime Raynal
 *
 */
public class Log {

	/**
	 * Les logs
	 */
	private Document doc;
	
	/**
	 * Récupère les log correspondant à une date
	 * @param path Chemin vers le fichier
	 */
	public Log(String path) {
		try {
			doc = new SAXBuilder().build(new File(path));
		} catch (JDOMException e) {
			e.printStackTrace();
			doc = null;
		} catch (NullPointerException | IOException e ) {
			doc = null;
		}
	}
	
	/**
	 * Retourne les messages contenu dans le fichier de log
	 * @param info True si l'on veut les logs d'infos
	 * @param warn True si l'on veut les logs d'avertissement
	 * @param error True si l'on veut les logs 
	 * @return Une liste de String
	 */
	public List<String> getMessage(boolean info, boolean warn, boolean error) {

		List<String> listToReturn = new ArrayList<String>();
		
		StringBuilder s;

		for (Element e : doc.getRootElement().getChild("logs").
														 getChildren("entry")) {
			s = new StringBuilder();
			if (e.getAttributeValue("level").contentEquals("info") && info) {
				s.append("I ");
				s.append("- " + e.getChild("time").getText() + " - ");
				s.append(e.getChild("message").getText());
				listToReturn.add(s.toString());
			} else if (e.getAttributeValue("level").contentEquals("warn") 
					&& warn) {
				s.append("W ");
				s.append("- " + e.getChild("time").getText() + " - ");
				s.append(e.getChild("message").getText());
				listToReturn.add(s.toString());
			} else if (e.getAttributeValue("level").contentEquals("error") 
					&& error) {
				s.append("E ");
				s.append("- " + e.getChild("time").getText() + " - ");
				s.append(e.getChild("message").getText());
				listToReturn.add(s.toString());
			} 
		}

		return listToReturn;
	}

	
	/**
	 * Indique si des logs ont était trouvé
	 * @return True si pas de log
	 */
	public boolean haveNoLog() {
		return doc == null;
	}	
}
