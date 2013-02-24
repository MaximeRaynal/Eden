/*
 * ObjectDistributionSystem
 * pi.project.objectDistribution.serveur                      17 févr. 2013
 * ShippableObjectFactoryJsonImpl.java
 */
package pi.project.objectDistribution.serveur;

import com.google.gson.Gson;

import pi.project.objectDistribution.bean.ObjectFactory;
import pi.project.objectDistribution.data.Request;

/**
 * Implementation JSON de la fabrique abstraite. Transforme les bean en chaîne
 *  JSON et renvoi la chaîne. 
 * @author Maxime Raynal
 *
 */
public class ShippableObjectFactoryJsonImpl implements ShippableObjectFactory {

	/* (non-Javadoc)
	 * @see pi.project.objectDistribution.serveur.ShippableObjectFactory#
	 * getPackagedObject(pi.project.objectDistribution.data.Service, 
	 * 					 pi.project.objectDistribution.data.Request)
	 */
	@Override
	public String getPackagedObject(Request req) {
		
		ObjectFactory factory = null;
		
		switch (req.getService()) {
		case LOG_SERVICE:
			factory = new LogFactoryImplJson();
			break;
		default:
			break;
		}		
		return new Gson().toJson(factory.build(req.getContent()));
	}

}
