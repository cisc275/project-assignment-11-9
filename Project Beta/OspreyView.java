//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class OspreyView extends GameView {

	private Osprey osprey;
	private ArrayList<Fish> fish;
	private ArrayList<Seaweed> seaweed;
	OspreyModel.Tutorial state;
	private FileReader readFile;
	private BufferedReader reader;
	private String fastTime;
	final static int X_OFFSET = 100;
	final static int Y_OFFSET = 100;

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
	public void update(Osprey osprey, ArrayList<Fish> fish, ArrayList<Seaweed> seaweed, OspreyModel.Tutorial state) {
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
		//format: Name: 100
		
		try {
			
			readFile = new FileReader("fastTime.dat");//possibly change this for monday to a different file so we can let her put her initials
			reader = new BufferedReader(readFile);
			return reader.readLine();
		}catch(Exception e) {
			return "Nobody:2000";
		}
		finally {
			try {
				if(reader != null) {
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
		images = new BufferedImage[35];
		images[0] = createBufferedImage("Osprey Background.png");
		images[1] = createBufferedImage("Osprey.png");
		images[2] = createBufferedImage("FishEast.png");
		images[3] = createBufferedImage("FishWest.png");
		images[4] = createBufferedImage("Seaweed.png");
		images[5] = createBufferedImage("GreenArrow.png");
		images[6] = createBufferedImage("SpaceBar.png");
		for(int i = 0; i < 13; i++) {
			String fileName = "ob";
			images[7 + i] = createBufferedImage(fileName + (i + 1) + ".png");
		}
		for(int i = 0; i < 13; i++) {
			String fileName = "omap";
			images[20 + i] = createBufferedImage(fileName + (i + 1) + ".png");
		}
		images[33] = createBufferedImage("GoldenFishEast.png");
		images[34] = createBufferedImage("GoldenFishWest.png");
		makeFrames();
	}

	/*
	 * private method makeFrames.
	 * Takes no parameter and returns nothing.
	 * Splits those images into their respective frames for animation.
	 */
	private void makeFrames() {
		animationFrames = new BufferedImage[20];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++){
				animationFrames[j + 4*i] = images[1 + i].getSubimage(165*j, 0, 165, 140);
			}
		}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++){
				animationFrames[j + 4*i + 12] = images[33 + i].getSubimage(165*j, 0, 165, 140);
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
		g.drawImage(images[(int)(osprey.getXPos() / 3500) + 7], 0, 0, TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		g.drawImage(images[(int)(osprey.getXPos() / 3500) + 20], TitleView.FRAME_WIDTH - 175, 10, 150, 150, this);
		if (state == OspreyModel.Tutorial.ONE) {
			g.drawImage(images[6], TitleView.FRAME_WIDTH / 2 - 120, TitleView.FRAME_HEIGHT / 3 - 20, 240, 40, this);
		}
		int ox = X_OFFSET - (int)(osprey.getXWidth() / 2);
		int oy = (int)osprey.getYPos() + Y_OFFSET - (int)(osprey.getYWidth() / 2);
		g.drawImage(animationFrames[osprey.getAnimation()],
					ox - (int)(osprey.getXWidth() / 2), oy - (int)(osprey.getYWidth() / 2), (int)(osprey.getXWidth() * 2), (int)(osprey.getYWidth() * 2), this);
		if(isDebug) { g.drawRect(ox, oy, (int)osprey.getXWidth(), (int)osprey.getYWidth()); }
		for(Fish f : fish) {
			int x = (int)(f.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(f.getXWidth() / 2);
			int y = (int)f.getYPos() + Y_OFFSET - (int)(f.getYWidth() / 2);
			if(f.getSize() == 0) {
				g.drawImage(animationFrames[f.getAnimation() + 12 + 4 * directionConverter(f.getDirection())],
						x - (int)(f.getXWidth()), y - (int)(f.getYWidth() * 1.5), (int)(f.getXWidth() * 3), (int)(f.getYWidth() * 4), this);
			}
			else {
				g.drawImage(animationFrames[f.getAnimation() + 4 + 4* directionConverter(f.getDirection())],
						x - (int)(f.getXWidth()), y - (int)(f.getYWidth() * 1.5), (int)(f.getXWidth() * 3), (int)(f.getYWidth() * 4), this);
			}
			if(isDebug) { g.drawRect(x, y, (int)f.getXWidth(), (int)f.getYWidth()); }
			if (state != OspreyModel.Tutorial.NONE) {
				g.drawImage(images[5], x, y - 80, 40, 40, this);
			}
		}
		for(Seaweed s : seaweed) {
			int x = (int)(s.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(s.getXWidth() / 2);
			int y = (int)s.getYPos() + Y_OFFSET - (int)(s.getYWidth() / 2);
			g.drawImage(images[4], x - (int)(s.getXWidth() / 8), y - (int)(s.getYWidth() / 8), (int)(s.getXWidth() * 1.25), (int)(s.getYWidth() * 1.25), this);
			if(isDebug) { g.drawRect(x, y, (int)s.getXWidth(), (int)s.getYWidth()); }
		}
		g.setColor(Color.WHITE);
		g.drawString("Time: " + osprey.gameTimer + "s", TitleView.FRAME_WIDTH - 180, 175);
		g.drawString("Press P to pause",  TitleView.FRAME_WIDTH - 180, 195);
		g.drawString("Press ESC to return to menu",  TitleView.FRAME_WIDTH - 180,  215);
		g.setColor(Color.BLACK);
	}

}