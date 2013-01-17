/*
 * zSandbox
 * pi.project.sandbox.sound                      14 janv. 2013
 * SpeechRecognitionAnswer.java
 */
package pi.project.voice.googleAPI.recognize;

import java.util.List;

/**
 * Classe de type bean, représente un retour JSON de l'API speech recognition
 * de google
 * @author Maxime Raynal
 *
 */
public class SpeechRecognitionAnswer {
	
	/**
	 * Le statut de la réponse
	 */
	private int status;
	
	/**
	 * L'identifiant de la réponse
	 */
	private String id;
	
	/**
	 * La liste d'hypothèse de phrase
	 */
	List<Hypotheses> hypotheses;

	/**
	 * @param status
	 * @param id
	 * @param hypotheses
	 */
	public SpeechRecognitionAnswer(int status, String id,
			List<Hypotheses> hypotheses) {
		this.status = status;
		this.id = id;
		this.hypotheses = hypotheses;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the hypotheses
	 */
	public List<Hypotheses> getHypotheses() {
		return hypotheses;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param hypotheses the hypotheses to set
	 */
	public void setHypotheses(List<Hypotheses> hypotheses) {
		this.hypotheses = hypotheses;
	}
	
	/**
	 * Retourne la meilleures hypothèses
	 * @return Une hypothèse
	 */
	public String getBetterHypothese() {
		Hypotheses better = hypotheses.get(0);
		
		for (Hypotheses h : hypotheses) {
			if (h.getConfidence() > better.getConfidence()) {
				better = h;
			}
		}
		
		return better.getUtterance();
	}

	/**
	 * Représente une hypothèse de reconnaissance
	 * @author Maxime Raynal
	 */
	class Hypotheses {

		/**
		 * Le retour
		 */
		private String utterance;
		
		/**
		 * La probabilité de retour
		 */
		private float confidence;

		/**
		 * @param utterance
		 * @param confidence
		 */
		public Hypotheses(String utterance, float confidence) {
			this.utterance = utterance;
			this.confidence = confidence;
		}
		
		/**
		 * @return the utterance
		 */
		public String getUtterance() {
			return utterance;
		}

		/**
		 * @return the confidence
		 */
		public float getConfidence() {
			return confidence;
		}

		/**
		 * @param utterance the utterance to set
		 */
		public void setUtterance(String utterance) {
			this.utterance = utterance;
		}

		/**
		 * @param confidence the confidence to set
		 */
		public void setConfidence(float confidence) {
			this.confidence = confidence;
		}
	}
}