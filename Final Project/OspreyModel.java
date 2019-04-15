//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

/*
 * Public class OspreyModel contains all the world information about the Osprey game.
 */
public class OspreyModel extends Model {

	int stage;
	
	/* 
	 * Public method isEnd.
	 * Takes no parameters, returns a boolean signifying if the game is over.
	 */
	@Override
	public boolean isEnd() {
		return false;
	}

	/*
	 * Public method isWin.
	 * Takes no parameters, returns a boolean signifying if the game was won.
	 */
	@Override
	public boolean isWin() {
		return false;
	}

	/*
	 * Public method update.
	 * Takes no parameters, returns nothing.
	 * Updates model variables.
	 */
	@Override
	public void update() {
	}

	/*
	 * Public method checkInteractions.
	 * Takes no parameters, returns nothing.
	 * Checks if any of the objects in the model are interacting, and handles those interactions.
	 */
	@Override
	public void checkInteractions() {
	}
	
	public int getStage() { return this.stage; }
	
	public void setStage(int stage) { this.stage = stage; }

}
