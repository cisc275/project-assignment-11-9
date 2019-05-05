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
	private final static int MAX_TREES = 10;

	public HarrierModel(){
		super();
		harrier = new Harrier();
		foxes = new ArrayList<>();
		mice = new ArrayList<>();
		twigs = new ArrayList<>();
		trees = new ArrayList<>();
		generate();
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
		return harrier.getVision() <= 0 || isWin();
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
			foxes.add(
					new Fox(Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_WIDTH - harrier.getVision()) + harrier.getVision()),
							Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - harrier.getVision()) + harrier.getVision())));
		}
		while(mice.size() < MAX_MICE) {
			mice.add(
					new Mouse(Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_WIDTH - harrier.getVision()) + harrier.getVision()),
							  Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - harrier.getVision()) + harrier.getVision())));
		}
		while(twigs.size() < MAX_TWIGS) {
			twigs.add(
					new Twig(Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_WIDTH - harrier.getVision()) + harrier.getVision()),
							 Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - harrier.getVision()) + harrier.getVision())));
		}
		while(trees.size() < MAX_TREES) {
			trees.add(
					new Tree(Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_WIDTH - harrier.getVision()) + harrier.getVision()),
							 Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - harrier.getVision()) + harrier.getVision())));
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
		harrier.move();
		for(Fox f : foxes) { f.move(); f.twitch(30, rand.nextInt()); }
		for(Mouse m : mice) { m.move(); m.twitch(20, rand.nextInt()); }
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
		for(Fox f : foxes) { if(isCollision(harrier, f)) { f.interact(harrier); }}
		Iterator<Mouse> mIter = mice.iterator();
		while(mIter.hasNext()) {
			Mouse m = mIter.next();
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
