/*
 * MeteoAPI
 * pi.project.meteo                      10 janv. 2013
 * Meteo.java
 */
package pi.project.meteo;

/**
 * Représentation mémoire d'une météo, contient toute les informations
 * dispensé par l'API de Yahoo.
 * Il s'agit d'un instantanée, de la météo que Yahoo donne,
 *  au moment de la requête, ne gère pas encore la météo des jours à venir.
 * Cette API à besoin d'une connexion Internet active pour fonctionner.
 * @author Maxime Raynal
 *
 */
public class Meteo {

	/**
	 * La ville d'ou vient la météo
	 */
	private String city;
	
	/**
	 * Le pays dans lequel se trouve la ville 
	 */
	private String country;
	
	/**
	 * Le code postal de la ville
	 */
	private int codePoste;
	
	/**
	 * L'identifiant unique de la ville dans l'API
	 */
	private String woeid;
	
	//Les unitées
	
	/**
	 * L'unitée de température (C ou F)
	 */
	private String uTemperature;
	
	/**
	 * L'unitée de distance
	 */
	private String uDistance;
	
	/**
	 * L'unitée de pression
	 */
	private String uPressure;
	
	/**
	 * L'unitée de distance
	 */
	private String uSpeed;
	
	//Les données relative au vent
	
	/**
	 * Le refroidissement occasionné par le ventS
	 */
	private int chill;
	
	/**
	 * La direction du vent en degré
	 */
	private int windDirection;
	
	/**
	 * La vitesse du vent
	 */
	private float windSpeed;

	//Les informations atmosphérique
	
	/**
	 * L'humiditée
	 */
	private int humidity;
	
	/**
	 * La visibilité (en centième de l'unitée de distance)
	 */
	private float visibility;
	
	/**
	 * La pression atmosphérique
	 */
	private float pressure;
	
	/**
	 * Les variations de pression :  constant, 1 élévation, 2 chute
	 */
	private int pressureRising;
	
	//Les données astronomique
	
	/**
	 * Heure de levé du soleil
	 */
	private String sunrise;
	
	/**
	 * L'heure du couché de soleil
	 */
	private String sunset;
	
	//Les conditions métérologique
	
	/**
	 * Le "temps" qu'il fait en français (traduction provenant de l'api)
	 */
	private String weatherCondition;
	
	/**
	 * Le code météo de Yahoo
	 */
	private int weatherCode;
	
	/**
	 * La température en degré (Celsius ou F)
	 */
	private int temperature;
	
	/**
	 * Constructeur de base à partir du WOEID de yahoo
	 * @param woeid L'identifiant de ville de Yahoo
	 */
	public Meteo(String woeid) {
		this.woeid = woeid;
	}
	
	/**
	 * Constructeur surcharger, se charge lui même de récupérer le WOEID
	 * @param city Le nom de la ville
	 * @param country Le pays ou se trouve la ville
	 */
	public Meteo(String city, String country) {
		this.city = city;
		this.country = country;
		
		//TODO récupérer le woeid
	}

	/**
	 * Constructeur surcharger, se charge lui même de récupérer le WOEID
	 * @param city Le nom de la ville
	 * @param codePoste Le code postal de la ville, lève l'ampbiguité dans le 
	 *  cas de plusieurs ville avec le même nom
	 */
	public Meteo(String city, int codePoste) {
		super();
		this.city = city;
		this.codePoste = codePoste;
	}

	/**
	 * @return the woeid
	 */
	public String getWoeid() {
		return woeid;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the codePoste
	 */
	public int getCodePoste() {
		return codePoste;
	}

	/**
	 * @param codePoste the codePoste to set
	 */
	public void setCodePoste(int codePoste) {
		this.codePoste = codePoste;
	}

	/**
	 * @return the uTemperature
	 */
	public String getuTemperature() {
		return uTemperature;
	}

	/**
	 * @param uTemperature the uTemperature to set
	 */
	public void setuTemperature(String uTemperature) {
		this.uTemperature = uTemperature;
	}

	/**
	 * @return the uDistance
	 */
	public String getuDistance() {
		return uDistance;
	}

	/**
	 * @param uDistance the uDistance to set
	 */
	public void setuDistance(String uDistance) {
		this.uDistance = uDistance;
	}

	/**
	 * @return the uPressure
	 */
	public String getuPressure() {
		return uPressure;
	}

	/**
	 * @param uPressure the uPressure to set
	 */
	public void setuPressure(String uPressure) {
		this.uPressure = uPressure;
	}

	/**
	 * @return the uSpeed
	 */
	public String getuSpeed() {
		return uSpeed;
	}

	/**
	 * @param uSpeed the uSpeed to set
	 */
	public void setuSpeed(String uSpeed) {
		this.uSpeed = uSpeed;
	}

	/**
	 * @return the chill
	 */
	public int getChill() {
		return chill;
	}

	/**
	 * @param chill the chill to set
	 */
	public void setChill(int chill) {
		this.chill = chill;
	}

	/**
	 * @return the windDirection
	 */
	public int getWindDirection() {
		return windDirection;
	}

	/**
	 * @param windDirection the windDirection to set
	 */
	public void setWindDirection(int windDirection) {
		this.windDirection = windDirection;
	}

	/**
	 * @return the windSpeed
	 */
	public float getWindSpeed() {
		return windSpeed;
	}

	/**
	 * @param windSpeed the windSpeed to set
	 */
	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}

	/**
	 * @return the humidity
	 */
	public int getHumidity() {
		return humidity;
	}

	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	/**
	 * @return the visibility
	 */
	public float getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(float visibility) {
		this.visibility = visibility;
	}

	/**
	 * @return the pressure
	 */
	public float getPressure() {
		return pressure;
	}

	/**
	 * @param pressure the pressure to set
	 */
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}

	/**
	 * @return the pressureRising
	 */
	public int getPressureRising() {
		return pressureRising;
	}

	/**
	 * @param pressureRising the pressureRising to set
	 */
	public void setPressureRising(int pressureRising) {
		this.pressureRising = pressureRising;
	}

	/**
	 * @return the sunrise
	 */
	public String getSunrise() {
		return sunrise;
	}

	/**
	 * @param sunrise the sunrise to set
	 */
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	/**
	 * @return the sunset
	 */
	public String getSunset() {
		return sunset;
	}

	/**
	 * @param sunset the sunset to set
	 */
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

	/**
	 * @return the weatherCondition
	 */
	public String getWeatherCondition() {
		return weatherCondition;
	}

	/**
	 * @param weatherCondition the weatherCondition to set
	 */
	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}

	/**
	 * @return the weatherCode
	 */
	public int getWeatherCode() {
		return weatherCode;
	}

	/**
	 * @param weatherCode the weatherCode to set
	 */
	public void setWeatherCode(int weatherCode) {
		this.weatherCode = weatherCode;
	}

	/**
	 * @return the temperature
	 */
	public int getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

}
