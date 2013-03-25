/*
 * EdenProject
 * pi.project.sandbox                      16 mars 2013
 * TestYaml.java
 */
package pi.project.sandbox;

import java.io.FileWriter;
import java.io.IOException;

import org.yaml.snakeyaml.Yaml;

import pi.project.config.ConfigurationBean;

/**
 * @author maxime
 *
 */
public class TestYaml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Yaml c = new Yaml();
		ConfigurationBean conf = new ConfigurationBean();
		conf.setDirectoryFilePath("xml/config/directory.xml");
		conf.setUsedAPIFilePath("api.yml");
		conf.setBaseDirectoryPath("/home/maxime/git/EdenProject/");
		try {
			c.dump(conf, new FileWriter("main_conf.yaml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
