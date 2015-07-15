/**
 * 
 */
package drone;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * @author Jason Yao
 *
 */
public class Baymax extends DifferentialPilot
{
	/**
	 * @param wheelDiameter
	 * @param trackWidth
	 * @param leftMotor
	 * @param rightMotor
	 */
	public Baymax(double wheelDiameter, double trackWidth,
			RegulatedMotor leftMotor, RegulatedMotor rightMotor)
	{
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);
	}

	/**
	 * @param wheelDiameter
	 * @param trackWidth
	 * @param leftMotor
	 * @param rightMotor
	 * @param reverse
	 */
	public Baymax(double wheelDiameter, double trackWidth,
			RegulatedMotor leftMotor, RegulatedMotor rightMotor, boolean reverse)
	{
		super(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
	}

	/**
	 * @param leftWheelDiameter
	 * @param rightWheelDiameter
	 * @param trackWidth
	 * @param leftMotor
	 * @param rightMotor
	 * @param reverse
	 */
	public Baymax(double leftWheelDiameter, double rightWheelDiameter,
			double trackWidth, RegulatedMotor leftMotor,
			RegulatedMotor rightMotor, boolean reverse)
	{
		super(leftWheelDiameter, rightWheelDiameter, trackWidth, leftMotor,
				rightMotor, reverse);
	}

	public boolean isAtEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	public void move(MapNode currentNode) {
		// TODO Auto-generated method stub
		
	}

} // End of the Baymax class
