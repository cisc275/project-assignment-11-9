//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;

/*
 * Public class HarrierModel contains all the world information about the Harrier game.
 */
public class HarrierModel extends Model {

	private Harrier harrier;
	private ArrayList<Fox> foxes;
	private ArrayList<Mouse> mice;
	private ArrayList<Twig> twigs;
	private ArrayList<Tree> trees;
	private final static int MAX_FOXES = 3;
	private final static int MAX_MICE = 20;
	private final static int MAX_TWIGS = 20;
	private final static int MAX_TREES = 50;
	private final static int GOLD_CHANCE_MOD = 25;

	public HarrierModel(){
		super();
		harrier = new Harrier();
		foxes = new ArrayList<>();
		mice = new ArrayList<>();
		twigs = new ArrayList<>();
		trees = new ArrayList<>();
	}

	public Harrier getHarrier() { return this.harrier; }
	
	public void setHarrier(Harrier harrier) { this.harrier = harrier; }
	
	public ArrayList<Fox> getFoxes() { return this.foxes; }
	
	public void setFoxes(ArrayList<Fox> foxes) { this.foxes = foxes; }
	
	public ArrayList<Mouse> getMice() { return this.mice; }
	
	public void setMice(ArrayList<Mouse> mice) { this.mice = mice; }
	
	public ArrayList<Twig> getTwigs() { return this.twigs; }
	
	public void setTwigs(ArrayList<Twig> twigs) {this.twigs = twigs; }
	
	public ArrayList<Tree> getTrees() { return this.trees; }
	
	public void setTrees(ArrayList<Tree> trees) { this.trees = trees; }
	
	/* 
	 * Public method isEnd.
	 * Takes no parameters, returns a boolean signifying if the game is over.
	 */
	@Override
	public boolean isEnd() {
		return harrier.getVision() <= Harrier.MIN_VISION || isWin();
	}

	/*
	 * Public method isWin.
	 * Takes no parameters, returns a boolean signifying if the game was won.
	 */
	@Override
	public boolean isWin() {
		return getTime() >= 100000;
	}

	/* 
	 * Public method generate.
	 * Takes no parameters and returns nothing.
	 * Adds relevant GameObjects to the model.
	 */
	@Override
	public void generate() {
		while(foxes.size() < MAX_FOXES) {
			double[] coords = genCoords();
			Fox f = new Fox(coords[0],coords[1]);
			if(generationHelper(f)) { foxes.add(f); }
		}
		while(mice.size() < MAX_MICE) {
			double[] coords = genCoords();
			if(Model.rand.nextInt(GOLD_CHANCE_MOD) % GOLD_CHANCE_MOD == 0) {
				GoldenMouse gm = new GoldenMouse(coords[0],coords[1]);
				if(generationHelper(gm)) { mice.add(gm); }
			}
			else {
				Mouse m = new Mouse(coords[0],coords[1]);
				if(generationHelper(m)) { mice.add(m); }
			}
		}
		while(twigs.size() < MAX_TWIGS) {
			double[] coords = genCoords();
			Twig tw = new Twig(coords[0],coords[1]);
			if(generationHelper(tw)) { twigs.add(tw); }
		}
		while(trees.size() < MAX_TREES) {
			double[] coords = genCoords();
			Tree tr = new Tree(coords[0],coords[1]);
			if(generationHelper(tr)) { trees.add(tr); }
		}
	}
	
	/*
	 * private method genCoords.
	 * Takes no parameters, returns double[].
	 * Generates a pair of coordinates for the harrier game.
	 */
	private double[] genCoords() {
		double angle = Model.rand.nextDouble() * Math.PI / 2;
		double[] coords = {Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_WIDTH - harrier.getVision()) + harrier.getVision() * Math.cos(angle)) + harrier.getXPos(),
				   		   Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - harrier.getVision()) + harrier.getVision() * Math.sin(angle)) + harrier.getYPos()};
		return coords;
	}
	
	/*
	 * private method generationHelper.
	 * Takes GameObject as parameter, and returns boolean.
	 * Checks if the GameObject will be in contact with other GameObjects.
	 */
	private boolean generationHelper(GameObject go) {
		boolean flag = true;
		for(Fox f : foxes) { if(isCollision(go,f)) { flag = false; }}
		for(Mouse m : mice) { if(isCollision(go, m)) { flag = false; }}
		for(Twig tw : twigs) { if(isCollision(go,tw)) { flag = false; }}
		for(Tree tr : trees) { if(isCollision(go,tr)) { flag = false; }}
		return flag;
	}
	
	/*
	 * Public method update.
	 * Takes no parameters, returns nothing.
	 * Updates model variables.
	 */
	@Override
	public void update() {
		setTime(getTime() + 1);
		harrier.move(); harrier.incrementAnimation();
		for(Fox f : foxes) { f.roam(harrier); f.incrementAnimation(); }
		for(Mouse m : mice) { m.roam(); m.incrementAnimation(); }
		checkInteractions();
		generate();
	}

	/*
	 * Public method checkInteractions.
	 * Takes no parameters, returns nothing.
	 * Checks if any of the objects in the model are interacting, and handles those interactions.
	 */
	@Override
	public void checkInteractions() {
		for(Fox f : foxes) {
			if(isCollision(harrier, f)) {f.interact(harrier); }
		}
		Iterator<Mouse> mIter = mice.iterator();
		while(mIter.hasNext()) {
			Mouse m = mIter.next();
			for(GameObject tr : trees) {
				if(isCollision(m, tr)) { m.interact(tr); }
			}
			if(isCollision(harrier, m)) {
				m.interact(harrier);
				mIter.remove();
			}
		}
		Iterator<Twig> twIter = twigs.iterator();
		while(twIter.hasNext()) {
			Twig tw = twIter.next();
			if(isCollision(harrier, tw)) {
				tw.interact(harrier);
				twIter.remove();
			}
		}
		for(Tree tr : trees) { if(isCollision(harrier, tr)) { tr.interact(harrier); }}
	}

}
