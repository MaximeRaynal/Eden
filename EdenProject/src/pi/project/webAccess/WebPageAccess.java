/*
 * LibGetWebPage
 * pi.project.webAccess                      9 janv. 2013
 * WebPageDownloader.java
 */

package pi.project.webAccess;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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
	 * Récupération d'une page par l'url avec les paramètre à part dans une Map
	 *  par la méthode GET. Permet de spécifier des paramètre supplémentaire
	 *  pour la requête (ex: User-Agent)
	 * @param url L'adresse de téléchargement de la page
	 * @param param Les paramètre GET de la page
	 * @param requestParam Les paramètre de la requête
	 * @return Le contenu de la page
	 * @throws IOException Problème avec le flux Web
	 * @throws MalformedURLException Url invalide
	 */
	public static String get(String url, Map<String, String> param, 
									Map<String, String> requestParam) 
									throws MalformedURLException, IOException {
		
		StringBuilder fullUrl = new StringBuilder(url + "?");
		
		for (String s : param.keySet()) {
			fullUrl.append(s + "=" +param.get(s) + "&");
		}
		
		URLConnection uc = new URL(fullUrl.toString()).openConnection();
		
		for (String s : requestParam.keySet()) {
			uc.setRequestProperty(s,requestParam.get(s));
		}
		
		Scanner sc = new Scanner(uc.getInputStream());
		
		StringBuilder page = new StringBuilder();
		while (sc.hasNext()) {
			page.append(sc.nextLine());
		}
		
		sc.close();
		return page.toString();	
	}
	
	/**
	 * Récupère le contenu d'une page par la méthode GET 
	 *  et l'enregistre dans un ficher.
	 * Supporte le téléchargement de fichier binaires.
	 * @param url L'adresse a effectuer
	 * @param path Le chemin vers le fichier à stocker
	 * @throws IOException Problème avec le flux Web
	 * @throws MalformedURLException Url invalide
	 */
	public static void download(String url, String path) 
									throws MalformedURLException, IOException {

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
	 * Récupère un fichier en provenance d'un serveur, par le protocole HTTP,
	 * méthode GET. Permet de spécifier les paramètre de l'URL dans une map.
	 * @param url L'url à accéder
	 * @param path L'endroit ou enregistrer le retour
	 * @param param Les paramètre de l'url
	 * @throws IOException Problème avec le flux Web
	 * @throws MalformedURLException Url invalide
	 */
	public static void download(String url, String path, 
			Map<String, String> param) throws MalformedURLException, IOException {
		
		StringBuilder fullUrl = new StringBuilder(url + "?");
		
		for (String s : param.keySet()) {
			fullUrl.append(s + "=" +param.get(s) + "&");
		}
		
		download(fullUrl.toString(), path);
		
	}
	
	/**
	 * Récupération d'une page par l'url avec les paramètre à part dans une Map
	 *  par la méthode GET. Permet de spécifier des paramètre supplémentaire
	 *  pour la requête (ex: User-Agent)
	 * @param url L'adresse de téléchargement de la page
	 * @param path Le chemin vers le fichier à enregistrer
	 * @param param Les paramètre GET de la page
	 * @param requestParam Les paramètre de la requête
	 * @throws IOException Problème avec le flux Web
	 * @throws MalformedURLException Url invalide
	 */
	public static void download(String url, String path, 
			Map<String, String> param, 	Map<String, String> requestParam) 
									throws MalformedURLException, IOException {
		
		StringBuilder fullUrl = new StringBuilder(url + "?");
		
		for (String s : param.keySet()) {
			fullUrl.append(s + "=" +param.get(s) + "&");
		}
		
		URLConnection uc = new URL(fullUrl.toString()).openConnection();
		
		for (String s : requestParam.keySet()) {
			uc.setRequestProperty(s,requestParam.get(s));
		}
		
		InputStream stream =  uc.getInputStream();
		
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
     * Récupération d'une page par l'url avec les paramètre à par dans une Map,
     * par la méthode POST
	 * @param url L'adresse de téléchargement de la page
	 * @param param Les paramètre GET de la page
	 * @param requestParam 
	 * @return Le contenu de la page
	 * @throws IOException Problème avec le flux Web
	 * @throws MalformedURLException Url invalide
	 */
	public static String post(String url, Map<String, String> param,
									Map<String, String> requestParam) 
									throws MalformedURLException, IOException {

			StringBuilder data = new StringBuilder();
			
			for (String s : param.keySet()) {
				data.append(URLEncoder.encode(s + "=" +param.get(s) +
																"&", "UTF-8"));
			}
			
			HttpURLConnection con = 
							(HttpURLConnection) new URL(url).openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			
			for (String s : requestParam.keySet()) {
				con.setRequestProperty(s,requestParam.get(s));
			}
			
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
	
	/**
	 * Upload un fichier vers un serveur. Protocol HTTP, méthode POST
	 * @param url L'URL vers laquelle uploader le fichier
	 * @param path Le chemin vers le fichier à charger
	 * @param urlParam Les paramètre de l'URL
	 * @param reqParam Les paramètre de la requête
	 * @return Ce que renvoi la page
	 * @throws MalformedURLException L'URL est invalide
	 * @throws IOException Problème dans la gestion des flux
	 */
	public static String uploadSimpleFile(String url, String path,
			Map<String, String> urlParam, Map<String, String> reqParam) 
					throws MalformedURLException, IOException {
		
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		StringBuilder fullUrl = new StringBuilder(url + "?");
		
		for (String s : urlParam.keySet()) {
			fullUrl.append(s + "=" + urlParam.get(s) + "&");
		}
		
		URLConnection con = new URL(fullUrl.toString()).openConnection();
		
		for (String s : reqParam.keySet()) {
			con.setRequestProperty(s,reqParam.get(s));
		}
	
		StringBuilder data = new StringBuilder();

		con.setDoOutput(true);
			
		DataOutputStream dstream = new DataOutputStream(con.getOutputStream());
		
		int currentByte;
		
		while ((currentByte = file.read()) != -1) {
			dstream.write(currentByte);
		}
		
		file.close();
		
		Scanner sc = new Scanner(con.getInputStream());
		data = new StringBuilder();
		while (sc.hasNext()) {
			data.append(sc.nextLine());
		}
		sc.close();
		
		return data.toString();
	}
}