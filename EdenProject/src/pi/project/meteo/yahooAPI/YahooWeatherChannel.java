/*
 * MeteoAPI
 * pi.project.meteo                      10 janv. 2013
 * WeatherChannel.java
 */
package pi.project.meteo.yahooAPI;

import java.io.IOException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import pi.project.meteo.Meteo;
import pi.project.meteo.WeatherChannel;





/**
 * Classe servant à la récupération des information métrologique provenant de 
 *  l'API de Yahoo 
 * @author Maxime Raynal
 *
 */
public class YahooWeatherChannel implements WeatherChannel {
	
	/**
	 * L'url de base (sans paramètre) d'accès à l'API de Yahoo
	 */
	public final static String BASE_URL = 
			"http://weather.yahooapis.com/forecastrss";
	
	/**
	 * Définit si l'on doit utilisé le système métrique ou impérial, vaut vrai
	 *  par défaut. 
	 */
	private boolean metricSystemUnit;

	/**
	 * Constructeur de base, active par défaut l'utilisation du système métrique
	 */
	public YahooWeatherChannel() {
		setMetricSystemUnit(true);
	}
	
	/**
	 * On définit lors de la création si on utilise le système métrique ou pas
	 * @param s True si on veut utiliser le système métrique
	 */
	public YahooWeatherChannel(boolean s) {
		setMetricSystemUnit(s);
	}
	
	/**
	 * @return the metricSystemUnit
	 */
	public boolean isMetricSystemUnit() {
		return metricSystemUnit;
	}

	/**
	 * @param metricSystemUnit the metricSystemUnit to set
	 */
	public void setMetricSystemUnit(boolean metricSystemUnit) {
		this.metricSystemUnit = metricSystemUnit;
	}

	/*
	 * (non-Javadoc)
	 * @see pi.project.meteo.WeatherChannel#meteoAt(pi.project.meteo.Meteo)
	 */
	@Override
	public Meteo meteoAt(Meteo city) {

		try {
			parse(city, new SAXBuilder().build(new URL((
					BASE_URL + "?w=" + city.getWoeid() + "&u=" +
											(metricSystemUnit ? "c" : "f")))));
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		return city;
	}
	
	/**
	 * Transfert le contenu du flux dans un fichier du type météo
	 * @param city L'objet à remplir
	 * @param doc Le flux à parser
	 */
	private void parse(Meteo city, Document doc) {
		Element channel = doc.getRootElement().getChild("channel");
		Namespace nsY = Namespace.getNamespace(
									"http://xml.weather.yahoo.com/ns/rss/1.0");
		Element e = channel.getChild("location",nsY);
		System.out.println(e.getValue());
		city.setCity(e.getAttributeValue("city"));
		city.setCountry(e.getAttributeValue("country"));
		
		e = channel.getChild("units", nsY);
		
		city.setuTemperature(e.getAttributeValue("temperature"));
		city.setuDistance(e.getAttributeValue("distance"));
		city.setuPressure(e.getAttributeValue("pressure"));
		city.setuSpeed(e.getAttributeValue("speed"));
		
		e = channel.getChild("wind", nsY);
		
		city.setChill(Integer.parseInt(e.getAttributeValue("chill")));
		city.setWindDirection(Integer.parseInt(e.getAttributeValue("direction")));
		city.setWindSpeed(Float.parseFloat(e.getAttributeValue("speed")));
		
		e = channel.getChild("atmosphere", nsY);
		
		city.setHumidity(Integer.parseInt(e.getAttributeValue("humidity")));
		city.setVisibility(Float.parseFloat(e.getAttributeValue("visibility")));
		city.setPressure(Float.parseFloat(e.getAttributeValue("pressure")));
		city.setPressureRising(Integer.parseInt(e.getAttributeValue("rising")));
		
		e = channel.getChild("astronomy", nsY);
		
		city.setSunrise(e.getAttributeValue("sunrise"));
		city.setSunset(e.getAttributeValue("sunset"));
		
		channel = channel.getChild("item");
		
		e = channel.getChild("condition", nsY);
		
		city.setWeatherCondition(e.getAttributeValue("text"));
		city.setWeatherCode(Integer.parseInt(e.getAttributeValue("code")));
		city.setTemperature(Integer.parseInt(e.getAttributeValue("temp")));
	}

	/**
	 * Retourne un objet meteo mais directement à partir du WOEID
	 *  retourne null si le WOEID n'éxiste pas
	 * @param woeid L'identifiant yahoo de la ville
	 * @return Un objet de type météo contenant les informations demandé ou 
	 *  null, si le WOEID n'éxiste pas.
	 */
	public Meteo meteoAt(String woeid) {
		return meteoAt(new Meteo(woeid));
	}
}
