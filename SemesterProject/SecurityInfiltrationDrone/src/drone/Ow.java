package drone;

/**
 * Package Imports
 */
import java.util.ArrayList;
import java.util.Stack;


/**
 * Lejos Imports
 */
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.utility.PilotProps;

/**
 * Runs the robot through a maze
 * @author Jason Yao
 */
public class Ow
{
	// Independently controlled motors
	private static RegulatedMotor leftMotor;
	private static RegulatedMotor rightMotor;
	private static boolean GLORIOUSSUCCESS = false; // Will be set to true when all objectives are complete
	private static int MAZE_MODE;

	/**
	 * Runs the robot through the maze
	 * @param args Undirected Graph if the graph beforehand is known
	 */
	public static void main(String[] args)
	{
		try
		{
			// TODO remove after confirmed working
			LCD.drawString("Plugin Test", 0, 4);
			Delay.msDelay(5000);

			//TODO remove after testing
			System.out.println("Got to checkpoint");
			System.exit(0);

			// Welcome Message
			introMessage();

			/* START OF CONFIGURATIONS */
			MAZE_MODE = 0;

			PilotProps pp = new PilotProps();
			pp.loadPersistentValues();
			float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "4.0"));
			float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "18.0"));

			System.out.println("Wheel diameter is " + wheelDiameter);
			System.out.println("Track width is " + trackWidth);

			leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "A"));
			rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "B"));
			boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE,"false"));

			Baymax baymax = new Baymax(wheelDiameter,trackWidth,leftMotor,rightMotor,reverse);
			/* END OF CONFIGURATIONS*/

			/* START OF TESTING SUITE */
			if (testMotors(baymax) == false)
				throw new BaymaxException("Error: Cannot move motors, Mochi is sitting on them");
			else
				System.out.println("Test Successful: Motors are green");
			if (testRotate(baymax) == false)
				throw new BaymaxException("Error: Cannot rotate in place, stomach in the way");
			else
				System.out.println("Test Successful: Rotate Motion is good");
			if (testForwardMovement(baymax) == false)
				throw new BaymaxException("Error: Cannot move forwards, need to let loose some air");
			else
				System.out.println("Test Successful: Forward Motion is good");
			if (testBackwardMovement(baymax) == false)
				throw new BaymaxException("Error: Cannot move backwards, there's a door in my way");
			else
				System.out.println("Test Successful: Backward Motion is good");
			/* END OF TESTING SUITE */

			/* START OF MAZE SOLVER */
			Balalalalalala(baymax, 0);
			/* END OF MAZE SOLVER*/

			/* START OF ITEM RETRIEVEL */
			
			/* END OF ITEM RETRIEVEL */
			System.out.println("Glorious Success");

		}
		//		catch (BaymaxException e)
		//		{System.err.print(e.getMessage());}
		catch (Exception e)
		{System.err.println("WHAT THE FUCK DID YOU DO WHY IS THIS ON FIRE");}
	} // End of the main method


	/**
	 * Wrapper helper method to determine which mode Baymax should operate in.
	 * Operates as a controller that is agnostic on the model and the view (action), in
	 * a similar vein to MVC.
	 * @param baymax The robot being passed in
	 * @param mode The mode Baymax is in, 0 for Unknown maze, 1 for Known maze
	 */
	private static void Balalalalalala(Baymax baymax, int mode) throws BaymaxException
	{
		if ((MAZE_MODE == 0))
		{
			// Maze is clearly defined, runs through with Trémaux's algorithm
			DFS(baymax);
		}
		else if (MAZE_MODE == 1)
		{
			// Maze is known beforehand, runs Dijkstra for shortest path, and executes
			dijkstra(baymax);
		}
		else
			throw new BaymaxException("Error: Invalid traversal mode selected");
	} // End of Balalalalala method

	/**
	 * 
	 * @param baymax
	 */
	private static void dijkstra(Baymax baymax)
	{
		// TODO Auto-generated method stub

	} // End of Disjkstra's Algorithm method

	/**
	 * 
	 * @param baymax
	 */
	private static void DFS(Baymax baymax)
	{
		// New DFS
		Stack<MapNode> movementStack = new Stack<MapNode>();
		
		MapNode currentNode = new MapNode(0,0);
		movementStack.push(currentNode);
		while (!movementStack.isEmpty() && GLORIOUSSUCCESS == false)
		{
			// Sets node if not source node
			currentNode = movementStack.pop();
			if (currentNode.getPositionX() == 0 && currentNode.getPositionY() == 0)
			{
				
			}
			
			// Goes forward if possible
			
			// Otherwise goes left
			
			// Otherwise goes right
			
			// Otherwise backtracks to the last node
		}
		
		
		
			// Sets node
			// Goes forward if possible
			// else Goes left
		
		
		
		// Old DFS
		ArrayList<Object> memoizedMovement = new ArrayList<Object>();
		//Stack<MapNode> movementStack = new Stack<MapNode>();
		//MapNode currentNode = new MapNode(0,0); // Initializes the source node to be at (0,0) in a cartesian plane
		movementStack.push(currentNode);

		while (!movementStack.isEmpty() && GLORIOUSSUCCESS == false)
		{
			currentNode = movementStack.pop();

			if (!(currentNode.getPositionX() == 0 && currentNode.getPositionY() == 0))
				baymax.move(currentNode);

			// Checks if its been discovered already
			if (!currentNode.isDiscovered())
			{
				currentNode.setDiscovered(true);

				if (baymax.isAtEnd())
				{
					GLORIOUSSUCCESS = true;
					break;
				}

				findChildren(currentNode); // This is going to look retarded but oh well

				for (int i = 0; i < currentNode.getChildren().size(); ++i)
					movementStack.push(currentNode.getChildren().get(i));
			}
		}
		// End of DFS


		//		
		//		TODO
		//		TODO
		//		TODO
		//		TODO
		//		
		//		
		//
		//    	// START OF BOILERPLATE
		//    	 // Wait for user to press ENTER
		//    	System.out.println("Waiting for press");
		//		Button.waitForAnyPress();
		//        baymax.setAcceleration(4000);
		//		baymax.setTravelSpeed(20); // cm/sec
		//		baymax.setRotateSpeed(180); // deg/sec
		//		System.out.println("Going forwards");
		//		baymax.forward();
		//		Delay.msDelay(1000);
		//		System.out.println("Stopping");
		//		baymax.stop();
		//		System.out.println("Stopped");
		//		showCount(baymax, 0);
		//		System.out.println("Going backwards");
		//		baymax.backward();
		//		Delay.msDelay(1000);
		//		baymax.stop();
		//		showCount(baymax, 1);
		//		System.out.println("Travel started");
		//		baymax.travel(10,true);
		//		System.out.println("Waiting for move to end");
		//		while(baymax.isMoving())Thread.yield();
		//		System.out.println("Move ended");
		//		showCount(baymax, 2);
		//		System.out.println("Travel back");
		//		baymax.travel(-10);
		//		System.out.println("Travel back ended");
		//		showCount(baymax, 3);
		//		System.out.println("Loop of rotates anti-clockwise");
		//		for(int i = 0; i<4; i++)
		//		{
		//			baymax.rotate(90);
		//		}
		//		showCount(baymax, 4);
		//		System.out.println("Loop of rotates clockwise");
		//		for(int i = 0; i<4; i++)
		//		{
		//			baymax.rotate(-90,true);
		//			while(baymax.isMoving())Thread.yield();
		//		}
		//		showCount(baymax, 5);
		//		System.out.println("Arcs backwards and forwards, anticlockwise and clockwise");
		//		baymax.steer(-50,180,true);
		//		while(baymax.isMoving())Thread.yield();
		//		baymax.steer(-50,-180);
		//		showCount(baymax, 6);
		//		baymax.steer(50,180);
		//		baymax.steer(50, -180);
		//		showCount(baymax, 7);
		//		System.out.println("Travel forwards");
		//		baymax.travel(10,true);
		//		Delay.msDelay(500);
		//        baymax.stop();
		//        System.out.println("Travel backwards");
		//		baymax.travel(-10);
		//		System.out.println("Spin twice anti-clockwise");
		//		baymax.rotate(720);
		//		System.out.println("Waiting for button press");
		//		// Exit after any button is pressed
		//		Button.waitForAnyPress();
		//    	
		//    	//END OF BOILERPLATE
		//    	
	} // End of the DFS method


	private static void findChildren(MapNode currentNode)
	{
		// TODO Auto-generated method stub	
	} // End of the findChildren method

	/**
	 * [Helper Method] Prints an introductionary message on the screen.
	 */
	private static void introMessage()
	{
		GraphicsLCD g = LocalEV3.get().getGraphicsLCD();
		g.drawString("Hello! I am Baymax,", 5, 0, 0);
		g.setFont(Font.getSmallFont());


		g.drawString("your personal", 2, 20, 0);
		g.drawString("healthcare companion.", 2, 30, 0);
	} // End of the IntroMessage method

	/* START OF TESTING SUITE */
	/**
	 * [Test Method] Tests whether backwards movement is good
	 * @param baymax The robot that is being tested
	 * @return true if the robot can move backwards, false if not
	 */
	private static boolean testBackwardMovement(Baymax baymax)
	{
		try {
			System.out.println("Testing: backwards movement");

			// Configuration
			baymax.setAcceleration(20);
			baymax.setTravelSpeed(5); // cm per second

			System.out.println("Waiting for press");
			Button.waitForAnyPress();

			System.out.println("Moving backwards");

			baymax.backward();
			Delay.msDelay(1000);

			System.out.println("Stopping");
			baymax.stop();

			return true;		
		}
		catch (Exception e)
		{return false;}
	} // End of the testBackwardMovement method

	/**
	 * [Test Method] Tests whether forwards movement is good
	 * @param baymax The robot that is being tested
	 * @return true if the robot can move forwards, false if not
	 */
	private static boolean testForwardMovement(Baymax baymax)
	{
		try {
			System.out.println("Testing: forwards movement");

			// Configuration
			baymax.setAcceleration(20);
			baymax.setTravelSpeed(5); // cm per second

			System.out.println("Waiting for press");
			Button.waitForAnyPress();

			System.out.println("Moving forwards");

			baymax.forward();
			Delay.msDelay(1000);

			System.out.println("Stopping");
			baymax.stop();

			return true;		
		}
		catch (Exception e)
		{return false;}
	} // End of the testForwardMovement method

	private static boolean testMotors(Baymax baymax)
	{
		try
		{
			System.out.println("Testing: Motor movement");

			// Configuration
			Button.waitForAnyPress();
			baymax.setAcceleration(20);
			baymax.setRotateSpeed(15); // degrees per second

			// Tests the left motor: forwards
			leftMotor.forward();
			if (leftMotor.isStalled())
				return false;
			else
				System.out.println("Left motor forward is good");
			baymax.stop();

			// Tests the left motor: backwards
			leftMotor.backward();
			if (leftMotor.isStalled())
				return false;
			else
				System.out.println("Left motor backward is good");
			baymax.stop();

			// Tests the right motor: forwards
			rightMotor.forward();
			if (rightMotor.isStalled())
				return false;
			else
				System.out.println("Right motor forward is good");

			// Tests the right motor: backwards
			rightMotor.backward();
			if (rightMotor.isStalled())
				return false;
			else
				System.out.println("Right motor backward is good");
			return true;
		}
		catch (Exception e)
		{return false;}
	} // End of the testMotors method

	private static boolean testRotate(Baymax baymax)
	{
		try
		{
			System.out.println("Testing: in-place rotation");

			// Configuration
			Button.waitForAnyPress();
			baymax.setAcceleration(20);
			baymax.setTravelSpeed(5); // cm per second
			baymax.setRotateSpeed(15); // degrees per second

			// Tests for 90 degree turn: clockwise
			baymax.rotate(90);
			if (baymax.isStalled())
				return false;
			Delay.msDelay(1000);
			System.out.println("90 clockwise rotation is good");
			baymax.stop();

			// Tests for 180 degree turn: clockwise
			baymax.rotate(180);
			if (baymax.isStalled())
				return false;
			Delay.msDelay(1000);
			System.out.println("180 clockwise rotation is good");
			baymax.stop();

			// Tests for 90 degree turn: counter-clockwise
			baymax.rotate(-90);
			if (baymax.isStalled())
				return false;
			Delay.msDelay(1000);
			System.out.println("90 counter-clockwise is good");
			baymax.stop();

			// Tests for 180 degree turn: counter-clockwise
			baymax.rotate(180);
			if (baymax.isStalled())
				return false;
			Delay.msDelay(1000);
			System.out.println("180 counter-clockwise is good");
			baymax.stop();

			return true;
		}
		catch (Exception e)
		{return false;}
	} // End of the testRotate method

	/* END OF TESTING SUITE */

} // End of the Baymax Class
