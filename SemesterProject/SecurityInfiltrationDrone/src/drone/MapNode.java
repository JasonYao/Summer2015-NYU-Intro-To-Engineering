/**
 * 
 */
package drone;

import java.util.ArrayList;

/**
 * @author Jason Yao
 *
 */
public class MapNode
{
	// Attributes of MapNodes
	private int positionX; // X Position
	private int positionY; // Y Position
	private ArrayList<MapNode> children;
	private MapNode predecessor;
	private boolean isDiscovered;

	/**
	 * Constructor for the source node
	 * @param positionX
	 * @param positionY
	 */
	public MapNode(int positionX, int positionY)
	{
		super();
		setPositionX(positionX);
		setPositionY(positionY);
		setChildren(new ArrayList<MapNode>());
		setPredecessor(null);
		setDiscovered(false);
	} // End of the MapNode source constructor
	
	/**
	 * Constructor for all subsequent nodes
	 * @param positionX
	 * @param positionY
	 * @param predecessor
	 */
	public MapNode(int positionX, int positionY, MapNode predecessor)
	{
		super();
		setPositionX(positionX);
		setPositionY(positionY);
		setChildren(new ArrayList<MapNode>());
		setPredecessor(predecessor);
		setDiscovered(false);
	} // End of the MapNode constructor

	/* Getters and Setters */

	public MapNode getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(MapNode predecessor) {
		this.predecessor = predecessor;
	}

	public boolean isDiscovered() {
		return isDiscovered;
	}

	public void setDiscovered(boolean isDiscovered) {
		this.isDiscovered = isDiscovered;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public ArrayList<MapNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<MapNode> children) {
		this.children = children;
	}

} // End of the MapNode class
