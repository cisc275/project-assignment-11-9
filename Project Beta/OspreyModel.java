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
	private final static int MAX_FISH = 5;
	private final static int MAX_SEAWEED = 8;
	private final static int GOLD_CHANCE_MOD = 25;
	private final static double AIR_DRAG = .0001;
	private final static double WATER_DRAG = .001;
	Tutorial state = Tutorial.ONE;

	public enum Tutorial {
		ONE, TWO, THREE, FOUR, NONE;
	}
	
	public Tutorial getTutorial() {
		return state;
	}
	
	public OspreyModel(){
		super();
		osprey = new Osprey();
		fish = new ArrayList<>();
		seaweed = new ArrayList<>();
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
		return osprey.getXVel() <= Osprey.MIN_SPEED;
	}

	/*
	 * Public method isWin.
	 * Takes no parameters, returns a boolean signifying if the game was won.
	 */
	@Override
	public boolean isWin() {
		return osprey.getXPos() >= 45000;
	}

	/* 
	 * Public method generate.
	 * Takes no parameters and returns nothing.
	 * Adds relevant GameObjects to the model.
	 */
	@Override
	public void generate() {
		while(fish.size() < MAX_FISH) {
			double[] coords = genCoords();
			if(Model.rand.nextInt(GOLD_CHANCE_MOD) % GOLD_CHANCE_MOD == 0) {
				GoldenFish gf = new GoldenFish(coords[0],coords[1]);
				if(generationHelper(gf)) { fish.add(gf); }
			}
			else {
				Fish f = new Fish(coords[0],coords[1],Model.rand.nextInt(3) + 1); 
				if(generationHelper(f)) { fish.add(f); }
			}
		}
		while(seaweed.size() < MAX_SEAWEED) {
			double[] coords = genCoords();
			Seaweed s = new Seaweed(coords[0],coords[1]);
			if(generationHelper(s)) { seaweed.add(s); }
		}
	}
	
	/*
	 * private method genCoords.
	 * Takes no parameters, returns double[].
	 * Generates a pair of coordinates for the osprey game.
	 */
	private double[] genCoords() {
		double[] coords = {Model.rand.nextDouble() * TitleView.FRAME_WIDTH + TitleView.FRAME_WIDTH + osprey.getXPos(), 
				   		   Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - WATER_HEIGHT) / 2 + WATER_HEIGHT};
		return coords;
	}
	
	/*
	 * private method generationHelper.
	 * Takes GameObject as parameter, and returns boolean.
	 * Checks if the GameObject will be in contact with other GameObjects.
	 */
	private boolean generationHelper(GameObject go) {
		boolean flag = true;
		for(Fish f : fish) { if(isCollision(go, f)) { flag = false; }}
		for(Seaweed s : seaweed) { if(isCollision(go, s)) { flag = false; }}
		return flag;
	}
	
	/*
	 * public method destroy.
	 * Takes no parameters, and returns nothing.
	 * Checks if any GameObjects are behind the osprey and out of view to be deleted.
	 */
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
		if(osprey.getYPos() <= Osprey.MIN_HEIGHT) { osprey.setIsRecovering(false); }
		osprey.move(); osprey.incrementAnimation();
		for(Fish f : fish) { f.move(); f.incrementAnimation(); }
		checkInteractions();
		destroy();
		switch(state) {
		case ONE:
			if (osprey.getYPos() > WATER_HEIGHT) {
				state = Tutorial.TWO;
				double[] coords = genCoords();
				Fish f = new Fish(osprey.getXPos() + TitleView.FRAME_WIDTH,TitleView.FRAME_HEIGHT - 300, 3);
				fish.add(f);
			}
			break;
		case TWO:
			if (osprey.getXVel() > osprey.START_SPEED) {
				state = Tutorial.THREE;
				Seaweed s1 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 160);
				seaweed.add(s1);
				Seaweed s2 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 240);
				seaweed.add(s2);
				Seaweed s3 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 320);
				seaweed.add(s3);
				Seaweed s4 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 400);
				seaweed.add(s4);
				Seaweed s5 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 480);
				seaweed.add(s5);
				Seaweed s6 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + 100, TitleView.FRAME_HEIGHT - 480);
				seaweed.add(s6);
				Seaweed s7 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + 200, TitleView.FRAME_HEIGHT - 480);
				seaweed.add(s7);
				Seaweed s8 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + 300, TitleView.FRAME_HEIGHT - 480);
				seaweed.add(s8);
				Seaweed s9 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + 400, TitleView.FRAME_HEIGHT - 160);
				seaweed.add(s9);
				Seaweed s10 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + 400, TitleView.FRAME_HEIGHT - 240);
				seaweed.add(s10);
				Seaweed s11 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + 400, TitleView.FRAME_HEIGHT - 320);
				seaweed.add(s11);
				Seaweed s12 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + 400, TitleView.FRAME_HEIGHT - 400);
				seaweed.add(s12);
				Seaweed s13 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + 400, TitleView.FRAME_HEIGHT - 480);
				seaweed.add(s13);
				Fish f = new Fish(osprey.getXPos() + TitleView.FRAME_WIDTH + 200, TitleView.FRAME_HEIGHT - 300,2);
				fish.add(f);
			} else {
				if (fish.size() < 1) {
					Fish f = new Fish(osprey.getXPos() + TitleView.FRAME_WIDTH,TitleView.FRAME_HEIGHT - 300, 3);
					fish.add(f);
				}
			}
			break;
		case THREE:
			if (osprey.getXVel() < osprey.START_SPEED + 1) {
				state = Tutorial.FOUR;
				GoldenFish f = new GoldenFish(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 320);
				fish.add(f);
			} else {
				if (seaweed.size() == 8) {
					Seaweed s1 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 160);
					seaweed.add(s1);
					Seaweed s2 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 240);
					seaweed.add(s2);
					Seaweed s3 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 320);
					seaweed.add(s3);
					Seaweed s4 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 400);
					seaweed.add(s4);
					Seaweed s5 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 480);
					seaweed.add(s5);
				} else if (seaweed.size() < 13) {
					Seaweed s6 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 480);
					seaweed.add(s6);
				}
				if (fish.size() < 1) {
					Fish f = new Fish(osprey.getXPos() + TitleView.FRAME_WIDTH,TitleView.FRAME_HEIGHT - 300, 3);
					fish.add(f);
				}
			}
			break;
		case FOUR:
			if (osprey.getXVel() > osprey.START_SPEED + 1) {
				state = Tutorial.NONE;
			} else {
				if (fish.size() < 1) {
					GoldenFish f = new GoldenFish(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - 320);
					fish.add(f);
				}
			}
			break;
		case NONE:
			System.out.println(osprey.getXVel());
			applyResistance();
			setTime(getTime() + 1);
			gameClock();
			generate();
			break;
		}
	}
	
	public void applyResistance() {
		if(osprey.getYPos() < WATER_HEIGHT) { osprey.setXVel(osprey.getXVel() * (1 - AIR_DRAG)); }
		else { osprey.setXVel(osprey.getXVel() * (1 - WATER_DRAG)); }
	}
	
	/*
	 * Runs a game clock during the game in seconds
	 * No Parameters
	 * Void method, returns nothing
	 */
	public void gameClock() {
		if(getTime()%50 == 0) {
			osprey.gameTimer += 1;
		}
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
			for(Fish fi : fish) { if(!f.equals(fi) && isCollision(f,fi)) { f.interact(fi); }}
			for(Seaweed s : seaweed) { if(isCollision(f,s)) { f.interact(s); }}
			if(isCollision(osprey, f) && !osprey.getIsRecovering()) {
				f.interact(osprey);
				fIter.remove();
			}
		}
		for(Seaweed s : seaweed) { if(isCollision(osprey, s)) { s.interact(osprey); }}
	}

}