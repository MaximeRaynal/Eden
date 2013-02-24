/*
 * ObjectDistribution
 * pi.project.objectDistribution.data                      31 janv. 2013
 * Request.java
 */
package pi.project.objectDistribution.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import pi.project.log.Logger;

import com.google.gson.Gson;

/**
 * Objet symbolisant une requête
 * Une requête s'effectue sur une service,
 * Avec un message.
 * L'auteur de la requête doit être authentifier (pour l'instant l'
 *  authentification est le nom du service associé.
 * @author Maxime Raynal
 *
 */
public class Request {
	
	/**
	 * Chaîne d'identification du demandeur
	 */
	private String authentification;
	
	/**
	 * Le contenu de la requête
	 */
	private String content;
	
	/**
	 * Le service au quel on veut accéder
	 */
	private Service service;
	
	/**
	 * Constructeur complet de requête
	 * @param auth Chaîne d'authentification
	 * @param cont Contenu de la requête
	 * @param serv Le service au quel on veut accéder
	 */
	public Request(String auth, String cont, Service serv) {
		authentification = auth;
		content = cont;
		service = serv;
	}
	
	/**
	 * @return the authentification
	 */
	public String getAuthentification() {
		return authentification;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return the service
	 */
	public Service getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(Service service) {
		this.service = service;
	}

	/**
	 * @param authentification the authentification to set
	 */
	public void setAuthentification(String authentification) {
		this.authentification = authentification;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Retourne la requête sous forme JSON pour la transmission.
	 * @return Représentation de la requête sous forme JSON
	 */
	public String toJson() {
		return new Gson().toJson(this);	
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Authentification string : " + authentification + "\n" +
				"Content : " + content;	
	}
	
	/**
	 * Envoi la requête au serveur et retourne la réponse.
	 * @return La réponse contenant l'objet formaté
	 */
	public Reponse send() {
		//Connexion au serveur
		String objectParsed = null;
		try {
			Socket s = new Socket("eden-raspberry", 10298);
			new OutputStreamWriter(s.getOutputStream()).write(toJson());
			
			objectParsed = new BufferedReader(
					new InputStreamReader(s.getInputStream())).readLine();
		    s.close();
		} catch (UnknownHostException e) {
			Logger.e("Le serveur n'a pas été trouvé. " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Logger.e("Problème de connexion réseaux. " + e.getMessage());
			e.printStackTrace();
		}
		return new Gson().fromJson(objectParsed, Reponse.class);
	}
}
