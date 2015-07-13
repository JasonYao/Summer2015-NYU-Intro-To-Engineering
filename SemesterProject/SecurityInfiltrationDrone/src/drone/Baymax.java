/**
 * 
 */
package drone;

import lejos.robotics.RegulatedMotor;
import lejos.utility.PilotProps;
//import lejos.robotics.RegulatedMotor;

/**
 * @author Jason Yao
 *
 */
public class Baymax
{
	// Independently controlled motors
	static RegulatedMotor leftMotor;
	static RegulatedMotor rightMotor;

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			// Welcome Message
			introMessage();
			
			// Configuration
			
			// 
			// TODO Auto-generated method stub

		}
		catch (Exception e)
		{System.err.println("WHAT THE FUCK DID YOU DO WHY IS THIS ON FIRE");}
		finally
		{
			
		} // End of finally block
	} // End of the main method
	
	private static void introMessage()
	{
		
	}

} // End of the Baymax Class
