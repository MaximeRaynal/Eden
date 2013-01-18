/*
 * Loger
 * pi.project.log                      14 janv. 2013
 * Logger.java
 */
package pi.project.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Classe de gestion des Log.
 * Les logs sont gérer sous la forme XML pour faciliter le parcourt et le
 *  traitement. Le processus de log à vocation à avoir une interface logiciel
 *  dédié.
 * Pour faciliter le traitement, le processus crée un fichier de log par jour.
 * Un nom d'un fichier de log à la forme : eden_log_jj_mm_aa.xml
 * @author Maxime Raynal
 */
public class Logger {
	
	/**
	 * Le chemin vers le répertoire de log
	 */
	public static String LOG_PATH = "./log/";
	
	/**
	 * Ajoute une entrée dans le journal
	 * @param state Le niveau du message 
	 * @param msg Le message
	 */
	private static void write(LogState state, String msg) {
		
		//Récupération de la date courante
		Calendar today = Calendar.getInstance();
		String todayIdentifier = today.get(Calendar.DAY_OF_MONTH) + "_" +
					today.get(Calendar.MONTH) + "_" + today.get(Calendar.YEAR);
	
		File logFile = new File(LOG_PATH + "eden_log_" + todayIdentifier + 
																		".xml");
		//logFile.mkdirs();
		try {
			if (logFile.createNewFile()) {
				generateNewFile(logFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Document log = open(logFile.getAbsolutePath());
		
		serialize(addEntry(log,msg, state), logFile);
		
				
	}

	/**
	 * Inscrit dans le log un message de type erreur
	 * @param msg Le message à écrire
	 */
	public static void e(String msg) {
		Logger.write(LogState.ERROR, msg);
	}
	
	/**
	 * Inscrit dans le log un message de type warning
	 * @param msg Le message à écrire
	 */
	public static void w(String msg) {
		Logger.write(LogState.WARNING, msg);
	}
	
	/**
	 * Inscrit dans le log un message de type info
	 * @param msg Le message à écrire
	 */
	public static void i(String msg) {
		Logger.write(LogState.INFO, msg);
	}
	
	/**
	 * Met en place un nouveau fichier
	 * @param logFile
	 */
	private static void generateNewFile(File logFile) {
		//Récupération de la date courante
		Calendar today = Calendar.getInstance();
		String date = today.get(Calendar.DAY_OF_MONTH) + "/" +
				today.get(Calendar.MONTH) + "/" + today.get(Calendar.YEAR);
		Document doc = open("xml/log/log_base.xml");
		doc.getRootElement().getChild("date").addContent(date);
		
		serialize(doc, logFile);		
	}
	
	/**
	 * Ajoute une entrée de log au document
	 * @param doc Le document auquel ajouter l'entrée
	 * @param msg Le message du log
	 */
	private static Document addEntry(Document doc, String msg, LogState state) {
		
		//Génération de l'entrée
		Document entry = open("xml/log/log_entry.xml");
		entry.getRootElement().setAttribute("level", state.getValue());
		Calendar today = Calendar.getInstance();
		
		entry.getRootElement().getChild("time").setText(
									today.get(Calendar.HOUR_OF_DAY) + ":" +
									today.get(Calendar.MINUTE) + ":" + 
									today.get(Calendar.SECOND));
		
		entry.getRootElement().getChild("message").setText(msg);
		
		//On ajoute l'entrée
		doc.getRootElement().getChild("logs").addContent(entry.detachRootElement());
		return doc;
	}
	
	/**
	 * Enregistre un dans fichier un document XML
	 * @param doc Le document à enregistrer
	 * @param f Le fichier dans lequel on enregistre
	 */
	private static void serialize(Document doc, File f) {
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		try {
			sortie.output(doc, new FileOutputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ouvre un document et retourne un fichier XML.
	 * @param path Chemin vers le fichier à ouvre
	 * @return Un object Document pour le traitement
	 */
	private static Document open(String path) {
		Document doc = new Document();
		SAXBuilder sx = new SAXBuilder();
		try {
			doc = sx.build(new File(path));
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
}
