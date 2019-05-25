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
	private final static double WATER_HEIGHT = TitleView.FRAME_HEIGHT * .6;
	private final static int MAX_FISH = 5;
	private final static int MAX_SEAWEED = 8;
	private final static int GOLD_CHANCE_MOD = 25;
	private final static double AIR_DRAG = .0001;
	private final static double WATER_DRAG = .001;
	private final static double FINISH_LINE = 45000;
	private final static int TUT_FISH_SIZE = 3;
	private final static double TUT_FISH_Y_OFFSET = 300;
	private final static double TUT_X_OFFSET_1 = 100;
	private final static double TUT_X_OFFSET_2 = 200;
	private final static double TUT_X_OFFSET_3 = 300;
	private final static double TUT_X_OFFSET_4 = 400;
	private final static double TUT_Y_OFFSET_1 = 160;
	private final static double TUT_Y_OFFSET_2 = 240;
	private final static double TUT_Y_OFFSET_3 = 320;
	private final static double TUT_Y_OFFSET_4 = 400;
	private final static double TUT_Y_OFFSET_5 = 480;
	private final static int X_COORD = 0;
	private final static int Y_COORD = 1;
	private final static int TIME_TO_CLOCK = 50;

	public OspreyModel(){
		super();
		osprey = new Osprey();
		fish = new ArrayList<>();
		seaweed = new ArrayList<>();
		stage = Tutorial.ONE;
	}

	public Osprey getOsprey() { return this.osprey; }
	
	public void setOsprey(Osprey osprey) { this.osprey = osprey; }
	
	public ArrayList<Fish> getFish() { return this.fish; }
	
	public void setFish(ArrayList<Fish> fish) { this.fish = fish; }
	
	public ArrayList<Seaweed> getSeaweed() { return this.seaweed; }
	
	public void setSeaweed(ArrayList<Seaweed> seaweed) { this.seaweed = seaweed; }
	
	/* 
	 * Public method isLoss.
	 * Parameters: none
	 * Returns: boolean
	 * Returns a boolean signifying if the osprey is going too slow.
	 */
	@Override
	public boolean isLoss() {
		return osprey.getXVel() <= Osprey.MIN_SPEED;
	}

	/*
	 * Public method isWin.
	 * Parameters: none
	 * Returns: boolean
	 * Returns a boolean signifying if the osprey has flown to the finish line.
	 */
	@Override
	public boolean isWin() {
		return osprey.getXPos() >= FINISH_LINE;
	}

	/* 
	 * Public method generate.
	 * Parameters: none
	 * Returns: nothing
	 * Adds fish and seaweed to the model.
	 */
	@Override
	public void generate() {
		while(fish.size() < MAX_FISH) {
			double[] coords = genCoords();
			if(Model.rand.nextInt(GOLD_CHANCE_MOD) % GOLD_CHANCE_MOD == 0) {
				GoldenFish gf = new GoldenFish(coords[X_COORD],coords[Y_COORD]);
				if(generationHelper(gf)) { fish.add(gf); }
			}
			else {
				Fish f = new Fish(coords[X_COORD],coords[Y_COORD],Model.rand.nextInt(Fish.MAX_SIZE - Fish.MIN_SIZE + 1) + Fish.MIN_SIZE); 
				if(generationHelper(f)) { fish.add(f); }
			}
		}
		while(seaweed.size() < MAX_SEAWEED) {
			double[] coords = genCoords();
			Seaweed s = new Seaweed(coords[X_COORD],coords[Y_COORD]);
			if(generationHelper(s)) { seaweed.add(s); }
		}
	}
	
	/* 
	 * Private method genCoords.
	 * Parameters: none
	 * Returns: double[]
	 * Creates an x and y pair for GameObjects to spawn on.
	 */
	private double[] genCoords() {
		double[] coords = {Model.rand.nextDouble() * TitleView.FRAME_WIDTH + TitleView.FRAME_WIDTH + osprey.getXPos(), 
				   		   Model.rand.nextDouble() * (TitleView.FRAME_HEIGHT - WATER_HEIGHT) / 2 + WATER_HEIGHT};
		return coords;
	}
	
	/* 
	 * Private method generationHelper.
	 * Parameters:
	 * 		GameObject: go
	 * Returns: boolean
	 * Returns a boolean indicating whether the GameObject can be spawned, i.e. is not overlapping another GameObject.
	 */
	private boolean generationHelper(GameObject go) {
		boolean flag = true;
		for(Fish f : fish) { if(isCollision(go, f)) { flag = false; }}
		for(Seaweed s : seaweed) { if(isCollision(go, s)) { flag = false; }}
		return flag;
	}
	
	/* 
	 * Public method destroy.
	 * Parameters: none
	 * Returns: nothing
	 * Removes fish and seaweed from the model that are unreachable.
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
	 * Parameters: none
	 * Returns: nothing
	 * Handles the staging of the game and updates model variables respectively.
	 */
	@Override
	public void update() {
		if(osprey.getYPos() <= Osprey.MIN_HEIGHT) { osprey.setIsRecovering(false); }
		osprey.move(); osprey.incrementAnimation();
		for(Fish f : fish) { f.move(); f.incrementAnimation(); }
		checkInteractions();
		destroy();
		applyStaging();
	}
	
	/* 
	 * Private method applyStaging.
	 * Parameters: none
	 * Returns: nothing
	 * Handles the staging of the game.
	 */
	private void applyStaging() {
		switch (stage) {
		case ONE:
			if (osprey.getYPos() > WATER_HEIGHT) {
				stage = Tutorial.TWO;
				Fish f = new Fish(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_FISH_Y_OFFSET, TUT_FISH_SIZE);
				fish.add(f);
			}
			break;
		case TWO:
			if (osprey.getXVel() > osprey.START_SPEED) {
				stage = Tutorial.THREE;
				Seaweed s1 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_1);
				seaweed.add(s1);
				Seaweed s2 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_2);
				seaweed.add(s2);
				Seaweed s3 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_3);
				seaweed.add(s3);
				Seaweed s4 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_4);
				seaweed.add(s4);
				Seaweed s5 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_5);
				seaweed.add(s5);
				Seaweed s6 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + TUT_X_OFFSET_1, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_5);
				seaweed.add(s6);
				Seaweed s7 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + TUT_X_OFFSET_2, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_5);
				seaweed.add(s7);
				Seaweed s8 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + TUT_X_OFFSET_3, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_5);
				seaweed.add(s8);
				Seaweed s9 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + TUT_X_OFFSET_4, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_1);
				seaweed.add(s9);
				Seaweed s10 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + TUT_X_OFFSET_4, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_2);
				seaweed.add(s10);
				Seaweed s11 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + TUT_X_OFFSET_4, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_3);
				seaweed.add(s11);
				Seaweed s12 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + TUT_X_OFFSET_4, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_4);
				seaweed.add(s12);
				Seaweed s13 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH + TUT_X_OFFSET_4, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_5);
				seaweed.add(s13);
				Fish f = new Fish(osprey.getXPos() + TitleView.FRAME_WIDTH + TUT_X_OFFSET_2, TitleView.FRAME_HEIGHT - TUT_FISH_Y_OFFSET, TUT_FISH_SIZE);
				fish.add(f);
			} else {
				if (fish.size() < 1) {
					Fish f = new Fish(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_FISH_Y_OFFSET, TUT_FISH_SIZE);
					fish.add(f);
				}
			}
			break;
		case THREE:
			if (osprey.getXVel() < osprey.START_SPEED + 1) {
				stage = Tutorial.FOUR;
				GoldenFish f = new GoldenFish(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_3, true);
				fish.add(f);
			} else {
				if (seaweed.size() == 8) {
					Seaweed s1 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_1);
					seaweed.add(s1);
					Seaweed s2 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_2);
					seaweed.add(s2);
					Seaweed s3 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_3);
					seaweed.add(s3);
					Seaweed s4 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_4);
					seaweed.add(s4);
					Seaweed s5 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_5);
					seaweed.add(s5);
				} else if (seaweed.size() < 13) {
					Seaweed s6 = new Seaweed(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_5);
					seaweed.add(s6);
				}
				if (fish.size() < 1) {
					Fish f = new Fish(osprey.getXPos() + TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT - TUT_FISH_Y_OFFSET, TUT_FISH_SIZE);
					fish.add(f);
				}
			}
			break;
		case FOUR:
			if (osprey.getXVel() > osprey.START_SPEED + 1) {
				stage = Tutorial.NONE;
			} else {
				if (fish.size() < 1) {
					GoldenFish f = new GoldenFish(osprey.getXPos() + TitleView.FRAME_WIDTH,
							TitleView.FRAME_HEIGHT - TUT_Y_OFFSET_3, true);
					fish.add(f);
				}
			}
			break;
		case NONE:
			applyResistance();
			setTime(getTime() + 1);
			gameClock();
			generate();
			break;
		}
	}
	
	/*
	 * Public method applyResistance.
	 * Parameters: none
	 * Returns: nothing
	 * Applies appropriate air or water resistance depending on osprey's y position.
	 */
	public void applyResistance() {
		if(osprey.getYPos() < WATER_HEIGHT) { osprey.setXVel(osprey.getXVel() * (1 - AIR_DRAG)); }
		else { osprey.setXVel(osprey.getXVel() * (1 - WATER_DRAG)); }
	}
	
	/*
	 * Public method checkInteractions.
	 * Parameters: none
	 * Returns: nothing
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
	
	/*
	 * Public method gameClock.
	 * Parameters: none
	 * Returns: nothing
	 * Counts time elapsed from the end of the tutorial.
	 */
	public void gameClock() {
		if(getTime() % TIME_TO_CLOCK == 0) {
			osprey.gameTimer += 1;
		}
	}

}
