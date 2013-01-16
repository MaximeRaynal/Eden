/*
 * LibVoice
 * pi.project.voice.googleAPI                      10 janv. 2013
 * GoogleAPISpeaker.java
 */
package pi.project.voice.googleAPI.speak;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import pi.project.log.Logger;
import pi.project.voice.Speaker;

/**
 * Classe permettant une sortie audio.
 * Utilise le Web service de Google pour créer un fichier audio
 * Le concept n'est certes pas bon mais c'est une solution viable, simple à
 *  mettre en place et avec un très bon rendu.
 * Cependant ce la dépend de la connection internet et du maintient de l'API par
 * Google.
 * @author Maxime Raynal
 *
 */
public class GoogleAPISpeaker implements Speaker {

	/**
	 * Chemin vers le répertoire temporaire
	 */
	public static String TEMP_PATH = "/tmp/LibVoice/";
	
	/**
	 * Url d'accès au Web Service 
	 */
	public static String BASE_URL = "http://translate.google.com/translate_tts";
	
	/**
	 * La langue dans laqu'elle sera effectué l'analyse
	 */
	private String lang;
	
	/**
	 * Constructeur par défaut, définit la langue comme français
	 */
	public GoogleAPISpeaker() {
		lang = "fr";
	}
	
	/**
	 * Constructeur permettant à l'utilisateur de choisir la langue
	 * @param l Le code de langue que veut utiliser l'utilisateur
	 */
	public GoogleAPISpeaker(String l) {
		lang = l;
	}
	
	/* (non-Javadoc)
	 * @see pi.project.voice.Speaker#say(java.lang.String)
	 */
	@Override
	public void say(String toSay) {
		int fileNum = 0;
		for (String s : cut(conform(toSay))) {
			 
			 try {
				download(
						 BASE_URL + "?tl=" + lang + "&q=" + s,
						 TEMP_PATH+"part"+fileNum++ + ".mp3");
			} catch (IOException e) {
				Logger.w("Impossible de télécharger le fichier à lire de " +
						"l'API de google" + e.getMessage());
			}
		}
		
		Player p = null;
		File f = null;
		for (int i =0; i < fileNum; i++) {
			f = new File(TEMP_PATH+"part"+i+".mp3");
			try {
				p = new Player(new FileInputStream(f));
			} catch (FileNotFoundException | JavaLayerException e) {
				Logger.w("Impossible d'accéder au fichier : " + e.getMessage());
			}
			
			try {
				p.play();
			} catch (JavaLayerException e) {
				Logger.w("Impossible de lire le fichier audio : " +
																e.getMessage());
			}
			
			f.delete();
		}
		
		
	}
	
	/**
	 * Requête de téléchargement, variant de la lib WebPageAcces pour pouvoir
	 *  spoofer le user agent sinon le web service refuse l'accès
	 * @param url L'adresse de ce qu'il faut télécharger
	 * @param path L'endroit ou stocké le fichier téléchargé
	 * @throws IOException En cas de problème
	 */
	private void download(String url, String path) throws IOException {

		URLConnection uc = new URL(url).openConnection();
		uc.setRequestProperty("User-Agent", "Mozilla");
		
		InputStream stream =  uc.getInputStream();
		
		new File("/tmp/LibVoice").mkdirs();
		
		File f = new File(path);
		f.createNewFile();
		
		FileOutputStream fw = new FileOutputStream(f);
		
		int currentByte;
		
		while ((currentByte = stream.read()) != -1) {
			fw.write(currentByte);
		}
		
		stream.close();
		fw.close();
	}

	/**
	 * Transforme la chaîne de manière à ce qu'elle soit utilisable dans une 
	 * requête HTTP.
	 * @param s La chaîne à modifier
	 * @return Une chaîne conforme
	 */
	private String conform(String s) {
		return s.replace(" ", "+");		
	}
	
	/**
	 * L'api de google n'autorise pas plus de 100 caractères dans les requêtes
	 *  il faut donc par fois faire plusieurs requêtes.
	 * @param s La chaîne à découper
	 * @return Une liste dont chaque élément doit être utiliser dans une requête
	 */
	private List<String> cut(String s) {
		List<String> list = new ArrayList<>();
		list.add(s);
		
		int currentListIndex = 0;

		while (list.get(currentListIndex).length() > 100) {
			int currentStringIndex;
			for (currentStringIndex = 99; s.charAt(currentStringIndex) != '+';
													currentStringIndex--);
			list.add(list.get(currentListIndex).
					substring(currentStringIndex, 
							list.get(currentListIndex).length()));
			
			list.set(currentListIndex, list.get(currentListIndex).
					substring(0, currentStringIndex));
			currentListIndex++;
		}
			
		
		return list;
	}
}
