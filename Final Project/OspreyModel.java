//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;

/*
 * Public class OspreyModel contains all the world information about the Osprey game.
 */
public class OspreyModel extends Model {
	
	private Osprey osprey;
	private ArrayList<Fish> fish;
	private ArrayList<Seaweed> seaweed;
	private int stage;
	private final static double WATER_HEIGHT = TitleView.FRAME_HEIGHT / 2;
	private final static int MAX_FISH = 10;
	private final static int MAX_SEAWEED = 5;
	private final static int GOLD_CHANCE_MOD = 50;

	public OspreyModel(){
		super();
		osprey = new Osprey();
		fish = new ArrayList<>();
		seaweed = new ArrayList<>();
		generate();
	}

	public Osprey getOsprey() { return this.osprey; }
	
	public void setOsprey(Osprey osprey) { this.osprey = osprey; }
	
	public ArrayList<Fish> getFish() { return this.fish; }
	
	public void setFish(ArrayList<Fish> fish) { this.fish = fish; }
	
	public ArrayList<Seaweed> getSeaweed() { return this.seaweed; }
	
	public void setSeaweed(ArrayList<Seaweed> seaweed) { this.seaweed = seaweed; }
	
	public int getStage() { return this.stage; }
	
	public void setStage(int stage) { this.stage = stage; }
	
	/* 
	 * Public method isEnd.
	 * Takes no parameters, returns a boolean signifying if the game is over.
	 */
	@Override
	public boolean isEnd() {
		return osprey.getXVel() <= 0 || isWin();
	}

	/*
	 * Public method isWin.
	 * Takes no parameters, returns a boolean signifying if the game was won.
	 */
	@Override
	public boolean isWin() {
		return osprey.getXPos() >= 100000;
	}

	/* 
	 * Public method generate.
	 * Takes no parameters and returns nothing.
	 * Adds relevant GameObjects to the model.
	 */
	@Override
	public void generate() {
		while(fish.size() < MAX_FISH) {
			if(Model.rand.nextInt(GOLD_CHANCE_MOD) % GOLD_CHANCE_MOD == 0) {
				fish.add(
						new GoldenFish(Model.rand.nextDouble() * TitleView.FRAME_WIDTH + TitleView.FRAME_WIDTH + osprey.getXPos(), 
								 Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - WATER_HEIGHT) / 2 + WATER_HEIGHT));
			}
			else {
				fish.add(
						new Fish(Model.rand.nextDouble() * TitleView.FRAME_WIDTH + TitleView.FRAME_WIDTH + osprey.getXPos(), 
								 Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - WATER_HEIGHT) / 2 + WATER_HEIGHT, 
								 Model.rand.nextInt(3) + 2));
			}
		}
		while(seaweed.size() < MAX_SEAWEED) {
			seaweed.add(
					new Seaweed(Model.rand.nextDouble() * TitleView.FRAME_WIDTH + TitleView.FRAME_WIDTH + osprey.getXPos(), 
							 	Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - WATER_HEIGHT) / 2 + WATER_HEIGHT));
		}
	}
	
	public void destroy() {
		Iterator<Fish> fIter = fish.iterator();
		while(fIter.hasNext()) {
			Fish f = fIter.next();
			if(f.getXPos() < osprey.getXPos() - 2 * OspreyView.X_OFFSET) {
				fIter.remove();
			}
		}
		Iterator<Seaweed> sIter = seaweed.iterator();
		while(sIter.hasNext()) {
			Seaweed s = sIter.next();
			if(s.getXPos() < osprey.getXPos() - 2 * OspreyView.X_OFFSET) {
				sIter.remove();
			}
		}
	}
	
	/*
	 * Public method update.
	 * Takes no parameters, returns nothing.
	 * Updates model variables.
	 */
	@Override
	public void update() {
		setTime(getTime() + 1);
		osprey.move();
		for(Fish f : fish) { f.move(); }
		checkInteractions();
		destroy();
		generate();
	}
	
	/*
	 * Public method checkInteractions.
	 * Takes no parameters, returns nothing.
	 * Checks if any of the objects in the model are interacting, and handles those interactions.
	 */
	@Override
	public void checkInteractions() {
		Iterator<Fish> fIter = fish.iterator();
		while(fIter.hasNext()) {
			Fish f = fIter.next();
			if(isCollision(osprey, f)) {
				f.interact(osprey);
				fIter.remove();
			}
		}
		for(Seaweed s : seaweed) { if(isCollision(osprey, s)) { s.interact(osprey); }}
	}

}
