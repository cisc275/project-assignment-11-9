//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
/*
 * Public class HarierView displays the Harrier game.
 */
public class HarrierView extends GameView {

	private Harrier harrier;
	private ArrayList<Fox> foxes;
	private ArrayList<Mouse> mice;
	private ArrayList<Twig> twigs;
	private ArrayList<Tree> trees;
	private Tutorial state = Tutorial.NONE;
	private String highScore;
	private FileReader readFile;
	private BufferedReader reader;
	private String[] imageEndings = {"East", "North", "Northeast", "Northwest", "South", "Southeast", "Southwest", "West"};
	private final static double backgroundScalarX = 2400 / (TitleView.FRAME_WIDTH * 3.0);
	private final static double backgroundScalarY = 2400 / (TitleView.FRAME_HEIGHT * 3.0);
	private final static int NUM_IMAGES = 48;
	private final static int NUM_ANIMATIONS = 16;
	private final static int NUM_FRAMES = 4;
	private final static int IMAGE_WIDTH = 165;
	private final static int IMAGE_HEIGHT = 140;

	public HarrierView() {
		setOpaque(true);
		setBackground(Color.green);
		harrier = new Harrier();
		foxes = new ArrayList<>();
		mice = new ArrayList<>();
		twigs = new ArrayList<>();
		trees = new ArrayList<>();
		initializeImages();
	}

	/*
	 * private method initializeImages.
	 * Takes no parameter and returns nothing.
	 * Reads the images into the images array.
	 */
	private void initializeImages() {
		images = new BufferedImage[NUM_IMAGES];
		images[0] = createBufferedImage("HarrierBackground.png");
		initializeImageSet("Harrier", ".png", 1);
		initializeImageSet("Fox", ".png", 9);
		initializeImageSet("Mouse", ".png", 17);
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
		initializeImageSet("GoldenMouse", ".png", 40);
		makeFrames();
	}
	
	/*
	 * private method initializeImageSet.
	 * Takes no parameter and returns nothing.
	 * Reads a set of images into the images array.
	 */
	private void initializeImageSet(String fileSet, String fileType, int offset) {
		for(int i = 0; i < imageEndings.length; i++) {
			String fileName = fileSet;
			fileName += imageEndings[i] + fileType;
			images[offset + i] = createBufferedImage(fileName);
		}
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
				animationFrames[j + NUM_FRAMES*i] = images[1 + i].getSubimage(IMAGE_WIDTH*j, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
			}
		}
	}
	
	/*
	 * public method update.
	 * Takes Harrier, ArrayList<Fox>, and ArrayList<Mouse>, ArrayList<Twig>, ArrayList<Tree> as parameters and returns nothing.
	 * Passes the model objects to the helper for drawing.
	 */
	public void update(Harrier harrier, ArrayList<Fox> foxes, ArrayList<Mouse> mice, ArrayList<Twig> twigs, ArrayList<Tree> trees, Tutorial state) {
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
	public String getHighScore() {
		//format: Name: Score
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
		highScore = getHighScore();
		if (harrier.getScore() > Integer.parseInt((highScore.split(":")[1]))) {
			String name = JOptionPane
					.showInputDialog("Congratulations on setting a new HighScore! Please enter your initials.");
			while (name == null || filterInput(name).equals("no")) {
				name = JOptionPane.showInputDialog("Name Invalid. Try Again");
			}
			highScore = name + ":" + harrier.getScore();

			File scoreFile = new File("highscore.dat");
			if (!scoreFile.exists()) {
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
			} finally {
				try {
					if (writer != null) {
						writer.close();
					}
				} catch (Exception e) {
				}
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
		paintIcons(g);
		paintText(g);
		paintSight(g);
		super.paintComponent(g);
		paintBackground(g);
		paintHarrier(g);
		paintTwigs(g);
		paintMice(g);
		paintFoxes(g);
		paintTrees(g);
	}
	
	/*
	 * private method paintIcons.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws tutorial icons on the screen when needed.
	 */
	private void paintIcons(Graphics g) {
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
	}
	
	/*
	 * private method paintText.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws text GUI on the screen.
	 */
	private void paintText(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		g.drawString("Score: " + harrier.getScore(), TitleView.FRAME_WIDTH - 225, 20);
		g.drawString("Press P to pause", TitleView.FRAME_WIDTH - 225, 40);
		g.drawString("Press ESC to return to menu", TitleView.FRAME_WIDTH - 225, 60);
		g.setColor(Color.BLACK);
	}
	
	/*
	 * private method paintSight.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws vision radius on the screen.
	 */
	private void paintSight(Graphics g) {
		Ellipse2D.Double ellipse = new Ellipse2D.Double(TitleView.FRAME_WIDTH / 2 - harrier.getVision(),
				TitleView.FRAME_HEIGHT / 2 - harrier.getVision(), harrier.getVision() * 2, harrier.getVision()*2);
		g.setClip(ellipse);
	}
	
	/*
	 * private method paintBackground.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws background on the screen.
	 */
	private void paintBackground(Graphics g) {
		g.drawImage(images[0], 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT,
				(int)(backgroundScalarX * (harrier.getXPos() - TitleView.FRAME_WIDTH / 2) + 1200),
				(int)(backgroundScalarY * (harrier.getYPos() - TitleView.FRAME_HEIGHT / 2) + 1200),
				(int)(backgroundScalarX * (harrier.getXPos() + TitleView.FRAME_WIDTH / 2) + 1200),
				(int)(backgroundScalarY * (harrier.getYPos() + TitleView.FRAME_HEIGHT / 2) + 1200), this);
		g.setColor(Color.RED);
		g.drawRect(-TitleView.FRAME_WIDTH / 2 - (int)harrier.getXPos(),
				-TitleView.FRAME_HEIGHT / 2 - (int)harrier.getYPos(), 
				2 * TitleView.FRAME_WIDTH, 
				2 * TitleView.FRAME_HEIGHT);
		g.setColor(Color.BLACK);
	}
	
	/*
	 * private method paintHarrier.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the harrier on the screen.
	 */
	private void paintHarrier(Graphics g) {
		int hx = TitleView.FRAME_WIDTH / 2 - (int)(harrier.getXWidth() / 2);
		int hy = TitleView.FRAME_HEIGHT / 2 - (int)(harrier.getYWidth() / 2);
		g.drawImage(animationFrames[harrier.getAnimation() + 4 * directionConverter(harrier.getDirection())], 
					hx - (int)(harrier.getXWidth() / 2), hy - (int)(harrier.getYWidth() / 2), (int)(harrier.getXWidth() * 2), (int)(harrier.getYWidth() * 2), this);
		if(isDebug) { g.drawRect(hx, hy, (int)harrier.getXWidth(), (int)harrier.getYWidth()); }
	}
	
	/*
	 * private method paintTwigs.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the twigs on the screen.
	 */
	private void paintTwigs(Graphics g) {
		for(Twig tw : twigs) {
			int x = (int)(tw.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tw.getXWidth() / 2);
			int y = (int)(tw.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tw.getYWidth() / 2);
			g.drawImage(images[25], x , y, (int)tw.getXWidth(), (int)tw.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)tw.getXWidth(), (int)tw.getYWidth()); }
			if (state == Tutorial.FIVE) {
				double myRadius = tw.calcDist(harrier);
				if (myRadius >= harrier.getVision()) {
					switch(tw.getApproximateDirection(harrier)) {
					case NORTH:
						if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
							g.drawImage(images[36], TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 - (int) harrier.getVision(), 30, 30, this);
						} else {
							g.drawImage(images[36], TitleView.FRAME_WIDTH / 2 + 10, 0, 30, 30, this);
						}
						break;
					case SOUTH:
						if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
							g.drawImage(images[38], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 + (int) harrier.getVision() - 30, 30, 30, this);
						} else {
							g.drawImage(images[38], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT - 30, 30, 30, this);
						}
						break;
					case WEST:
						g.drawImage(images[39], TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / 2 + 10, 30, 30, this);
						break;
					case EAST:
						g.drawImage(images[37], TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 30, TitleView.FRAME_HEIGHT / 2 - 30, 30, 30, this);
						break;
					}
				}
			}
		}
	}
	
	/*
	 * private method paintMice.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the mice on the screen.
	 */
	private void paintMice(Graphics g) {
		for(Mouse m : mice) {
			int x = (int)(m.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(m.getXWidth() / 2);
			int y = (int)(m.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(m.getYWidth() / 2);
			if(m.getSpeedMod() > 4.9) {
				g.drawImage(images[40 + directionConverter(m.getDirection())], x, y, (int)m.getXWidth(), (int)m.getYWidth(), this);
			}
			else {
			g.drawImage(images[17 + directionConverter(m.getDirection())], x, y, (int)m.getXWidth(), (int)m.getYWidth(), this);
			}
			if(isDebug) { g.drawRect(x, y, (int)m.getXWidth(), (int)m.getYWidth()); }
			double myRadius = m.calcDist(harrier);
			if (myRadius + 10 >= harrier.getVision()) {
				switch(m.getApproximateDirection(harrier)) {
				case NORTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
						g.drawImage(images[33], TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 - (int) harrier.getVision(), 30, 30, this);
						if (state == Tutorial.SIX) {
							g.drawImage(images[36], TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 - (int) harrier.getVision() + 40, 30, 30, this);
						}
					} else {
						g.drawImage(images[33], TitleView.FRAME_WIDTH / 2 + 10, 0, 30, 30, this);
					}
					break;
				case SOUTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
						g.drawImage(images[35], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 + (int) harrier.getVision() - 30, 30, 30, this);
						if (state == Tutorial.SIX) {
							g.drawImage(images[38], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 + (int) harrier.getVision() - 30 - 40, 30, 30, this);
						}
					} else {
						g.drawImage(images[35], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT - 30, 30, 30, this);
					}
					break;
				case WEST:
					g.drawImage(images[32], TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / 2 + 10, 30, 30, this);
					if (state == Tutorial.SIX) {
						g.drawImage(images[39], TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision() + 40, TitleView.FRAME_HEIGHT / 2 + 10, 30, 30, this);
					}
					break;
				case EAST:
					g.drawImage(images[34], TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 30, TitleView.FRAME_HEIGHT / 2 - 30, 30, 30, this);
					if (state == Tutorial.SIX) {
						g.drawImage(images[37], TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 30 - 40, TitleView.FRAME_HEIGHT / 2 - 30, 30, 30, this);
					}
					break;
				}
			}
		}
	}
	
	/*
	 * private method paintFoxes.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the foxes on the screen.
	 */
	private void paintFoxes(Graphics g) {
		for(Fox f : foxes) {
			int x = (int)(f.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(f.getXWidth() / 2);
			int y = (int)(f.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(f.getYWidth() / 2);
			g.drawImage(animationFrames[f.getAnimation() + 32 + 4 * directionConverter(f.getDirection())],
						x - (int)(f.getXWidth() / 2), y - (int)(f.getYWidth() / 2), (int)(f.getXWidth() * 2), (int)(f.getYWidth() * 2), this);
			if(isDebug) { g.drawRect(x, y, (int)f.getXWidth(), (int)f.getYWidth()); }
			double myRadius = f.calcDist(harrier);
			if (myRadius + 10 >= harrier.getVision()) {
				switch(f.getApproximateDirection(harrier)) {
				case NORTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
						g.drawImage(images[29], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 - (int) harrier.getVision(), 30, 30, this);
					} else {
						g.drawImage(images[29], TitleView.FRAME_WIDTH / 2 - 30, 0, 30, 30, this);
					}
					break;
				case SOUTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
						g.drawImage(images[31], TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 + (int) harrier.getVision() - 30, 30, 30, this);
					} else {
						g.drawImage(images[31], TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT - 30, 30, 30, this);
					}
					break;
				case WEST:
					g.drawImage(images[28], TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / 2 - 30, 30, 30, this);
					break;
				case EAST:
					g.drawImage(images[30], TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 30, TitleView.FRAME_HEIGHT / 2 + 10, 30, 30, this);
					break;
				}
			}
		}
	}
	
	/*
	 * private method paintTrees.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the trees on the screen.
	 */
	private void paintTrees(Graphics g) {
		for(Tree tr : trees) {
			int x = (int)(tr.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tr.getXWidth() / 2);
			int y = (int)(tr.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tr.getYWidth() / 2);
			g.drawImage(images[26], x - (int)(tr.getXWidth() * .75), y - (int)(tr.getYWidth() / 2), (int)(tr.getXWidth() * 2.5), (int)(tr.getYWidth() * 2), this);
			if(isDebug) { g.drawRect(x, y, (int)tr.getXWidth(), (int)tr.getYWidth()); }
		}
	}

}
