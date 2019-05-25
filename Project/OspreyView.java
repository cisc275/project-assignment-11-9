//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
/*
 * Public class OspreyView displays the Osprey game.
 */
public class OspreyView extends GameView {

	private Osprey osprey;
	private ArrayList<Fish> fish;
	private ArrayList<Seaweed> seaweed;
	private Tutorial state;
	private FileReader readFile;
	private BufferedReader reader;
	private String fastTime;
	public final static int X_OFFSET = 100;
	public final static int Y_OFFSET = 100;
	private final static int NUM_IMAGES = 34;
	private final static int NUM_ANIMATIONS = 5;
	private final static int NUM_FRAMES = 4;
	private final static int NUM_SLIDES = 13;
	private final static int IMAGE_WIDTH = 165;
	private final static int IMAGE_HEIGHT = 140;
	private final static int BACKGROUND_INDEX = 0;
	private final static int MAP_INDEX = 13;
	private final static int OSPREY_INDEX = 26;
	private final static int FISH_E_INDEX = 27;
	private final static int FISH_W_INDEX = 28;
	private final static int GOLDEN_E_INDEX = 29;
	private final static int GOLDEN_W_INDEX = 30;
	private final static int SEAWEED_INDEX = 31;
	private final static int POINTER_INDEX = 32;
	private final static int SPACEBAR_INDEX = 33;
	private final static double SLIDE_DIVISOR = OspreyModel.FINISH_LINE / NUM_SLIDES;
	private final static int MAP_SIZE = 150;
	private final static int MAP_W_OFFSET = 25;
	private final static int MAP_H_OFFSET = 10;
	private final static int SPACEBAR_WIDTH = 240;
	private final static int SPACEBAR_HEIGHT = 40;
	private final static double OSPREY_SCALAR = 2;
	private final static double FISH_W_SCALAR = 3;
	private final static double FISH_H_SCALAR = 4;
	private final static double SEAWEED_SCALAR = 1.25;
	private final static int OSPREY_OFFSET = 0;
	private final static int FISH_OFFSET = 4;
	private final static int GOLDEN_OFFSET = 12;
	private final static int POINTER_SIZE = 40;
	private final static int RADIUS_DIAM_RATIO = 2;
	private final static int TEXT_W_OFFSET = 180;
	private final static int TEXT_SIZE = 20;

	public OspreyView() {
		setOpaque(true);
		setBackground(Color.blue);
		osprey = new Osprey();
		fish = new ArrayList<>();
		seaweed = new ArrayList<>();
		initializeImages();
	}

	/*
	 * public method update.
	 * Takes Osprey, ArrayList<Fish>, and ArrayList<Seaweed> as parameters and returns nothing.
	 * Passes the model objects to the helper for drawing.
	 */
	public void update(Osprey osprey, ArrayList<Fish> fish, ArrayList<Seaweed> seaweed, Tutorial state) {
		this.osprey = osprey;
		this.fish = fish;
		this.seaweed = seaweed;
		this.state = state;
	}
	
	/*
	 * Reads the file for fastestTime and assigns the string fastTime to that line
	 * No Parameters
	 * Void method, returns nothing
	 */
	public String getFastTime() {
		//format: Name:100
		
		try {
			readFile = new FileReader("fastTime.dat"); // possibly change this for monday to a different file so we can
			reader = new BufferedReader(readFile);
			return reader.readLine();
		} catch (Exception e) {
			return "Nobody:2000";
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Compares final time with fastest time to see if a new highscore is reached
	 * Takes in no parameters
	 * Void method, returns nothing
	 */
	public void checkTime() {
		fastTime = getFastTime();
		if(osprey.gameTimer < Integer.parseInt((fastTime.split(":")[1]))) {
			String name = JOptionPane.showInputDialog("Congratulations on setting a new HighScore! Please enter your initials.");
			while(name == null || filterInput(name).equals("no")) {
			name = JOptionPane.showInputDialog("Name Invalid. Try Again");
			}
			fastTime = name.toUpperCase() + ":" + osprey.gameTimer;

			File scoreFile = new File("fastTime.dat");
			if(!scoreFile.exists()) {
				try {
					scoreFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			FileWriter writeFile = null;
			BufferedWriter writer = null;
			try {
				writeFile = new FileWriter(scoreFile);
				writer = new BufferedWriter(writeFile);
				writer.write(this.fastTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(writer != null) {
						writer.close();
					}
				} catch (Exception e) {}
			}
		}
	}

	/*
	 * private method initializeImages.
	 * Takes no parameter and returns nothing.
	 * Reads the images into the images array.
	 */
	private void initializeImages() {
		images = new BufferedImage[NUM_IMAGES];
		for(int i = 0; i < NUM_SLIDES; i++) {
			String fileName = "OspreyBackground";
			images[BACKGROUND_INDEX + i] = createBufferedImage(fileName + (i + 1) + ".png");
		}
		for(int i = 0; i < NUM_SLIDES; i++) {
			String fileName = "OspreyMap";
			images[MAP_INDEX + i] = createBufferedImage(fileName + (i + 1) + ".png");
		}
		images[OSPREY_INDEX] = createBufferedImage("Osprey.png");
		images[FISH_E_INDEX] = createBufferedImage("FishEast.png");
		images[FISH_W_INDEX] = createBufferedImage("FishWest.png");
		images[GOLDEN_E_INDEX] = createBufferedImage("GoldenFishEast.png");
		images[GOLDEN_W_INDEX] = createBufferedImage("GoldenFishWest.png");
		images[SEAWEED_INDEX] = createBufferedImage("Seaweed.png");
		images[POINTER_INDEX] = createBufferedImage("Pointer.png");
		images[SPACEBAR_INDEX] = createBufferedImage("SpaceBar.png");
		makeFrames();
	}

	/*
	 * private method makeFrames.
	 * Takes no parameter and returns nothing.
	 * Splits those images into their respective frames for animation.
	 */
	private void makeFrames() {
		animationFrames = new BufferedImage[NUM_ANIMATIONS * NUM_FRAMES];
		for(int i = 0; i < NUM_ANIMATIONS; i++) {
			for(int j = 0; j < NUM_FRAMES; j++){
				animationFrames[j + NUM_FRAMES*i] = images[OSPREY_INDEX + i].getSubimage(IMAGE_WIDTH*j, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
			}
		}
	}

	/*
	 * public method directionConverter.
	 * Takes Direction as parameter and returns int.
	 * Interprets the direction into an index.
	 */
	public int directionConverter(Direction d) {
		if(d == Direction.EAST) { return 0; }
		else { return 1; }
	}

	/*
	 * public method paintComponent.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the images onto the graphics object for display.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintMisc(g);
		paintOsprey(g);
		paintFish(g);
		paintSeaweed(g);
		paintText(g);	
	}
	
	/*
	 * private method paintMisc.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws background, mini-map, and tutorial icons.
	 */
	private void paintMisc(Graphics g) {
		g.drawImage(images[(int)(osprey.getXPos() / SLIDE_DIVISOR) + BACKGROUND_INDEX], 0, 0, TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		g.drawImage(images[(int)(osprey.getXPos() / SLIDE_DIVISOR) + MAP_INDEX], TitleView.FRAME_WIDTH - MAP_SIZE - MAP_W_OFFSET, MAP_H_OFFSET, MAP_SIZE, MAP_SIZE, this);
		if (state == Tutorial.ONE) {
			g.drawImage(images[SPACEBAR_INDEX], 
					   (TitleView.FRAME_WIDTH - SPACEBAR_WIDTH) / RADIUS_DIAM_RATIO, (TitleView.FRAME_HEIGHT - SPACEBAR_HEIGHT) / RADIUS_DIAM_RATIO, 
					   SPACEBAR_WIDTH, SPACEBAR_HEIGHT, this);
		}
	}
	
	/*
	 * private method paintOsprey.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the Osprey.
	 */
	private void paintOsprey(Graphics g) {
		int ox = X_OFFSET - (int)(osprey.getXWidth() / RADIUS_DIAM_RATIO);
		int oy = (int)osprey.getYPos() + Y_OFFSET - (int)(osprey.getYWidth() / RADIUS_DIAM_RATIO);
		g.drawImage(animationFrames[osprey.getAnimation() + OSPREY_OFFSET],
					ox - (int)(osprey.getXWidth() * (OSPREY_SCALAR - 1) / RADIUS_DIAM_RATIO),
					oy - (int)(osprey.getYWidth() * (OSPREY_SCALAR - 1) / RADIUS_DIAM_RATIO),
					(int)(osprey.getXWidth() * OSPREY_SCALAR), (int)(osprey.getYWidth() * OSPREY_SCALAR), this);
		if(isDebug) { g.drawRect(ox, oy, (int)osprey.getXWidth(), (int)osprey.getYWidth()); }
	}
	
	/*
	 * private method paintFish.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws fish.
	 */
	private void paintFish(Graphics g) {
		for(Fish f : fish) {
			int x = (int)(f.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(f.getXWidth() / RADIUS_DIAM_RATIO);
			int y = (int)f.getYPos() + Y_OFFSET - (int)(f.getYWidth() / RADIUS_DIAM_RATIO);
			if(f.getSize() == GoldenFish.GOLDEN_SIZE) {
				g.drawImage(animationFrames[f.getAnimation() + GOLDEN_OFFSET + NUM_FRAMES * directionConverter(f.getDirection())],
						x - (int)(f.getXWidth() * (FISH_W_SCALAR - 1) / RADIUS_DIAM_RATIO),
						y - (int)(f.getYWidth() * (FISH_W_SCALAR - 1) / RADIUS_DIAM_RATIO),
						(int)(f.getXWidth() * FISH_W_SCALAR), (int)(f.getYWidth() * FISH_H_SCALAR), this);
			}
			else {
				g.drawImage(animationFrames[f.getAnimation() + FISH_OFFSET + NUM_FRAMES * directionConverter(f.getDirection())],
							x - (int)(f.getXWidth() * (FISH_W_SCALAR - 1) / RADIUS_DIAM_RATIO),
							y - (int)(f.getYWidth() * (FISH_H_SCALAR - 1) / RADIUS_DIAM_RATIO),
							(int)(f.getXWidth() * FISH_W_SCALAR), (int)(f.getYWidth() * FISH_H_SCALAR), this);
			}
			if(isDebug) { g.drawRect(x, y, (int)f.getXWidth(), (int)f.getYWidth()); }
			if (state != Tutorial.NONE) {
				g.drawImage(images[POINTER_INDEX], x, y - 2 * POINTER_SIZE, POINTER_SIZE, POINTER_SIZE, this);
			}
		}
	}
	
	/*
	 * private method paintSeaweed.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws seaweed.
	 */
	private void paintSeaweed(Graphics g) {
		for(Seaweed s : seaweed) {
			int x = (int)(s.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(s.getXWidth() / RADIUS_DIAM_RATIO);
			int y = (int)s.getYPos() + Y_OFFSET - (int)(s.getYWidth() / RADIUS_DIAM_RATIO);
			g.drawImage(images[SEAWEED_INDEX], 
						x - (int)(s.getXWidth() * (SEAWEED_SCALAR - 1) / RADIUS_DIAM_RATIO),
						y - (int)(s.getYWidth() * (SEAWEED_SCALAR - 1) / RADIUS_DIAM_RATIO),
						(int)(s.getXWidth() * SEAWEED_SCALAR), (int)(s.getYWidth() * SEAWEED_SCALAR), this);
			if(isDebug) { g.drawRect(x, y, (int)s.getXWidth(), (int)s.getYWidth()); }
		}
	}
	
	/*
	 * private method paintText.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws text for GUI.
	 */
	private void paintText(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Time: " + osprey.gameTimer + "s", TitleView.FRAME_WIDTH - TEXT_W_OFFSET, MAP_SIZE + MAP_H_OFFSET + TEXT_SIZE);
		g.drawString("Press P to pause",  TitleView.FRAME_WIDTH - TEXT_W_OFFSET, MAP_SIZE + MAP_H_OFFSET + TEXT_SIZE * 2);
		g.drawString("Press ESC to return to menu",  TitleView.FRAME_WIDTH - TEXT_W_OFFSET,  MAP_SIZE + MAP_H_OFFSET + TEXT_SIZE * 3);
		g.setColor(Color.BLACK);
	}

}