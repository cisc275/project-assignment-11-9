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
		objects.add(new Mouse(100, 100));
		objects.add(new Mouse(-300, -300));
		objects.add(new Fox(-500, -500));
		objects.add(new Tree(-500, 0));
		objects.add(new Tree(370, -280));
		objects.add(new Twig(100, 100));
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
		harrier.move();
		for(int i = 0; i < objects.size(); i++) {
			HarrierAble h = objects.get(i);
			if(h instanceof Animal) {
				Animal a = (Animal)h;
				a.move();
				a.twitch(20, i);
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
			HarrierAble h = objects.get(i);
			if(isCollision(harrier, (GameObject)h)) {
				h.interact(harrier);
				if(h instanceof Mouse || h instanceof Twig) { objects.remove(i); }
			}
		}
	}

	public Harrier getHarrier() { return this.harrier; }

	public ArrayList<HarrierAble> getObjects() { return this.objects; }

}
