/*
 * LibGetWebPage
 * pi.project.webAccess                      9 janv. 2013
 * WebPageDownloader.java
 */

package pi.project.webAccess;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;



/**
 * Classe de gestion simple, permettant de récupérer le contenu d'une page Web
 * par le protocole HTTP par les méthode GET ou POST
 * @author Maxime Raynal
 */
public class WebPageAccess {
	
	/**
	 * Récupération d'une page par l'url simple par la méthode GET
	 * @param url L'adresse de téléchargement de la page
	 * @return Le contenu de la page
	 * @throws IOException Problème avec le flux Web
	 * @throws MalformedURLException Url invalide
	 */
	public static String get(String url) throws MalformedURLException,
																   IOException {
		
		Scanner sc = new Scanner(
				new URL(url).openConnection().getInputStream());
		
		StringBuilder page = new StringBuilder();
		while (sc.hasNext()) {
			page.append(sc.nextLine());
		}
		
		sc.close();
		return page.toString();	
	}
	
	/**
	 * Récupère le contenu d'une page par la méthode GET 
	 *  et l'enregistre dans un ficher
	 * @param url L'adresse a effectuer
	 * @param path Le chemin vers le fichier à stocker
	 * @throws IOException 
	 * @throws MalformedURLException
	 */
	public static void download(String url, String path) 
									throws MalformedURLException, IOException {

		//URLConnection uc = new URL(url).openConnection();
		//uc.setRequestProperty("User-Agent", "Mozilla");
		
		InputStream stream =  new URL(url).openConnection().getInputStream();
		
		
		
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
	 * Récupération d'une page par l'url avec les paramètre à par dans une Map
	 * par la méthode GET
	 * @param url L'adresse de téléchargement de la page
	 * @param param Les paramètre GET de la page
	 * @return Le contenu de la page
	 * @throws IOException Problème avec le flux Web
	 * @throws MalformedURLException Url invalide
	 */
	public static String get(String url, Map<String, String> param)
									 throws MalformedURLException, IOException {
		
		StringBuilder fullUrl = new StringBuilder(url + "?");
		
		for (String s : param.keySet()) {
			fullUrl.append(s + "=" +param.get(s) + "&");
		}
		
		return WebPageAccess.get(fullUrl.deleteCharAt(fullUrl.length()-1)
				.toString());
	}
	
	
	/**
     * Récupération d'une page par l'url avec les paramètre à par dans une Map,
     * par la méthode POST
	 * @param url L'adresse de téléchargement de la page
	 * @param param Les paramètre GET de la page
	 * @return Le contenu de la page
	 * @throws IOException Problème avec le flux Web
	 * @throws MalformedURLException Url invalide
	 */
	public static String post(String url, Map<String, String> param) 
									throws MalformedURLException, IOException {

			StringBuilder data = new StringBuilder();
			
			for (String s : param.keySet()) {
				data.append(URLEncoder.encode(s + "=" +param.get(s) +
																"&", "UTF-8"));
			}
			
			URLConnection con = new URL(url).openConnection();
			con.setDoOutput(true);

			OutputStreamWriter w = 
					new OutputStreamWriter(con.getOutputStream());
			w.write(data.deleteCharAt(data.length()-1).toString());
			w.flush();

			
			Scanner sc = new Scanner(con.getInputStream());
			data = new StringBuilder();
			while (sc.hasNext()) {
				data.append(sc.nextLine());
			}
			sc.close();
			return data.toString();
	}
}