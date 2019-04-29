//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;

/*
 * Public class OspreyModel contains all the world information about the Osprey game.
 */
public class OspreyModel extends Model {
	
	ArrayList<OspreyAble> objects;
	Osprey osprey;
	int stage;

	OspreyModel(){
		super();
		osprey = new Osprey();
		objects = new ArrayList<>();
		stage = 0;
		initialize();
	}

	/* 
	 * Public method initialize.
	 * Takes no parameters and returns nothing.
	 * Adds relevant GameObjects to objects.
	 */
	@Override
	public void initialize() {
		objects.add(new Fish(500, 250, 1));
		objects.add(new Fish(700, 450, 2));
		objects.add(new Fish(1600, 360, 3));
		objects.add(new Fish(1000, 270, 2));
		objects.add(new Fish(2000, 535, 2));
		objects.add(new Seaweed(1500, 200));
	}

	/* 
	 * Public method isEnd.
	 * Takes no parameters, returns a boolean signifying if the game is over.
	 */
	@Override
	public boolean isEnd() {
		return isWin() || osprey.getXVel() < 0;
	}

	/*
	 * Public method isWin.
	 * Takes no parameters, returns a boolean signifying if the game was won.
	 */
	@Override
	public boolean isWin() {
		return osprey.getXPos() > 100000;
	}

	/*
	 * Public method update.
	 * Takes no parameters, returns nothing.
	 * Updates model variables.
	 */
	@Override
	public void update() {
		osprey.move();
		for(OspreyAble o : objects) {
			if(o.isAnimal()) {
				Animal a = (Animal)o;
				a.move();
			}
		}
		checkInteractions();
	}

	/*
	 * Public method checkInteractions.
	 * Takes no parameters, returns nothing.
	 * Checks if any of the objects in the model are interacting, and handles those interactions.
	 */
	@Override
	public void checkInteractions() {
		for(int i = objects.size() - 1; i >= 0; i--) {
			OspreyAble o = objects.get(i);
			if(isCollision(osprey, (GameObject)o)) {
				o.interact(osprey);
				if(o.isFish()) { objects.remove(i); }
			}
		}
	}

	public int getStage() { return this.stage; }

	public void setStage(int stage) { this.stage = stage; }
	
	public Osprey getOsprey() { return this.osprey; }
	
	public ArrayList<OspreyAble> getObjects() { return this.objects; }
	
}
