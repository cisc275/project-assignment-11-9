//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
/*
 * public class Model defines general behaviors for the models for both the Harrier and Osprey games.
 */
public abstract class Model {
	
	/* 
	 * Public abstract method isEnd.
	 * Takes no parameters, returns a boolean signifying if the game is over.
	 */
	public abstract boolean isEnd();
	
	/*
	 * Public abstract method isWin.
	 * Takes no parameters, returns a boolean signifying if the game was won.
	 */
	public abstract boolean isWin();
	
	/*
	 * Public abstract method update.
	 * Takes no parameters, returns nothing.
	 * Updates model variables.
	 */
	public abstract void update();
	
	/*
	 * Public abstract method checkInteractions.
	 * Takes no parameters, returns nothing.
	 * Checks if any of the objects in the model are interacting, and handles those interactions.
	 */
	public abstract void checkInteractions();
}
