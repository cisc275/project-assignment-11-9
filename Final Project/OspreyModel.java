//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

/*
 * Public class OspreyModel contains all the world information about the Osprey game.
 */
public class OspreyModel extends Model {

	Osprey osprey;
	int stage;

	OspreyModel(){
		super();
		osprey = new Osprey();
		stage = 0;
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
		return osprey.getXPos() > 1000 || osprey.getXVel() == 0;
	}

	/*
	 * Public method isWin.
	 * Takes no parameters, returns a boolean signifying if the game was won.
	 */
	@Override
	public boolean isWin() {
		return osprey.getXPos() > 1000;
	}

	/*
	 * Public method update.
	 * Takes no parameters, returns nothing.
	 * Updates model variables.
	 */
	@Override
	public void update() {
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
			if(isCollision(osprey, a)) {
				osprey.interact(a);
				if(a instanceof Fish) { animals.remove(i); }
			}
		}
		for(GameObject p : plants) {
			if(isCollision(osprey, p)) { osprey.interact(p); }
		}
	}

	public int getStage() { return this.stage; }

	public void setStage(int stage) { this.stage = stage; }
}
