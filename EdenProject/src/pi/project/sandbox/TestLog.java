/*
 * EdenProject
 * pi.project.sandbox                      25 mars 2013
 * TestLog.java
 */
package pi.project.sandbox;

import pi.project.log.Logger;

/**
 * @author maxime
 *
 */
public class TestLog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger.e("Un Log d'erreur");
		Logger.w("Un log d'avertissement");
		Logger.i("Un log d'information");

	}

}
