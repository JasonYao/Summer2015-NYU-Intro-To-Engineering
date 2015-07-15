package drone;

/**
 * 
 */
import java.util.Stack;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.utility.PilotProps;
//import lejos.robotics.RegulatedMotor;

/**
 * @author Jason Yao
 *
 */
public class Ow
{
	// Independently controlled motors
	private static RegulatedMotor leftMotor;
	private static RegulatedMotor rightMotor;
	private static boolean GLORIOUSSUCCESS = false; // Will be set to true when all objectives are complete
	//private static boolean ENDOFTHELINE = false; // Will be set to true when all checkpoints have been reached TODO

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			// Welcome Message
			//introMessage();
			LCD.drawString("Plugin Test", 0, 4);
			Delay.msDelay(5000);

			/* START OF CONFIGURATIONS */
//			PilotProps pp = new PilotProps();
//			pp.loadPersistentValues();
//			float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "4.0"));
//			float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "18.0"));
//
//			System.out.println("Wheel diameter is " + wheelDiameter);
//			System.out.println("Track width is " + trackWidth);
//
//			leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "A"));
//			rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "B"));
//			boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE,"false"));
//
//			Baymax baymax = new Baymax(wheelDiameter,trackWidth,leftMotor,rightMotor,reverse);
			/* END OF CONFIGURATIONS*/

			//TODO remove after testing
//			System.out.println("Waiting for press");
//			Button.waitForAnyPress();
//			baymax.setAcceleration(4000);
//			baymax.setTravelSpeed(20); // cm/sec
//			baymax.setRotateSpeed(180); // deg/sec
//			System.out.println("Going forwards");
//			baymax.forward();
//			Delay.msDelay(1000);
//			System.out.println("Stopping");
//			baymax.stop();
//			System.out.println("Stopped");


			/* START OF TESTING SUITE */
			//	    	if (testMotors(baymax) == false)
			//	    		throw new BaymaxException("Error: Cannot move motors, Mochi is sitting on them");
			//	    	if (!testForwardMovement(baymax) == false)
			//	    		throw new BaymaxException("Error: Cannot move forwards, need to let loose some air");
			//	    	if (!testRotate(baymax) == false)
			//	    		throw new BaymaxException("Error: Cannot rotate in place, stomach in the way");
			//	    	if (!testBackwardMovement(baymax) == false)
			//	    		throw new BaymaxException("Error: Cannot move backwards, there's a door in my way");
			/* END OF TESTING SUITE */

			/* START OF MAZE SOLVER */
			// If in unknown maze, run Balalalalalala with 0
			// If in known graph, run Balalalalalala with 1
//			Balalalalalala(baymax, 0);
			/* END OF MAZE SOLVER*/

			/* START OF ITEM RETRIEVEL */

			/* END OF ITEM RETRIEVEL */
//			System.out.println("Glorious Success");

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
	@SuppressWarnings("unused") // TODO get rid of suppress
	private static void Balalalalalala(Baymax baymax, int mode) throws BaymaxException
	{
		// If unoptimized and unknown, call DFS. If known, call Dijkstra
		if (mode == 0)
			DFS(baymax);
		else if (mode == 1)
			Disjkstra(baymax);
		else
			throw new BaymaxException("Error: Invalid traversal mode selected");
	} // End of Balalalalala method

	private static void Disjkstra(Baymax baymax)
	{
		// TODO Auto-generated method stub

	} // End of Disjkstra's Algorithm method

	/**
	 * 
	 * @param baymax
	 */
	private static void DFS(Baymax baymax)
	{
		Stack<MapNode> movementStack = new Stack<MapNode>();
		MapNode currentNode = new MapNode(0,0); // Initializes the source node to be at (0,0) in a cartesian plane
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

	private static void introMessage()
	{

	}

	/* START OF TESTING SUITE */
	private static boolean testBackwardMovement(Baymax baymax)
	{
		// TODO Auto-generated method stub
		return false;
	} // End of the testBackwardMovement method

	private static boolean testForwardMovement(Baymax baymax)
	{
		// TODO Auto-generated method stub
		return false;
	} // End of the testForwardMovement method

	private static boolean testMotors(Baymax baymax)
	{
		// TODO Auto-generated method stub
		return false;
	} // End of the testMotors method

	private static boolean testRotate(Baymax baymax)
	{
		// TODO Auto-generated method stub
		return false;
	} // ENd of the testRotate method

	/* END OF TESTING SUITE */

} // End of the Baymax Class
