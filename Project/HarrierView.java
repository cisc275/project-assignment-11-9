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
	private Tutorial state;
	private String highScore;
	private FileReader readFile;
	private BufferedReader reader;
	private String[] imageEndings = {"East", "North", "Northeast", "Northwest", "South", "Southeast", "Southwest", "West"};
	private final static double backgroundScalarX = 2400 / (TitleView.FRAME_WIDTH * 3.0);
	private final static double backgroundScalarY = 2400 / (TitleView.FRAME_HEIGHT * 3.0);
	private final static int NUM_IMAGES = 47;
	private final static int NUM_ANIMATIONS = 16;
	private final static int NUM_FRAMES = 4;
	private final static int IMAGE_WIDTH = 165;
	private final static int IMAGE_HEIGHT = 140;
	private final static int BACKGROUND_INDEX = 0;
	private final static int HARRIER_INDEX = 1;
	private final static int FOX_INDEX = 9;
	private final static int MOUSE_INDEX = 17;
	private final static int GOLDEN_INDEX = 25;
	private final static int TWIG_INDEX = 33;
	private final static int TREE_INDEX = 34;
	private final static int RED_WEST = 35;
	private final static int RED_NORTH = 36;
	private final static int RED_EAST = 37;
	private final static int RED_SOUTH = 38;
	private final static int BLUE_WEST = 39;
	private final static int BLUE_NORTH = 40;
	private final static int BLUE_EAST = 41;
	private final static int BLUE_SOUTH = 42;
	private final static int ARROW_UP = 43;
	private final static int ARROW_RIGHT = 44;
	private final static int ARROW_DOWN = 45;
	private final static int ARROW_LEFT = 46;
	private final static int FOX_OFFSET = 32;
	private final static int ICON_SIZE = 80;
	private final static int ICON_W_DIVISOR = 6;
	private final static int ICON_H_DIVISOR = 2;
	private final static int FONT_SIZE = 16;
	private final static int TEXT_W_OFFSET = 225;
	private final static int TEXT_H_OFFSET = 20;
	private final static int RADIUS_DIAM_RATIO = 2;
	private final static int BACK_CENTER = 1200;
	private final static double HARRIER_SCALAR = 2;
	private final static double FOX_SCALAR = 2;
	private final static double TREE_W_SCALAR = 2.5;
	private final static double TREE_H_SCALAR = 2;
	private final static int SIGHT_ICON_SIZE = 30;
	private final static int SIGHT_ICON_OFFSET_1 = 10;
	private final static int SIGHT_ICON_OFFSET_2 = 40;

	public HarrierView() {
		setOpaque(true);
		setBackground(Color.green);
		harrier = new Harrier();
		foxes = new ArrayList<>();
		mice = new ArrayList<>();
		twigs = new ArrayList<>();
		trees = new ArrayList<>();
		state = Tutorial.NONE;
		initializeImages();
	}

	/*
	 * private method initializeImages.
	 * Takes no parameter and returns nothing.
	 * Reads the images into the images array.
	 */
	private void initializeImages() {
		images = new BufferedImage[NUM_IMAGES];
		images[BACKGROUND_INDEX] = createBufferedImage("HarrierBackground.png");
		initializeImageSet("Harrier", ".png", HARRIER_INDEX);
		initializeImageSet("Fox", ".png", FOX_INDEX);
		initializeImageSet("Mouse", ".png", MOUSE_INDEX);
		initializeImageSet("GoldenMouse", ".png", GOLDEN_INDEX);
		images[TWIG_INDEX] = createBufferedImage("Twig.png");
		images[TREE_INDEX] = createBufferedImage("Tree.png");
		images[RED_WEST] = createBufferedImage("SoundRed.png");
		images[RED_NORTH] = createBufferedImage("SoundRed.png");
		images[RED_EAST] = createBufferedImage("SoundRed.png");
		images[RED_SOUTH] = createBufferedImage("SoundRed.png");
		images[BLUE_WEST] = createBufferedImage("SoundBlue.png");
		images[BLUE_NORTH] = createBufferedImage("SoundBlue.png");
		images[BLUE_EAST] = createBufferedImage("SoundBlue.png");
		images[BLUE_SOUTH] = createBufferedImage("SoundBlue.png");
		images[ARROW_UP] = createBufferedImage("ArrowUp.png");
		images[ARROW_RIGHT] = createBufferedImage("ArrowRight.png");
		images[ARROW_DOWN] = createBufferedImage("ArrowDown.png");
		images[ARROW_LEFT] = createBufferedImage("ArrowLeft.png");
		makeFrames();
		rotateImages();
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
				animationFrames[j + NUM_FRAMES*i] = images[HARRIER_INDEX + i].getSubimage(IMAGE_WIDTH*j, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
			}
		}
	}
	
	/*
	 * private method rotateImages.
	 * Takes no parameter and returns nothing.
	 * Rotates necessary images.
	 */
	private void rotateImages() {
		AffineTransform tx1 = new AffineTransform();
		tx1.rotate(Math.PI/2, images[RED_WEST].getWidth()/2, images[RED_WEST].getHeight()/2);
		AffineTransform tx2 = new AffineTransform();
		tx2.rotate(Math.PI, images[RED_WEST].getWidth()/2, images[RED_WEST].getHeight()/2);
		AffineTransform tx3 = new AffineTransform();
		tx3.rotate(Math.PI*3/2, images[RED_WEST].getWidth()/2, images[RED_WEST].getHeight()/2);
		AffineTransformOp op1 = new AffineTransformOp(tx1, AffineTransformOp.TYPE_BILINEAR);
		AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
		AffineTransformOp op3 = new AffineTransformOp(tx3, AffineTransformOp.TYPE_BILINEAR);
		images[RED_NORTH] = op1.filter(images[RED_NORTH], null);
		images[RED_EAST] = op2.filter(images[RED_EAST], null);
		images[RED_SOUTH] = op3.filter(images[RED_SOUTH], null);
		images[BLUE_NORTH] = op1.filter(images[BLUE_NORTH], null);
		images[BLUE_EAST] = op2.filter(images[BLUE_EAST], null);
		images[BLUE_SOUTH] = op3.filter(images[BLUE_SOUTH], null);
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
			g.drawImage(images[ARROW_UP], TitleView.FRAME_WIDTH / ICON_W_DIVISOR, (TitleView.FRAME_HEIGHT - ICON_SIZE) / ICON_H_DIVISOR, ICON_SIZE, ICON_SIZE, this);
			break;
		case TWO:
			g.drawImage(images[ARROW_RIGHT], TitleView.FRAME_WIDTH / ICON_W_DIVISOR, (TitleView.FRAME_HEIGHT - ICON_SIZE) / ICON_H_DIVISOR, ICON_SIZE, ICON_SIZE, this);
			break;
		case THREE:
			g.drawImage(images[ARROW_DOWN], TitleView.FRAME_WIDTH / ICON_W_DIVISOR, (TitleView.FRAME_HEIGHT - ICON_SIZE) / ICON_H_DIVISOR, ICON_SIZE, ICON_SIZE, this);
			break;
		case FOUR:
			g.drawImage(images[ARROW_LEFT], TitleView.FRAME_WIDTH / ICON_W_DIVISOR, (TitleView.FRAME_HEIGHT - ICON_SIZE) / ICON_H_DIVISOR, ICON_SIZE, ICON_SIZE, this);
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
		g.setFont(new Font(Font.SERIF, Font.BOLD, FONT_SIZE));
		g.drawString("Score: " + harrier.getScore(), TitleView.FRAME_WIDTH - TEXT_W_OFFSET, TEXT_H_OFFSET);
		g.drawString("Press P to pause", TitleView.FRAME_WIDTH - TEXT_W_OFFSET, TEXT_H_OFFSET * 2);
		g.drawString("Press ESC to return to menu", TitleView.FRAME_WIDTH - TEXT_W_OFFSET, TEXT_H_OFFSET * 3);
		g.setColor(Color.BLACK);
	}
	
	/*
	 * private method paintSight.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws vision radius on the screen.
	 */
	private void paintSight(Graphics g) {
		Ellipse2D.Double ellipse = new Ellipse2D.Double(TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - harrier.getVision(),
				TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - harrier.getVision(), harrier.getVision() * RADIUS_DIAM_RATIO, harrier.getVision() * RADIUS_DIAM_RATIO);
		g.setClip(ellipse);
	}
	
	/*
	 * private method paintBackground.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws background on the screen.
	 */
	private void paintBackground(Graphics g) {
		g.drawImage(images[BACKGROUND_INDEX], 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT,
				(int)(backgroundScalarX * (harrier.getXPos() - TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO) + BACK_CENTER),
				(int)(backgroundScalarY * (harrier.getYPos() - TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO) + BACK_CENTER),
				(int)(backgroundScalarX * (harrier.getXPos() + TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO) + BACK_CENTER),
				(int)(backgroundScalarY * (harrier.getYPos() + TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO) + BACK_CENTER), this);
		g.setColor(Color.RED);
		g.drawRect(-TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - (int)harrier.getXPos(),
				-TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - (int)harrier.getYPos(), 
				RADIUS_DIAM_RATIO * TitleView.FRAME_WIDTH, 
				RADIUS_DIAM_RATIO * TitleView.FRAME_HEIGHT);
		g.setColor(Color.BLACK);
	}
	
	/*
	 * private method paintHarrier.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the harrier on the screen.
	 */
	private void paintHarrier(Graphics g) {
		int hx = TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - (int)(harrier.getXWidth() / RADIUS_DIAM_RATIO);
		int hy = TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - (int)(harrier.getYWidth() / RADIUS_DIAM_RATIO);
		g.drawImage(animationFrames[harrier.getAnimation() + NUM_FRAMES * directionConverter(harrier.getDirection())], 
					hx - (int)(harrier.getXWidth() * (HARRIER_SCALAR - 1) / RADIUS_DIAM_RATIO),
					hy - (int)(harrier.getYWidth() * (HARRIER_SCALAR - 1) / RADIUS_DIAM_RATIO),
					(int)(harrier.getXWidth() * HARRIER_SCALAR), (int)(harrier.getYWidth() * HARRIER_SCALAR), this);
		if(isDebug) { g.drawRect(hx, hy, (int)harrier.getXWidth(), (int)harrier.getYWidth()); }
	}
	
	/*
	 * private method paintTwigs.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the twigs on the screen.
	 */
	private void paintTwigs(Graphics g) {
		for(Twig tw : twigs) {
			int x = (int)(tw.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - (int)(tw.getXWidth() / RADIUS_DIAM_RATIO);
			int y = (int)(tw.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - (int)(tw.getYWidth() / RADIUS_DIAM_RATIO);
			g.drawImage(images[TWIG_INDEX], x , y, (int)tw.getXWidth(), (int)tw.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)tw.getXWidth(), (int)tw.getYWidth()); }
			if (state == Tutorial.FIVE) {
				double myRadius = tw.calcDist(harrier);
				if (myRadius >= harrier.getVision()) {
					switch(tw.getApproximateDirection(harrier)) {
					case NORTH:
						if (harrier.getVision() < TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO) {
							g.drawImage(images[ARROW_UP], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO + SIGHT_ICON_OFFSET_1, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - (int) harrier.getVision(), SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
						} else {
							g.drawImage(images[ARROW_UP], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO + SIGHT_ICON_OFFSET_1, 0, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
						}
						break;
					case SOUTH:
						if (harrier.getVision() < TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO) {
							g.drawImage(images[ARROW_DOWN], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - SIGHT_ICON_SIZE, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO + (int) harrier.getVision() - SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
						} else {
							g.drawImage(images[ARROW_DOWN], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - SIGHT_ICON_SIZE, TitleView.FRAME_HEIGHT - SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
						}
						break;
					case WEST:
						g.drawImage(images[ARROW_LEFT], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO + SIGHT_ICON_OFFSET_1, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
						break;
					case EAST:
						g.drawImage(images[ARROW_RIGHT], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO + (int)harrier.getVision() - SIGHT_ICON_SIZE, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
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
			int x = (int)(m.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - (int)(m.getXWidth() / RADIUS_DIAM_RATIO);
			int y = (int)(m.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - (int)(m.getYWidth() / RADIUS_DIAM_RATIO);
			if(m.getSpeedMod() >= GoldenMouse.GOLD_SPEED) {
				g.drawImage(images[GOLDEN_INDEX + directionConverter(m.getDirection())], x, y, (int)m.getXWidth(), (int)m.getYWidth(), this);
			}
			else {
			g.drawImage(images[MOUSE_INDEX + directionConverter(m.getDirection())], x, y, (int)m.getXWidth(), (int)m.getYWidth(), this);
			}
			if(isDebug) { g.drawRect(x, y, (int)m.getXWidth(), (int)m.getYWidth()); }
			double myRadius = m.calcDist(harrier);
			if (myRadius + 10 >= harrier.getVision()) {
				switch(m.getApproximateDirection(harrier)) {
				case NORTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO) {
						g.drawImage(images[BLUE_NORTH], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO + SIGHT_ICON_OFFSET_1, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - (int) harrier.getVision(), SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
						if (state == Tutorial.SIX) {
							g.drawImage(images[ARROW_UP], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO + SIGHT_ICON_OFFSET_1, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - (int) harrier.getVision() + SIGHT_ICON_OFFSET_2, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
						}
					} else {
						g.drawImage(images[BLUE_NORTH], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO + SIGHT_ICON_OFFSET_1, 0, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
					}
					break;
				case SOUTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO) {
						g.drawImage(images[BLUE_SOUTH], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - SIGHT_ICON_SIZE, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO + (int) harrier.getVision() - SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
						if (state == Tutorial.SIX) {
							g.drawImage(images[ARROW_DOWN], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - SIGHT_ICON_SIZE, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO + (int) harrier.getVision() - SIGHT_ICON_SIZE - SIGHT_ICON_OFFSET_2, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
						}
					} else {
						g.drawImage(images[BLUE_SOUTH], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - SIGHT_ICON_SIZE, TitleView.FRAME_HEIGHT - SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
					}
					break;
				case WEST:
					g.drawImage(images[BLUE_WEST], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO + SIGHT_ICON_OFFSET_1, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
					if (state == Tutorial.SIX) {
						g.drawImage(images[ARROW_LEFT], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - (int)harrier.getVision() + SIGHT_ICON_OFFSET_2, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO + SIGHT_ICON_OFFSET_1, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
					}
					break;
				case EAST:
					g.drawImage(images[BLUE_EAST], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO + (int)harrier.getVision() - SIGHT_ICON_SIZE, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
					if (state == Tutorial.SIX) {
						g.drawImage(images[ARROW_RIGHT], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO + (int)harrier.getVision() - SIGHT_ICON_SIZE - SIGHT_ICON_OFFSET_2, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
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
			int x = (int)(f.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - (int)(f.getXWidth() / RADIUS_DIAM_RATIO);
			int y = (int)(f.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - (int)(f.getYWidth() / RADIUS_DIAM_RATIO);
			g.drawImage(animationFrames[f.getAnimation() + FOX_OFFSET + NUM_FRAMES * directionConverter(f.getDirection())],
						x - (int)(f.getXWidth() * (FOX_SCALAR - 1) / RADIUS_DIAM_RATIO),
						y - (int)(f.getYWidth() * (FOX_SCALAR - 1) / RADIUS_DIAM_RATIO),
						(int)(f.getXWidth() * FOX_SCALAR), (int)(f.getYWidth() * FOX_SCALAR), this);
			if(isDebug) { g.drawRect(x, y, (int)f.getXWidth(), (int)f.getYWidth()); }
			double myRadius = f.calcDist(harrier);
			if (myRadius + 10 >= harrier.getVision()) {
				switch(f.getApproximateDirection(harrier)) {
				case NORTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO) {
						g.drawImage(images[RED_NORTH], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - SIGHT_ICON_SIZE, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - (int) harrier.getVision(), SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
					} else {
						g.drawImage(images[RED_NORTH], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - SIGHT_ICON_SIZE, 0, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
					}
					break;
				case SOUTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO) {
						g.drawImage(images[RED_SOUTH], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO + SIGHT_ICON_OFFSET_1, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO + (int) harrier.getVision() - SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
					} else {
						g.drawImage(images[RED_SOUTH], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO + SIGHT_ICON_OFFSET_1, TitleView.FRAME_HEIGHT - SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
					}
					break;
				case WEST:
					g.drawImage(images[RED_WEST], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
					break;
				case EAST:
					g.drawImage(images[RED_EAST], TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO + (int)harrier.getVision() - SIGHT_ICON_SIZE, TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO + SIGHT_ICON_OFFSET_1, SIGHT_ICON_SIZE, SIGHT_ICON_SIZE, this);
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
			int x = (int)(tr.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / RADIUS_DIAM_RATIO - (int)(tr.getXWidth() / RADIUS_DIAM_RATIO);
			int y = (int)(tr.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / RADIUS_DIAM_RATIO - (int)(tr.getYWidth() / RADIUS_DIAM_RATIO);
			g.drawImage(images[TREE_INDEX],
						x - (int)(tr.getXWidth() * (TREE_W_SCALAR - 1) / RADIUS_DIAM_RATIO),
						y - (int)(tr.getYWidth() * (TREE_H_SCALAR - 1) / RADIUS_DIAM_RATIO),
						(int)(tr.getXWidth() * TREE_W_SCALAR), (int)(tr.getYWidth() * TREE_H_SCALAR), this);
			if(isDebug) { g.drawRect(x, y, (int)tr.getXWidth(), (int)tr.getYWidth()); }
		}
	}

}
