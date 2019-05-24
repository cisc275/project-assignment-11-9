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
	private final static int MAX_TREES = 125;
	private final static int GOLD_CHANCE_MOD = 5;
	public final static double EXCLUSION_RADIUS = 150;
	private final static int END_TIME = 3628;
	private final static double TREE_COORDS_SEED = 1.4;
	private final static double NORMAL_COORDS_SEED = 1;
	private final static int Y_COORD = 1;
	private final static int X_COORD = 0;
	private final static int LEFT_TREE_INDEX = 120;
	private final static int RIGHT_TREE_INDEX = 360;
	private final static int TREE_SPACING = 120;
	private final static int UPPER_TREE_INDEX = 0;
	private final static int LOWER_TREE_INDEX = 240;
	private final static int UPPER_MIDDLE_REMOVED_TREE = 7;
	private final static int LEFT_MIDDLE_REMOVED_TREE = 1;
	private final static int HARRIER_STAGE_NINE = 300;
	private final static int FOX_RIGHT_OR_LOW = 600;
	private final static int FOX_LEFT_OR_HIGH = 100;
	private Tutorial stage = Tutorial.ONE;

	public enum Tutorial {
		ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, NONE;
	}
	
	public Tutorial getStage() {
		return stage;
	}

	public HarrierModel(){
		super();
		harrier = new Harrier();
		foxes = new ArrayList<>();
		mice = new ArrayList<>();
		twigs = new ArrayList<>();
		trees = new ArrayList<>();
	}

	public Harrier getHarrier() { return harrier; }
	
	public void setHarrier(Harrier harrier) { this.harrier = harrier; }
	
	public ArrayList<Fox> getFoxes() { return foxes; }
	
	public void setFoxes(ArrayList<Fox> foxes) { this.foxes = foxes; }
	
	public ArrayList<Mouse> getMice() { return mice; }
	
	public void setMice(ArrayList<Mouse> mice) { this.mice = mice; }
	
	public ArrayList<Twig> getTwigs() { return twigs; }
	
	public void setTwigs(ArrayList<Twig> twigs) {this.twigs = twigs; }
	
	public ArrayList<Tree> getTrees() { return trees; }
	
	public void setTrees(ArrayList<Tree> trees) { this.trees = trees; }
	
	/* 
	 * Public method isEnd.
	 * Takes no parameters, returns a boolean signifying if the game is over.
	 */
	@Override
	public boolean isEnd() {
		return harrier.getVision() <= Harrier.MIN_VISION;
	}

	/*
	 * Public method isWin.
	 * Takes no parameters, returns a boolean signifying if the game was won.
	 */
	@Override
	public boolean isWin() {
		return getTime() >= END_TIME;
	}
	
	private void resetHarrier() {
		harrier.setXPos(0);
		harrier.setYPos(0);
		harrier.setYVel(0);
		harrier.setXVel(0);
	}
	
	private void prepareStageSeven() {
		Tree t;
		for (int x = UPPER_TREE_INDEX; x <= LOWER_TREE_INDEX; x += TREE_SPACING) {
			t = new Tree(LEFT_TREE_INDEX, x);
			trees.add(t);
		}
		t = new Tree((LEFT_TREE_INDEX + RIGHT_TREE_INDEX)/2, LOWER_TREE_INDEX);
		trees.add(t);
		for (int x = UPPER_TREE_INDEX; x <= LOWER_TREE_INDEX; x += TREE_SPACING) {
			t = new Tree(RIGHT_TREE_INDEX, x);
			trees.add(t);
		}
		t = new Tree((LEFT_TREE_INDEX + RIGHT_TREE_INDEX)/2, UPPER_TREE_INDEX);
		trees.add(t);
		GoldenMouse m = new GoldenMouse((LEFT_TREE_INDEX + RIGHT_TREE_INDEX)/2, (UPPER_TREE_INDEX + LOWER_TREE_INDEX)/2, true);
		mice.add(m);
		resetHarrier();
	}
	
	private void prepareStageNine() {
		Iterator iter = trees.iterator();
		while (iter.hasNext()) {
			Object t = iter.next();
			iter.remove();
		}
		harrier.setXPos(HARRIER_STAGE_NINE);
		harrier.setYPos(HARRIER_STAGE_NINE);
		harrier.setYVel(0);
		harrier.setXVel(0);
		Fox f1 = new Fox(FOX_RIGHT_OR_LOW, FOX_RIGHT_OR_LOW);
		Fox f2 = new Fox(FOX_LEFT_OR_HIGH, FOX_LEFT_OR_HIGH);
		Fox f3 = new Fox(FOX_LEFT_OR_HIGH, FOX_RIGHT_OR_LOW);
		Fox f4 = new Fox(FOX_RIGHT_OR_LOW, FOX_LEFT_OR_HIGH);
		foxes.add(f1);
		foxes.add(f2);
		foxes.add(f3);
		foxes.add(f4);
	}
	

	/* 
	 * Public method generate.
	 * Takes no parameters and returns nothing.
	 * Adds relevant GameObjects to the model.
	 */
	@Override
	public void generate() {
		while(foxes.size() < MAX_FOXES) {
			double[] coords = genCoords(NORMAL_COORDS_SEED);
			Fox f = new Fox(coords[X_COORD],coords[Y_COORD]);
			if(generationHelper(f, true)) { foxes.add(f); }
		}
		while(mice.size() < MAX_MICE) {
			double[] coords = genCoords(NORMAL_COORDS_SEED);
			if(Model.rand.nextInt(GOLD_CHANCE_MOD) == 0) {
				GoldenMouse gm = new GoldenMouse(coords[X_COORD],coords[Y_COORD]);
				if(generationHelper(gm, true)) { mice.add(gm); }
			}
			else {
				Mouse m = new Mouse(coords[X_COORD],coords[Y_COORD]);
				if(generationHelper(m, true)) { mice.add(m); }
			}
		}
		while(twigs.size() < MAX_TWIGS) {
			double[] coords = genCoords(NORMAL_COORDS_SEED);
			Twig tw = new Twig(coords[X_COORD],coords[Y_COORD]);
			if(generationHelper(tw, true)) { twigs.add(tw); }
		}
		while(trees.size() < MAX_TREES) {
			double[] coords = genCoords(TREE_COORDS_SEED);
			Tree tr = new Tree(coords[X_COORD], coords[Y_COORD]);
			if(generationHelper(tr, false)) { trees.add(tr); }
		}
	}
	
	/*
	 * private method genCoords.
	 * Takes no parameters, returns double[].
	 * Generates a pair of coordinates for the harrier game.
	 */
	private double[] genCoords(double scalar) {
		double angle = Model.rand.nextDouble() * Math.PI / 2;
		double[] coords = {Model.randomSign() * (Model.rand.nextDouble() * (scalar * TitleView.FRAME_WIDTH - harrier.getVision()) + 
						   harrier.getVision() * Math.cos(angle)) + harrier.getXPos(),
				   		   Model.randomSign() * (Model.rand.nextDouble() * (scalar * TitleView.FRAME_HEIGHT - harrier.getVision()) +
				   		   harrier.getVision() * Math.sin(angle)) + harrier.getYPos()};
		return coords;
	}
	
	/*
	 * private method generationHelper.
	 * Takes GameObject as parameter, and returns boolean.
	 * Checks if the GameObject will be in contact with other GameObjects.
	 */
	private boolean generationHelper(GameObject go, boolean boundCheck) {
		boolean flag = true;
		for(Fox f : foxes) { if(isCollision(go,f)) { flag = false; }}
		for(Mouse m : mice) { if(isCollision(go, m)) { flag = false; }}
		for(Twig tw : twigs) { if(isCollision(go,tw)) { flag = false; }}
		for(Tree tr : trees) { if(isCollision(go,tr)) { flag = false; }}
		if(boundCheck && (go.getXPos() >= TitleView.FRAME_WIDTH || go.getXPos() <= -TitleView.FRAME_WIDTH ||
		   go.getYPos() >= TitleView.FRAME_HEIGHT || go.getYPos() <= -TitleView.FRAME_HEIGHT)) { flag = false; }
		return flag;
	}
	
	/*
	 * Public method update.
	 * Takes no parameters, returns nothing.
	 * Updates model variables.
	 */
	@Override
	public void update() {
		harrier.moveBounded(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, 0); harrier.incrementAnimation();
		for(Fox f : foxes) { f.roam(harrier, TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, -1); f.incrementAnimation(); }
		for(Mouse m : mice) { m.roam(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, -1); m.incrementAnimation(); }
		checkInteractions();
		switch(stage) {
		case ONE:
			if (harrier.getYVel() < 0) {
				stage = Tutorial.TWO;
			}
			break;
		case TWO:
			if (harrier.getXVel() > 0) {
				stage = Tutorial.THREE;
			}
			break;
		case THREE:
			if (harrier.getYVel() > 0) {
				stage = Tutorial.FOUR;
			}
			break;
		case FOUR:
			if (harrier.getXVel() < 0) {
				stage = Tutorial.FIVE;
				resetHarrier();
				Twig tw = new Twig(100, 100);
				twigs.add(tw);
			}
			break;
		case FIVE:
			if (twigs.size() < 1) {
				stage = Tutorial.SIX;
				resetHarrier();
				Mouse m = new Mouse(300, 300);
				mice.add(m);
			}
			break;
		case SIX:
			if (mice.size() < 1) {
				stage = Tutorial.SEVEN;
				prepareStageSeven();
			}
			break;
		case SEVEN:
			if (harrier.getVision() < harrier.INITIAL_VISION + Mouse.VISION_BOOST + Twig.VISION_BOOST) {
				stage = Tutorial.EIGHT;
				if (harrier.getYPos() > 0) {
					trees.remove(UPPER_MIDDLE_REMOVED_TREE);
				} else {
					trees.remove(LEFT_MIDDLE_REMOVED_TREE);
				}
			}
			break;
		case EIGHT:
			if (mice.size() < 1) {
				stage = Tutorial.NINE;
				prepareStageNine();
			}
			break;
		case NINE:
			if (harrier.getXPos() == 0) {
				stage = Tutorial.NONE;
				harrier.setVision(Harrier.INITIAL_VISION);
				Iterator<Fox> iter = foxes.iterator();
				while (iter.hasNext()) {
					Fox f = iter.next();
					iter.remove();
				}
			}
			break;
		case NONE:
			setTime(getTime() + 1);
			generate();
			break;
		}
	}

	/*
	 * Public method checkInteractions.
	 * Takes no parameters, returns nothing.
	 * Checks if any of the objects in the model are interacting, and handles those interactions.
	 */
	@Override
	public void checkInteractions() {
		for(Fox f : foxes) {
			if(isCollision(harrier, f) && harrier.calcDist() > EXCLUSION_RADIUS) { f.interact(harrier); }
		}
		Iterator<Mouse> mIter = mice.iterator();
		while(mIter.hasNext()) {
			Mouse m = mIter.next();
			for(Tree tr : trees) {
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