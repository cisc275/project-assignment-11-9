//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

import java.util.*;

/*
 * Public abstract class Model defines general behaviors for the models in both the Harrier and Osprey games.
 */
public abstract class Model {
	
	ArrayList<GameObject> objects;
	int time;
	Quiz quiz;
	MiniMap map;
	
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
	
	public ArrayList<GameObject> getObjects(){ return this.objects; }
	
	public void setObjects(ArrayList<GameObject> objects) { this.objects = objects; }
	
	public int getTime() { return this.time; }
	
	public void setTime(int time) { this.time = time; }
	
	public Quiz getQuiz() { return this.quiz; }
	
	public void setQuiz(Quiz quiz) { this.quiz = quiz; }
	
	public MiniMap getMap() { return this.map; }
	
	public void setMap(MiniMap map) { this.map = map; }
}
