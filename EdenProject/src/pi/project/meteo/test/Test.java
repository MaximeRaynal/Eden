/*
 * MeteoAPI
 * pi.project.meteo.test                      11 janv. 2013
 * Test.java
 */
package pi.project.meteo.test;

import pi.project.meteo.Meteo;
import pi.project.meteo.yahooAPI.YahooWeatherChannel;

/**
 * @author maxime
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Meteo rodez = new Meteo("619742");
		YahooWeatherChannel ch = new YahooWeatherChannel();
		
		ch.meteoAt(rodez);
		
		System.out.println(rodez.getTemperature() + " " + rodez.getuTemperature());

	}

}
