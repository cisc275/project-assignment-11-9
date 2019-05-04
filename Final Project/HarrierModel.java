//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;

/*
 * Public class HarrierModel contains all the world information about the Harrier game.
 */
public class HarrierModel extends Model {

	ArrayList<HarrierAble> objects;
	Harrier harrier;

	HarrierModel(){
		super();
		harrier = new Harrier();
		objects = new ArrayList<>();
	}

	/* 
	 * Public method initialize.
	 * Takes no parameters and returns nothing.
	 * Adds relevant GameObjects to objects.
	 */
	@Override
	public void initialize() {
		objects.add(new Mouse());
		objects.add(new Mouse());
		objects.add(new Fox());
		objects.add(new Tree());
		objects.add(new Tree());
		objects.add(new Twig());
	}

	/* 
	 * Public method isEnd.
	 * Takes no parameters, returns a boolean signifying if the game is over.
	 */
	@Override
	public boolean isEnd() {
		return isWin() || harrier.getVision() < 1;
	}

	/*
	 * Public method isWin.
	 * Takes no parameters, returns a boolean signifying if the game was won.
	 */
	@Override
	public boolean isWin() {
		return getTime() > 100000;
	}

	/*
	 * Public method update.
	 * Takes no parameters, returns nothing.
	 * Updates model variables.
	 */
	@Override
	public void update() {
		int iterative = 0;
		harrier.move();
		for(HarrierAble h : objects) {
			if(h.isAnimal()) {
				Animal a = (Animal)h;
				a.move();
				a.twitch(20, iterative);
			}
			iterative ++;
		}
		checkInteractions();
		randGen();
		
	}

	/*
	 * Public method checkInteractions.
	 * Takes no parameters, returns nothing.
	 * Checks if any of the objects in the model are interacting, and handles those interactions.
	 */
	@Override
	public void checkInteractions() {
		Iterator iter = objects.iterator();
		while (iter.hasNext()) {
			HarrierAble h = (HarrierAble) iter.next();
			if(isCollision(harrier, (GameObject)h)) {
				h.interact(harrier);
				if(h.isMouse() || h.isTwig()) { iter.remove(); }
			}
		}
	}

	public Harrier getHarrier() { return this.harrier; }

	public ArrayList<HarrierAble> getObjects() { return this.objects; }
	
	public void randGen() {
		int randAnimal= (int) ((Math.random() * (4) + 0));
		switch(randAnimal) {
		case 1:
			objects.add(new Mouse());
		case 2:
			objects.add(new Twig());
		}
		
	}

}