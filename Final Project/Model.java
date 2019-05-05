//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;
import java.lang.Math;

/*
 * Public abstract class Model defines general behaviors for the models in both the Harrier and Osprey games.
 */
public abstract class Model {

	private int time;
	private Quiz quiz;
	public static Random rand = new Random(System.currentTimeMillis());

	public int getTime() { return this.time; }

	public void setTime(int time) { this.time = time; }

	public Quiz getQuiz() { return this.quiz; }

	public void setQuiz(Quiz quiz) { this.quiz = quiz; }

	/* 
	 * Public abstract method isEnd.
	 * Takes no parameters, returns a boolean signifying if the game has achieved the end state.
	 */
	public abstract boolean isEnd();
	
	/*
	 * Public abstract method isWin.
	 * Takes no parameters, returns a boolean signifying if the game was won.
	 */
	public abstract boolean isWin();
	
	/* 
	 * Public abstract method generate.
	 * Takes no parameters and returns nothing.
	 * Adds relevant GameObjects to the model.
	 */
	public abstract void generate();
	
	/*
	 * Public abstract method update.
	 * Takes no parameters, returns nothing.
	 * Changes model variables on each tick.
	 */
	public abstract void update();
	
	/*
	 * Public abstract method checkInteractions.
	 * Takes no parameters, returns nothing.
	 * Checks if any of the objects in the model are interacting, and handles those interactions.
	 */
	public abstract void checkInteractions();
	
	/*
	 * Public method isCollision.
	 * Takes two GameObjects, returns boolean.
	 * Checks if the hit-boxes of the two objects are in contact.
	 */
	public boolean isCollision(GameObject g1, GameObject g2) {
		//Widths of GameObjects are divided by 2 to convert diameters to radii.
		double xDist = Math.abs(g1.getXPos() - g2.getXPos());
		double xWidth = g1.getXWidth() / 2 + g2.getXWidth() / 2;
		double yDist = Math.abs(g1.getYPos() - g2.getYPos());
		double yWidth = g1.getYWidth() / 2 + g2.getYWidth() / 2;
		return xDist <= xWidth && yDist <= yWidth;
	}
	
	public static int randomSign() {
		if(Model.rand.nextBoolean()) { return 1; }
		else { return -1; }
	}

}
