//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;
import java.lang.Math;
/*
 * Public abstract class Model defines general behaviors for the models in both the Harrier and Osprey games.
 */
public abstract class Model implements java.io.Serializable {

	private int time;
	protected Tutorial stage;
	public static Random rand = new Random(System.currentTimeMillis());

	public int getTime() { return this.time; }

	public void setTime(int time) { this.time = time; }
	
	public Tutorial getStage() { return stage; }
	
	public void setStage(Tutorial stage) { this.stage = stage; }

	/* 
	 * Public abstract method isLoss.
	 * Parameters: none
	 * Returns: boolean
	 * Returns a boolean signifying if the game was lost.
	 */
	public abstract boolean isLoss();
	
	/*
	 * Public abstract method isWin.
	 * Parameters: none
	 * Returns: boolean
	 * Returns a boolean signifying if the game was won.
	 */
	public abstract boolean isWin();
	
	/* 
	 * Public abstract method generate.
	 * Parameters: none
	 * Returns: nothing
	 * Adds relevant GameObjects to the model.
	 */
	public abstract void generate();
	
	/*
	 * Public abstract method update.
	 * Parameters: none
	 * Returns: nothing
	 * Changes model variables on each tick.
	 */
	public abstract void update();
	
	/*
	 * Public abstract method checkInteractions.
	 * Parameters: none
	 * Returns: nothing
	 * Checks if any of the objects in the model are interacting, and handles those interactions.
	 */
	public abstract void checkInteractions();
	
	/*
	 * Public method isCollision.
	 * Parameters: 
	 *     GameObject: g1, g2
	 * Returns: boolean
	 * Returns a boolean indicating if the hit-boxes of the two objects are in contact
	 */
	public boolean isCollision(GameObject g1, GameObject g2) {
		//Widths of GameObjects are divided by 2 to convert diameters to radii.
		double xDist = Math.abs(g1.getXPos() - g2.getXPos());
		double xWidth = g1.getXWidth() / 2 + g2.getXWidth() / 2;
		double yDist = Math.abs(g1.getYPos() - g2.getYPos());
		double yWidth = g1.getYWidth() / 2 + g2.getYWidth() / 2;
		return xDist <= xWidth && yDist <= yWidth;
	}
	
	/*
	 * Public method randomSign.
	 * Parameters: none
	 * Returns: int
	 * Produces 1 or -1 randomly.
	 */
	public static int randomSign() {
		if(Model.rand.nextBoolean()) { return 1; }
		else { return -1; }
	}

}