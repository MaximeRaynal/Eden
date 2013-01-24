/*
 * InterfaceGPIO
 * pi.project.gpio                      21 janv. 2013
 * PhysicPinNumber.java
 */
package pi.project.gpio;

/**
 * Représente les différents numéro physiques des pins
 * @author Maxime Raynal
 *
 */
public enum PhysicPinNumber {
	
	/**
	 * Premier pin : Alimentation 3.3V
	 */
	ONE,
	
	/**
	 * Deuxième pin : Alimentation 5V
	 */
	TWO,
	
	/**
	 * Troisième pin : SDA0
	 */
	THREE,
	
	/**
	 * Quatrième pin : DNC
	 */
	FOUR,
	
	/**
	 * Cinquième pin : SCL0
	 */
	FIVE,
	
	/**
	 * Sixième pin : GROUND
	 */
	SIX,
	
	/**
	 * Septième pin : GPIO 7
	 */
	SEVEN,
	
	/**
	 * Huitième pin : TxD
	 */
	HEIGHT,
	
	/**
	 * Neuvième pin : DNC
	 */
	NINE,
	
	/**
	 * Dixième pin : RxD
	 */
	TEN,
	
	/**
	 * Onzième pin : GPIO 0
	 */
	ELEVEN,
	
	/**
	 * Douzième pin : GPIO 1
	 */
	TWELVE,
	
	/**
	 * Treizième pin : GPIO 2
	 */
	THIRTEEN,
	
	/**
	 * Quatorzième pin : DNC
	 */
	FOURTEEN,
	
	/**
	 * Quinzième pin : GPIO 3
	 */
	FIFTEEN,
	
	/**
	 * Seizième pin : GPIO 4
	 */
	SIXTEEN,
	
	/**
	 * Dix-septième pin 
	 */
	SEVENTEEN,
	
	/**
	 * Dix-huitième pin :
	 */
	HEIGHTEEN,
	
	/**
	 * 
	 */
	NINETEEN,
	
	/**
	 * 
	 */
	TWENTY,
	
	/**
	 * 
	 */
	TWENTY_ONE,
	
	/**
	 * 
	 */
	TWENTY_TWO,
	
	/**
	 * 
	 */
	TWENTY_THREE,
	
	/**
	 * 
	 */
	TWENTY_FOUR,
	
	/**
	 * 
	 */
	TWENTY_FIVE,
	
	/**
	 * 
	 */
	TWENTY_SIX;
	
	private PhysicPinNumber() {
		
	}
	
	/**
	 * Retourne le nom logique équivalent au numéro
	 * @return Un nom logique de pin
	 */
	public LogicPinName toLogicName() {
		LogicPinName name = null;
		
		switch (this) {
		case ONE:
			
			break;

		default:
			break;
		}
		
		return name;
	}
}
