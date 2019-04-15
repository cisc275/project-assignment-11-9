//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

import java.util.*;

/*
 * Public abstract class Model defines general behaviors for the models in both the Harrier and Osprey games.
 */
public abstract class Model {

	ArrayList<Animal> animals;
	ArrayList<GameObject> plants;
	int time;
	Quiz quiz;
	
	Model(){
		animals = new ArrayList<>();
		plants = new ArrayList<>();
		time = 0;
		quiz = new Quiz();
		initializeGame();
	}
	
	/* 
	 * Public abstract method initializeGame.
	 * Takes no parameters and returns nothing.
	 * Adds relevant GameObjects to objects.
	 */
	public abstract void initializeGame();
	
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
	
	/*
	 * Public method isCollision.
	 * Takes two GameObjects, returns boolean.
	 * Checks if the hit-boxes of the two objects are in contact.
	 */
	public boolean isCollision(GameObject g1, GameObject g2) {
		return g1.getXPos() - g2.getXPos() <= g1.getXWidth() + g2.getXWidth()
			&& g1.getYPos() - g2.getYPos() <= g1.getYWidth() + g2.getYWidth();
	}
	
	public ArrayList<Animal> getAnimals(){ return this.animals; }

	public void setAnimals(ArrayList<Animal> animals) { this.animals = animals; }
	
	public ArrayList<GameObject> getPlants(){ return this.plants; }

	public void setPlants(ArrayList<GameObject> plants) { this.plants = plants; }

	public int getTime() { return this.time; }

	public void setTime(int time) { this.time = time; }

	public Quiz getQuiz() { return this.quiz; }

	public void setQuiz(Quiz quiz) { this.quiz = quiz; }
	
}
