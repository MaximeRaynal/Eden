/*
 * InterfaceGPIO
 * pi.project.gpio                      21 janv. 2013
 * LogicPinName.java
 */
package pi.project.gpio;

/**
 * Liste des différent nom logique des pins
 * @author Maxime Raynal
 *
 */
public enum LogicPinName {

	/**
	 * Pin d'alimentation 3.3V
	 */
	POWER_3_3V,
	
	/**
	 * Pin d'alimentation 5V
	 */
	POWER_5V,
	
	/**
	 * 
	 */
	SDA0,
	
	/**
	 * 
	 */
	SCL0,
	
	/**
	 * Pin de masse
	 */
	GROUND,
	
	/**
	 * Pin GPIO 0
	 */
	GPIO_0,
	
	/**
	 * Pin GPIO 1
	 */
	GPIO_1,
	
	/**
	 * Pin GPIO 2
	 */
	GPIO_2,
	
	/**
	 * Pin GPIO 3
	 */
	GPIO_3,
	
	/**
	 * Pin GPIO 4
	 */
	GPIO_4,
	
	/**
	 * Pin GPIO 5
	 */
	GPIO_5,
	
	/**
	 * Pin GPIO 6
	 */
	GPIO_6,
	
	/**
	 * Pin GPIO 7
	 */
	GPIO_7,
	
	/**
	 * 
	 */
	TxD,
	
	/**
	 * 
	 */
	RxD,
	
	/**
	 * 
	 */
	MOSI,
	
	/**
	 * 
	 */
	MISO,
	
	/**
	 * 
	 */
	SCLK,
	
	/**
	 * 
	 */
	CEO,
	
	/**
	 * 
	 */
	CE1,
	
	/**
	 * 
	 */
	DNC;

	private LogicPinName() {
	
	}
	
	/**
	 * Retourne la correspondance en numéro physique de pin
	 * @return Le numéro physique du pin correspondant, Le 3 si il s'agit d'un 
	 *                                DNC
	 */
	public PhysicPinNumber toPhysicNumber() {
		PhysicPinNumber number = null;
		
		switch (this) {
		case POWER_3_3V:
			number = PhysicPinNumber.ONE;
			break;
			
		case POWER_5V:
			number = PhysicPinNumber.TWO;
			break;
			
		case SDA0:
			number = PhysicPinNumber.THREE;
			break;
			
		case SCL0:
			number = PhysicPinNumber.FIVE;
			break;
			
		case GROUND:
			number = PhysicPinNumber.SIX;
			break;
			
		case GPIO_7:
			number = PhysicPinNumber.SEVEN;
			break;
			
		case TxD:
			number = PhysicPinNumber.HEIGHT;
			break;
			
		case RxD:
			number = PhysicPinNumber.TEN;
			break;
			
		case GPIO_0:
			number = PhysicPinNumber.ELEVEN;
			break;
			
		case GPIO_1:
			number = PhysicPinNumber.TWELVE;
			break;
			
		case GPIO_2:
			number = PhysicPinNumber.THIRTEEN;
			break;
			
		case GPIO_3:
			number = PhysicPinNumber.FIFTEEN;
			break;
			
		case GPIO_4:
			number = PhysicPinNumber.SIXTEEN;
			break;
			
		case GPIO_5:
			number = PhysicPinNumber.HEIGHTEEN;
			break;
			
		case MOSI:
			number = PhysicPinNumber.NINETEEN;
			break;
			
		case MISO:
			number = PhysicPinNumber.TWENTY_ONE;
			break;
			
		case GPIO_6:
			number = PhysicPinNumber.TWENTY_TWO;
			break;

		case SCLK:
			number = PhysicPinNumber.TWENTY_THREE;
			break;
			
		case CEO:
			number = PhysicPinNumber.TWENTY_FOUR;
			break;
			
		case CE1:
			number = PhysicPinNumber.TWENTY_SIX;

		default:
			number = PhysicPinNumber.THREE;
			break;
		}
		
		return number;
	}
	
	/**
	 * Renvoi le numéro du pin GPIO, -1 si ce n'est pas un pin GPIO
	 * @return Renvoi le numéro du pin GPIO, -1 si ce n'est pas un pin GPIO
	 */
	public int toGPIONumber() {
		int n = 0;
		
		switch (this) {
		case GPIO_0:
			n = 0;
			break;
			
		case GPIO_1:
			n = 1;
			break;
			
		case GPIO_2:
			n = 2;
			break;
			
		case GPIO_3:
			n = 3;
			break;
			
		case GPIO_4:
			n = 4;
			break;
			
		case GPIO_5:
			n = 5;
			break;
			
		case GPIO_6:
			n = 6;
			break;
			
		case GPIO_7:
			n = 7;
			break;
			
		default:
			n = -1;
			break;
		}
		return n;
	}
}
