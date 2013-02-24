/*
 * ObjectDistribution
 * pi.project.objectDistribution.data                      31 janv. 2013
 * Reponse.java
 */
package pi.project.objectDistribution.data;

import java.lang.reflect.Type;

import com.google.gson.Gson;

/**
 * Réponse envoyé par le serveur suite à une requête, 
 *  contient un objet encapsulé au format JSON 
 * @author Maxime Raynal
 */
public class Reponse {
	
	/**
	 * Le status de la requête
	 */
	private ReponseState status;

	/**
	 * Le contenu de la réponse, au format JSON
	 */
	private String content;
	
	/**
	 * Le type de l'objet encapsulé
	 */
	private Type objectType;

	/**
	 * @param status
	 * @param content
	 * @param t 
	 */
	public Reponse(ReponseState status, String content, Type t) {
		this.status = status;
		this.content = content;
		objectType = t;
	}

	/**
	 * Construit une réponse à partir d'une chaîne JSON.
	 * @param s La chaîne JSON représentant la réponse
	 */
	public Reponse(String s) {
		content = new Gson().fromJson(s, getClass()).getContent();
		status = new Gson().fromJson(s, getClass()).getStatus();
	}
	
	/**
	 * @return the status
	 */
	public ReponseState getStatus() {
		return status;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ReponseState status) {
		this.status = status;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the objectType
	 */
	public Type getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(Type objectType) {
		this.objectType = objectType;
	}
}
