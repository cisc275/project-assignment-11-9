//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.util.*;

/*
 * Public class HarrierModel contains all the world information about the Harrier game.
 */
public class HarrierModel extends Model implements java.io.Serializable {

	private static Harrier harrier;
	private ArrayList<Fox> foxes;
	private ArrayList<Mouse> mice;
	private ArrayList<Twig> twigs;
	private ArrayList<Tree> trees;
	private final static int MAX_FOXES = 3;
	private final static int MAX_MICE = 20;
	private final static int MAX_TWIGS = 20;
	private final static int MAX_TREES = 30;
	private final static int GOLD_CHANCE_MOD = 100;
	public final static double MIN_VISION = 1;
	public final static double MAX_VISION = Harrier.INITIALVISION;
	public boolean tutorial = true;
	public boolean tutorial1 = false;
	public boolean tutorial2 = false;
	public boolean tutorial3 = false;
	public boolean tutorial4 = false;
	public Tutorial tutorialState = Tutorial.ONE;
	
	public enum Tutorial {
		ONE, TWO, THREE, FOUR, NONE;
	}
	public HarrierModel(){
		super();
		harrier = new Harrier();
		foxes = new ArrayList<>();
		mice = new ArrayList<>();
		twigs = new ArrayList<>();
		trees = new ArrayList<>();
		//generate();
	}

	public static Harrier getHarrier() { return HarrierModel.harrier; }
	
	public void setHarrier(Harrier harrier) { HarrierModel.harrier = harrier; }
	
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
			double angle = Model.rand.nextDouble() * Math.PI / 2;
			foxes.add(
					new Fox(Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_WIDTH - harrier.getVision()) + harrier.getVision() * Math.cos(angle)),
							Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - harrier.getVision()) + harrier.getVision() * Math.sin(angle))));
		}
		while(mice.size() < MAX_MICE) {
			double angle = Model.rand.nextDouble() * Math.PI / 2;
			if(Model.rand.nextInt(GOLD_CHANCE_MOD) % GOLD_CHANCE_MOD == 0) {
				mice.add(
						new GoldenMouse(Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_WIDTH - harrier.getVision()) + harrier.getVision() * Math.cos(angle)),
										Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - harrier.getVision()) + harrier.getVision() * Math.sin(angle))));
			}
			else {
				mice.add(
						new Mouse(Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_WIDTH - harrier.getVision()) + harrier.getVision() * Math.cos(angle)),
								  Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - harrier.getVision()) + harrier.getVision() * Math.sin(angle))));
			}
		}
		while(twigs.size() < MAX_TWIGS) {
			double angle = Model.rand.nextDouble() * Math.PI / 2;
			twigs.add(
					new Twig(Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_WIDTH - harrier.getVision()) + harrier.getVision() * Math.cos(angle)),
							 Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - harrier.getVision()) + harrier.getVision() * Math.sin(angle))));
		}
		while(trees.size() < MAX_TREES) {
			double angle = Model.rand.nextDouble() * Math.PI / 2;
			trees.add(
					new Tree(Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_WIDTH - harrier.getVision()) + harrier.getVision() * Math.cos(angle)),
							 Model.randomSign() * (Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - harrier.getVision()) + harrier.getVision() * Math.sin(angle))));
		}
	}
	
	/*
	 * Public method update.
	 * Takes no parameters, returns nothing.
	 * Updates model variables.
	 */
	@Override
	public void update() {
		harrier.move();
		for(Fox f : foxes) { f.move(); f.twitch(30, rand.nextInt()); }
		for(Mouse m : mice) { m.move(); m.twitch(20, rand.nextInt()); }
		checkInteractions();
		if (tutorial) { 
			Tree tree = new Tree(100, 100);
			Twig twig = new Twig(100, 150);
			Mouse mouse = new Mouse(100, 100);
			Fox fox1 = new Fox(-150, 75);
			Fox fox2 = new Fox(-150, -75);
			Fox fox3 = new Fox(150, 75);
			Fox fox4 = new Fox(150, -75);
			if (!tutorial1) {
				if (harrier.goneNorth && harrier.goneSouth && harrier.goneEast && harrier.goneWest) {
					tutorial1 = true;
					harrier.setXVel(0);
					harrier.setYVel(0);
					harrier.setYPos(0);
					harrier.setXPos(0);
					trees.add(tree);
					tutorialState = Tutorial.TWO;
				}
			} else if (!tutorial2) {
				if (harrier.treeHit) {
					tutorial2 = true;
					trees.remove(0);
					twigs.add(twig);
					mice.add(mouse);
					harrier.setXVel(0);
					harrier.setYVel(0);
					harrier.setYPos(0);
					harrier.setXPos(0);
					tutorialState = Tutorial.THREE;
				}
			} else if (!tutorial3) {
				if (harrier.twigHit && harrier.mouseHit) {
					harrier.setVision(harrier.INITIALVISION);
					foxes.add(fox1);
					foxes.add(fox2);
					foxes.add(fox3);
					foxes.add(fox4);
					tutorial3 = true;
					harrier.setXVel(0);
					harrier.setYVel(0);
					harrier.setYPos(0);
					harrier.setXPos(0);
					tutorialState = Tutorial.FOUR;
				}
			} else if (!tutorial4) {
				if (harrier.foxHit) {
					tutorial4 = true;
					tutorial = false;
					foxes.remove(0);
					foxes.remove(0);
					foxes.remove(0);
					foxes.remove(0);
					harrier.setXVel(0);
					harrier.setYVel(0);
					harrier.setYPos(0);
					harrier.setXPos(0);
					tutorialState = Tutorial.NONE;
				}
			}
		} else {
			setTime(getTime() + 1);
			generate();
		}
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
		
		/*for(Fox f : foxes) {
			for(GameObject m : mice) {
				if(isCollision(f, m)) { f.interact(m); }
			}
			for(GameObject tr : trees) {
				if(isCollision(f, tr)) { f.interact(tr); }
			}
		}
		for(Mouse m : mice) {
			for(GameObject f : foxes) {
				if(isCollision(m, f)) { m.interact(f); }
			}
			for(GameObject tr : trees) {
				if(isCollision(m, tr)) { m.interact(tr); }
			}
		}
		
		for(int i = 0; i < foxes.size(); i++) {
			for(int j = 0; j < foxes.size(); j++) {
				if(i != j) {
					Animal interacter = foxes.get(i);
					GameObject interacted = foxes.get(j);
					if(isCollision(interacter, interacted)) { interacter.interact(interacted); }
				}
			}
		}
		for(int i = 0; i < mice.size(); i++) {
			for(int j = 0; j < mice.size(); j++) {
				if(i != j) {
					Animal interacter = mice.get(i);
					GameObject interacted = mice.get(j);
					if(isCollision(interacter, interacted)) { interacter.interact(interacted); }
				}
			}
		}*/
	}
	
	/*private void interactOthers(ArrayList<Animal> interacters, ArrayList<GameObject> interacteds) {
		for(Animal irs : interacters) {
			for(GameObject ids : interacteds) {
				if(isCollision(irs, ids)) { irs.interact(ids); }
			}
		}
	}
	
	private void interactSelf(ArrayList<Animal> interactees) {
		for(int i = 0; i < interactees.size(); i++) {
			for(int j = 0; i < interactees.size(); j++) {
				if(i != j) {
					Animal interacter = interactees.get(i);
					GameObject interacted = interactees.get(j);
					if(isCollision(interacter, interacted)) { interacter.interact(interacted); }
				}
			}
		}
	}*/

}