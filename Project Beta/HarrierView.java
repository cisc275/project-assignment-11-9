//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class HarrierView extends GameView {

	private Harrier harrier;
	private String highScore;
	private ArrayList<Fox> foxes;
	private ArrayList<Mouse> mice;
	private ArrayList<Twig> twigs;
	private ArrayList<Tree> trees;
	private FileReader readFile = null;
	private BufferedReader reader = null;
	private String[] imageEndings = {"East", "North", "Northeast", "Northwest", "South", "Southeast", "Southwest", "West"};

	public HarrierView() {
		setOpaque(true);
		setBackground(Color.green);
		highScore = "";
		harrier = new Harrier();
		foxes = new ArrayList<>();
		mice = new ArrayList<>();
		twigs = new ArrayList<>();
		trees = new ArrayList<>();
		initializeImages();
	}

	/*
	 * public method update.
	 * Takes Harrier, ArrayList<Fox>, and ArrayList<Mouse>, ArrayList<Twig>, ArrayList<Tree> as parameters and returns nothing.
	 * Passes the model objects to the helper for drawing.
	 */
	public void update(Harrier harrier, ArrayList<Fox> foxes, ArrayList<Mouse> mice, ArrayList<Twig> twigs, ArrayList<Tree> trees) {
		this.harrier = harrier;
		this.foxes = foxes;
		this.mice = mice;
		this.twigs = twigs;
		this.trees = trees;
	}
	
	/*
	 * Reads the file containing harrier highscore and assigns the string highscore
	 * Takes in no parameters
	 * Void method, returns nothing
	 */
	public String GetHighScore() {
		//format: Name: 100
		
		try {
			
			readFile = new FileReader("highscore.dat");//possibly change this for monday to a different file so we can let her put her initials
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
	 * Compares final score with highest score to see if a new highscore is reached
	 * Takes in no parameters
	 * Void method, returns nothing
	 */
	public void checkScore() {
		highScore = GetHighScore();
		if(harrier.getScore() > Integer.parseInt((highScore.split(":")[1]))) {
			String name = JOptionPane.showInputDialog("Congratulations on setting a new HighScore! Please enter your name.");
			while(name.length() >= 4 || filterInput(name).equals("no")) {
				name = JOptionPane.showInputDialog("Name Invalid. Try Again");
				}
			highScore = name + ":" + harrier.getScore();

			File scoreFile = new File("highscore.dat");
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
				writer.write(this.highScore);
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
		images = new BufferedImage[28];
		images[0] = createBufferedImage("Harrier Background.png");
		initializeImageSet("Harrier", 1);
		initializeImageSet("Fox", 9);
		initializeImageSet("Mouse", 17);
		images[25] = createBufferedImage("Twig.png");
		images[26] = createBufferedImage("Tree.png");
		images[27] = createBufferedImage("Vision.png");
		makeFrames();
	}

	/*
	 * private method initializeImageSet.
	 * Takes no parameter and returns nothing.
	 * Reads a set of images into the images array.
	 */
	private void initializeImageSet(String fileSet, int offset) {
		for(int i = 0; i < imageEndings.length; i++) {
			String fileName = fileSet;
			fileName += imageEndings[i] + ".png";
			images[offset + i] = createBufferedImage(fileName);
		}
	}

	/*
	 * private method makeFrames.
	 * Takes no parameter and returns nothing.
	 * Splits those images into their respective frames for animation.
	 */
	private void makeFrames() {
		animationFrames = new BufferedImage[64];
		for(int i = 0; i < 16; i++) {
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
		else if(d == Direction.NORTH) { return 1; }
		else if(d == Direction.NORTHEAST) { return 2; }
		else if(d == Direction.NORTHWEST) { return 3; }
		else if(d == Direction.SOUTH) { return 4; }
		else if(d == Direction.SOUTHEAST) { return 5; }
		else if(d == Direction.SOUTHWEST) { return 6; }
		else { return 7; }
	}

	/*
	 * public method paintComponent.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the images onto the graphics object for display.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		g.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		g.drawString("Score: " + harrier.getScore(), 1450, 20);
		g.drawString("Press P to pause", 1400, 40);
		g.drawString("Press ESC to return to menu", 1325, 60);
		Ellipse2D.Double ellipse = new Ellipse2D.Double(TitleView.FRAME_WIDTH / 2 - harrier.getVision(),
				TitleView.FRAME_HEIGHT / 2 - harrier.getVision(), harrier.getVision() * 2, harrier.getVision()*2);
		g.setClip(ellipse);
		super.paintComponent(g);
		g.drawImage(images[0], 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, null);
		int hx = TitleView.FRAME_WIDTH / 2 - (int)(harrier.getXWidth() / 2);
		int hy = TitleView.FRAME_HEIGHT / 2 - (int)(harrier.getYWidth() / 2);
		g.drawImage(animationFrames[harrier.getAnimation() + 4 * directionConverter(harrier.getDirection())], hx, hy, (int)harrier.getXWidth(), (int)harrier.getYWidth(), this);
		if(isDebug) { g.drawRect(hx, hy, (int)harrier.getXWidth(), (int)harrier.getYWidth()); }
		for(Fox f : foxes) {
			int x = (int)(f.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(f.getXWidth() / 2);
			int y = (int)(f.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(f.getYWidth() / 2);
			g.drawImage(animationFrames[f.getAnimation() + 32 + 4 * directionConverter(f.getDirection())], x, y, (int)f.getXWidth(), (int)f.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)f.getXWidth(), (int)f.getYWidth()); }
		}
		for(Mouse m : mice) {
			int x = (int)(m.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(m.getXWidth() / 2);
			int y = (int)(m.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(m.getYWidth() / 2);
			g.drawImage(images[17 + directionConverter(m.getDirection())], x, y, (int)m.getXWidth(), (int)m.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)m.getXWidth(), (int)m.getYWidth()); }
		}
		for(Twig tw : twigs) {
			int x = (int)(tw.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tw.getXWidth() / 2);
			int y = (int)(tw.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tw.getYWidth() / 2);
			g.drawImage(images[25], x, y, (int)tw.getXWidth(), (int)tw.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)tw.getXWidth(), (int)tw.getYWidth()); }
		}
		for(Tree tr : trees) {
			int x = (int)(tr.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tr.getXWidth() / 2);
			int y = (int)(tr.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tr.getYWidth() / 2);
			g.drawImage(images[26], x, y, (int)tr.getXWidth(), (int)tr.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)tr.getXWidth(), (int)tr.getYWidth()); }
		}
		
		/*g.drawImage(images[27], 
						(int) (0 - harrier.getVision()*20), (int) (0 - harrier.getVision()*20),
						(int) (TitleView.FRAME_WIDTH + harrier.getVision()*20), (int) (TitleView.FRAME_HEIGHT + harrier.getVision()*20),
						0, 0, images[6].getWidth(this), images[6].getHeight(this), this);*/
	}
	

}