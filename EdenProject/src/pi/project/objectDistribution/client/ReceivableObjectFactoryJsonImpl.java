/*
 * ObjectDistributionSystem
 * pi.project.objectDistribution.client                      24 févr. 2013
 * ReceivableObjectFactoryJsonImpl.java
 */
package pi.project.objectDistribution.client;

import com.google.gson.Gson;

import pi.project.objectDistribution.data.Reponse;
import pi.project.objectDistribution.data.ReponseState;

/**
 * @author maxime
 *
 */
public class ReceivableObjectFactoryJsonImpl implements ReceivableObjectFactory {

	/* (non-Javadoc)
	 * @see pi.project.objectDistribution.client.ReceivableObjectFactory#
	 * restituteObject(pi.project.objectDistribution.data.Reponse, 
	 * java.lang.Class)
	 */
	@Override
	public <T> T restituteObject(Reponse rep, Class<T> type) {
	
		if (rep.getStatus() != ReponseState.OK) {
			return null;
		}
		
		return new Gson().fromJson(rep.getContent(), type);
	}

}
