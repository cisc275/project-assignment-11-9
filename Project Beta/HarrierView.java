//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.geom.AffineTransform;
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
	HarrierModel.Tutorial state = HarrierModel.Tutorial.NONE;
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
	public void update(Harrier harrier, ArrayList<Fox> foxes, ArrayList<Mouse> mice, ArrayList<Twig> twigs, ArrayList<Tree> trees, HarrierModel.Tutorial state) {
		this.harrier = harrier;
		this.foxes = foxes;
		this.mice = mice;
		this.twigs = twigs;
		this.trees = trees;
		this.state = state;
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
		images = new BufferedImage[44];
		images[0] = createBufferedImage("Harrier Background.png");
		initializeImageSet("Harrier", 1);
		initializeImageSet("Fox", 9);
		initializeImageSet("Mouse", 17);
		images[25] = createBufferedImage("Twig.png");
		images[26] = createBufferedImage("Tree.png");
		images[27] = createBufferedImage("Vision.png");
		images[28] = createBufferedImage("EnemySound.png");
		images[29] = createBufferedImage("EnemySound.png");
		images[30] = createBufferedImage("EnemySound.png");
		images[31] = createBufferedImage("EnemySound.png");
		AffineTransform tx1 = new AffineTransform();
		tx1.rotate(Math.PI/2, images[28].getWidth()/2, images[28].getHeight()/2);
		AffineTransform tx2 = new AffineTransform();
		tx2.rotate(Math.PI, images[28].getWidth()/2, images[28].getHeight()/2);
		AffineTransform tx3 = new AffineTransform();
		tx3.rotate(Math.PI*3/2, images[28].getWidth()/2, images[28].getHeight()/2);
		AffineTransformOp op1 = new AffineTransformOp(tx1, AffineTransformOp.TYPE_BILINEAR);
		AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
		AffineTransformOp op3 = new AffineTransformOp(tx3, AffineTransformOp.TYPE_BILINEAR);
		images[29] = op1.filter(images[29], null);
		images[30] = op2.filter(images[30], null);
		images[31] = op3.filter(images[31], null);
		images[32] = createBufferedImage("PreySound.png");
		images[33] = createBufferedImage("PreySound.png");
		images[34] = createBufferedImage("PreySound.png");
		images[35] = createBufferedImage("PreySound.png");
		images[33] = op1.filter(images[33], null);
		images[34] = op2.filter(images[34], null);
		images[35] = op3.filter(images[35], null);
		images[36] = createBufferedImage("UpArrow.png");
		images[37] = createBufferedImage("RightArrow.png");
		images[38] = createBufferedImage("DownArrow.png");
		images[39] = createBufferedImage("LeftArrow.png");
		/*images[40] = createBufferedImage("GenericArrow.png");
		images[41] = createBufferedImage("GenericArrow.png");
		images[42] = createBufferedImage("GenericArrow.png");
		images[43] = createBufferedImage("GenericArrow.png");
		images[40] = op1.filter(images[40], null);
		images[41] = op2.filter(images[41], null);
		images[42] = op3.filter(images[42], null);*/
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
		switch(state) {
		case ONE:
			g.drawImage(images[36], TitleView.FRAME_WIDTH / 6, TitleView.FRAME_HEIGHT / 2 - 40, 80, 80, this);
			break;
		case TWO:
			g.drawImage(images[37], TitleView.FRAME_WIDTH / 6, TitleView.FRAME_HEIGHT / 2 - 40, 80, 80, this);
			break;
		case THREE:
			g.drawImage(images[38], TitleView.FRAME_WIDTH / 6, TitleView.FRAME_HEIGHT / 2 - 40, 80, 80, this);
			break;
		case FOUR:
			g.drawImage(images[39], TitleView.FRAME_WIDTH / 6, TitleView.FRAME_HEIGHT / 2 - 40, 80, 80, this);
			break;
		default:
			break;
		}
		g.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		g.drawString("Score " + harrier.getScore(), 1450, 20);
		g.drawString("Press P to pause",  1400, 40);
		g.drawString("Press ESC to return to menu",  1325,  60);
		Ellipse2D.Double ellipse = new Ellipse2D.Double(TitleView.FRAME_WIDTH / 2 - harrier.getVision(),
				TitleView.FRAME_HEIGHT / 2 - harrier.getVision(), harrier.getVision() * 2, harrier.getVision()*2);
		g.setClip(ellipse);
		super.paintComponent(g);
		g.drawImage(images[0], 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, null);
		int hx = TitleView.FRAME_WIDTH / 2 - (int)(harrier.getXWidth() / 2);
		int hy = TitleView.FRAME_HEIGHT / 2 - (int)(harrier.getYWidth() / 2);
		g.drawImage(animationFrames[harrier.getAnimation() + 4 * directionConverter(harrier.getDirection())], hx, hy, (int)harrier.getXWidth(), (int)harrier.getYWidth(), this);
		if(isDebug) { g.drawRect(hx, hy, (int)harrier.getXWidth(), (int)harrier.getYWidth()); }
		g.setColor(Color.RED);
		for(Fox f : foxes) {
			int x = (int)(f.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(f.getXWidth() / 2);
			int y = (int)(f.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(f.getYWidth() / 2);
			g.drawImage(animationFrames[f.getAnimation() + 32 + 4 * directionConverter(f.getDirection())], x, y, (int)f.getXWidth(), (int)f.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)f.getXWidth(), (int)f.getYWidth()); }
			double myRadius = f.radius(harrier.getYPos(), harrier.getXPos());
			if (myRadius + 10 >= harrier.getVision()) {
				switch(f.getApproximateDirection(harrier.getYPos(), harrier.getXPos())) {
				case NORTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
						g.drawImage(images[29], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 - (int) harrier.getVision(), 30, 30, this);
						//g.fillOval(TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 - (int)harrier.getVision(), 20, 20);
					} else {
						g.drawImage(images[29], TitleView.FRAME_WIDTH / 2 - 30, 0, 30, 30, this);
						//g.fillOval(TitleView.FRAME_WIDTH / 2 - 30, 0, 20, 20);
					}
					break;
				case SOUTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
						g.drawImage(images[31], TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 + (int) harrier.getVision() - 30, 30, 30, this);
						//g.fillOval(TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 + (int)harrier.getVision() - 20, 20, 20);
					} else {
						g.drawImage(images[31], TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT - 30, 30, 30, this);
						//g.fillOval(TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT - 20, 20, 20);
					}
					break;
				case WEST:
					g.drawImage(images[28], TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / 2 - 30, 30, 30, this);
					//g.fillOval(TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / 2 - 30, 20, 20);
					break;
				case EAST:
					g.drawImage(images[30], TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 30, TitleView.FRAME_HEIGHT / 2 + 10, 30, 30, this);
					//g.fillOval(TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 20, TitleView.FRAME_HEIGHT / 2 + 10, 20, 20);
					break;
				}
			}
		}
		g.setColor(Color.GREEN);
		for(Mouse m : mice) {
			int x = (int)(m.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(m.getXWidth() / 2);
			int y = (int)(m.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(m.getYWidth() / 2);
			g.drawImage(images[17 + directionConverter(m.getDirection())], x, y, (int)m.getXWidth(), (int)m.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)m.getXWidth(), (int)m.getYWidth()); }
			double myRadius = m.radius(harrier.getYPos(), harrier.getXPos());
			if (myRadius + 10 >= harrier.getVision()) {
				switch(m.getApproximateDirection(harrier.getYPos(), harrier.getXPos())) {
				case NORTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
						g.drawImage(images[33], TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 - (int) harrier.getVision(), 30, 30, this);
						if (state == HarrierModel.Tutorial.SIX) {
							g.drawImage(images[36], TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 - (int) harrier.getVision() + 40, 30, 30, this);
						}
						//g.fillOval(TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 - (int)harrier.getVision(), 20, 20);
					} else {
						g.drawImage(images[33], TitleView.FRAME_WIDTH / 2 + 10, 0, 30, 30, this);
						//g.fillOval(TitleView.FRAME_WIDTH / 2 + 10, 0, 20, 20);
					}
					break;
				case SOUTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
						g.drawImage(images[35], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 + (int) harrier.getVision() - 30, 30, 30, this);
						if (state == HarrierModel.Tutorial.SIX) {
							g.drawImage(images[38], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 + (int) harrier.getVision() - 30 - 40, 30, 30, this);
						}
						//g.fillOval(TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 + (int)harrier.getVision() - 20, 20, 20);
					} else {
						g.drawImage(images[35], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT - 30, 30, 30, this);
						//g.fillOval(TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT - 20, 20, 20);
					}
					break;
				case WEST:
					g.drawImage(images[32], TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / 2 + 10, 30, 30, this);
					if (state == HarrierModel.Tutorial.SIX) {
						g.drawImage(images[39], TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision() + 40, TitleView.FRAME_HEIGHT / 2 + 10, 30, 30, this);
					}
					//g.fillOval(TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / 2 + 10, 20, 20);
					break;
				case EAST:
					g.drawImage(images[34], TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 30, TitleView.FRAME_HEIGHT / 2 - 30, 30, 30, this);
					if (state == HarrierModel.Tutorial.SIX) {
						g.drawImage(images[37], TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 30 - 40, TitleView.FRAME_HEIGHT / 2 - 30, 30, 30, this);
					}
					//g.fillOval(TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 20, TitleView.FRAME_HEIGHT / 2 - 30, 20, 20);
					break;
				}
			}
		}
		for(Twig tw : twigs) {
			int x = (int)(tw.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tw.getXWidth() / 2);
			int y = (int)(tw.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tw.getYWidth() / 2);
			g.drawImage(images[25], x, y, (int)tw.getXWidth(), (int)tw.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)tw.getXWidth(), (int)tw.getYWidth()); }
			if (state == HarrierModel.Tutorial.FIVE) {
				double myRadius = tw.radius(harrier.getYPos(), harrier.getXPos());
				if (myRadius >= harrier.getVision()) {
					switch(tw.getApproximateDirection(harrier.getYPos(), harrier.getXPos())) {
					case NORTH:
						if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
							g.drawImage(images[36], TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 - (int) harrier.getVision(), 30, 30, this);
							//g.fillOval(TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 - (int)harrier.getVision(), 20, 20);
						} else {
							g.drawImage(images[36], TitleView.FRAME_WIDTH / 2 + 10, 0, 30, 30, this);
							//g.fillOval(TitleView.FRAME_WIDTH / 2 + 10, 0, 20, 20);
						}
						break;
					case SOUTH:
						if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
							g.drawImage(images[38], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 + (int) harrier.getVision() - 30, 30, 30, this);
							//g.fillOval(TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 + (int)harrier.getVision() - 20, 20, 20);
						} else {
							g.drawImage(images[38], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT - 30, 30, 30, this);
							//g.fillOval(TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT - 20, 20, 20);
						}
						break;
					case WEST:
						g.drawImage(images[39], TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / 2 + 10, 30, 30, this);
						//g.fillOval(TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / 2 + 10, 20, 20);
						break;
					case EAST:
						g.drawImage(images[37], TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 30, TitleView.FRAME_HEIGHT / 2 - 30, 30, 30, this);
						//g.fillOval(TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 20, TitleView.FRAME_HEIGHT / 2 - 30, 20, 20);
						break;
					}
				}
			}
		}
		g.setColor(Color.RED);
		for(Tree tr : trees) {
			int x = (int)(tr.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tr.getXWidth() / 2);
			int y = (int)(tr.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tr.getYWidth() / 2);
			g.drawImage(images[26], x, y, (int)tr.getXWidth(), (int)tr.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)tr.getXWidth(), (int)tr.getYWidth()); }
		}
		g.fillRect(0, 0 - TitleView.FRAME_HEIGHT/2 - (int)harrier.getYPos(), TitleView.FRAME_WIDTH, 10);
		g.fillRect(0, TitleView.FRAME_HEIGHT*3/2 - (int)harrier.getYPos(), TitleView.FRAME_WIDTH, 10);
		g.fillRect(0 - TitleView.FRAME_WIDTH/2 - (int)harrier.getXPos(), 0, 10, TitleView.FRAME_HEIGHT);
		g.fillRect(TitleView.FRAME_WIDTH*3/2 - (int)harrier.getXPos(), 0, 10, TitleView.FRAME_HEIGHT);
		/*g.drawImage(images[27], 
						(int) (0 - harrier.getVision()*20), (int) (0 - harrier.getVision()*20),
						(int) (TitleView.FRAME_WIDTH + harrier.getVision()*20), (int) (TitleView.FRAME_HEIGHT + harrier.getVision()*20),
						0, 0, images[6].getWidth(this), images[6].getHeight(this), this);*/
	}

}