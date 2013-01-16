/*
 * LibMeteo
 * pi.project.meteo                      11 janv. 2013
 * WeatherChannel.java
 */
package pi.project.meteo;

/**
 * Définit l'interfaçage de base pour l'utilisation des API météo
 * @author Maxime Raynal
 *
 */
public interface WeatherChannel {
	
	/**
	 * Charge dans un objet bean de type Meteo les informations voulu.
	 * @param city Le bean dans lequel on veut charger les informations
	 * @return Le bean chargé
	 */
	Meteo meteoAt(Meteo city);

}
