//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

/*
 * Public class HarrierModel contains all the world information about the Harrier game.
 */
public class HarrierModel extends Model {
	
	Harrier harrier;
	
	HarrierModel(){
		super();
		harrier = new Harrier();
		for (Animal a: animals) {
			a.setXVel(10);
		}
	}
	
	/* 
	 * Public method initializeGame.
	 * Takes no parameters and returns nothing.
	 * Adds relevant GameObjects to plants and animals.
	 */
	public void initializeGame() {

	}
	
	/* 
	 * Public method isEnd.
	 * Takes no parameters, returns a boolean signifying if the game is over.
	 */
	@Override
	public boolean isEnd() {
		return time > 100000 || harrier.getVision() == 0;
	}

	/*
	 * Public method isWin.
	 * Takes no parameters, returns a boolean signifying if the game was won.
	 */
	@Override
	public boolean isWin() {
		return time > 100000;
	}

	/*
	 * Public method update.
	 * Takes no parameters, returns nothing.
	 * Updates model variables.
	 */
	@Override
	public void update() {
		harrier.move();
		for(Animal a : animals) { a.move(); }
		checkInteractions();
	}

	/*
	 * Public method checkInteractions.
	 * Takes no parameters, returns nothing.
	 * Checks if any of the objects in the model are interacting, and handles those interactions.
	 */
	@Override
	public void checkInteractions() {
		for(int i = animals.size() - 1; i >= 0; i--) {
			Animal a = animals.get(i);
			if(isCollision(harrier, a)) {
				harrier.interact(a);
				if(a instanceof Mouse) { animals.remove(i); }
			}
		}
		for(GameObject p : plants) {
			if(isCollision(harrier, p)) { harrier.interact(p); }
		}
	}
	
	public int[] getObjectTypes() {
		int[] types = new int[animals.size() + 1];
		types[0] = 0;
		for (int i = 1; i < animals.size() + 1; i++) {
			types[i] = 1;
		}
		return types;
	}
	
	public int[] getXs() {
		int[] x = new int[animals.size() + 1];
		x[0] = harrier.getXPos();
		for (int i = 1; i < animals.size() + 1; i++) {
			x[i] = animals.get(i).getXPos();
		}
		return x;
	}
	
	public int[] getYs() {
		int[] y = new int[animals.size() + 1];
		y[0] = harrier.getYPos();
		for (int i = 1; i < animals.size() + 1; i++) {
			y[i] = animals.get(i).getYPos();
		}
		return y;
	}
	
	public Direction[] getDirs() {
		Direction[] dir = new Direction[animals.size() + 1];
		dir[0] = harrier.getDirection();
		for (int i = 1; i < animals.size() + 1; i++) {
			dir[i] = animals.get(i).getDirection();
		}
		return dir;
	}
}
