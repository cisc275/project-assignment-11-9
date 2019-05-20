//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.*;


public class OspreyView extends GameView {

	private Osprey osprey;
	private ArrayList<Fish> fish;
	private ArrayList<Seaweed> seaweed;
	OspreyModel.Tutorial state;
	final static int X_OFFSET = 100;
	final static int Y_OFFSET = 100;
	private FileReader readFile = null;
	private BufferedReader reader = null;
	private String fastTime;

	public OspreyView() {
		setOpaque(true);
		setBackground(Color.blue);
		fastTime = "";
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
	 * private method initializeImages.
	 * Takes no parameter and returns nothing.
	 * Reads the images into the images array.
	 */
	private void initializeImages() {
		images = new BufferedImage[7];
		images[0] = createBufferedImage("Osprey Background.png");
		images[1] = createBufferedImage("Osprey.png");
		images[2] = createBufferedImage("FishEast.png");
		images[3] = createBufferedImage("FishWest.png");
		images[4] = createBufferedImage("Seaweed.png");
		images[5] = createBufferedImage("GreenArrow.png");
		images[6] = createBufferedImage("SpaceBar.png");
		makeFrames();
	}

	/*
	 * private method makeFrames.
	 * Takes no parameter and returns nothing.
	 * Splits those images into their respective frames for animation.
	 */
	private void makeFrames() {
		animationFrames = new BufferedImage[12];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++){
				animationFrames[j + 4*i] = images[1 + i].getSubimage(165*j, 0, 165, 140);
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
			return "Nobody:0";
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
		if(osprey.gameTimer > Integer.parseInt((fastTime.split(":")[1]))) {
			String name = JOptionPane.showInputDialog("Congratulations on setting a new HighScore! Please enter your initials.");
			while(name == null || name.length() >= 4 || filterInput(name).equals("no")) {
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
	 * public method paintComponent.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the images onto the graphics object for display.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(images[0], 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		if (state == OspreyModel.Tutorial.ONE) {
			g.drawImage(images[6], TitleView.FRAME_WIDTH / 2 - 120, TitleView.FRAME_HEIGHT / 3 - 20, 240, 40, this);
		}
		int ox = X_OFFSET - (int)(osprey.getXWidth() / 2);
		int oy = (int)osprey.getYPos() + Y_OFFSET - (int)(osprey.getYWidth() / 2);
		g.drawImage(animationFrames[osprey.getAnimation()], ox, oy, (int)osprey.getXWidth(), (int)osprey.getYWidth(), this);
		if(isDebug) { g.drawRect(ox, oy, (int)osprey.getXWidth(), (int)osprey.getYWidth()); }
		for(Fish f : fish) {
			int x = (int)(f.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(f.getXWidth() / 2);
			int y = (int)f.getYPos() + Y_OFFSET - (int)(f.getYWidth() / 2);
			g.drawImage(animationFrames[f.getAnimation() + 4 + 4* directionConverter(f.getDirection())], x, y, (int)f.getXWidth(), (int)f.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)f.getXWidth(), (int)f.getYWidth()); }
			if (state != OspreyModel.Tutorial.NONE) {
				g.drawImage(images[5], x, y - 80, 40, 40, this);
			}
		}
		for(Seaweed s : seaweed) {
			int x = (int)(s.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(s.getXWidth() / 2);
			int y = (int)s.getYPos() + Y_OFFSET - (int)(s.getYWidth() / 2);
			g.drawImage(images[4], x, y, (int)s.getXWidth(), (int)s.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)s.getXWidth(), (int)s.getYWidth()); }
		}
		g.drawString("Time: " + osprey.gameTimer + "s", 1450, 20);
		g.drawString("Press P to pause",  1410, 40);
		g.drawString("Press ESC to return to menu",  1350,  60);
	}

}