<<<<<<< HEAD
//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.util.*;
import java.awt.image.*;
import javax.swing.*;

public class HarrierView extends GameView {

	private Harrier harrier;
	private ArrayList<Fox> foxes;
	private ArrayList<Mouse> mice;
	private ArrayList<Twig> twigs;
	private ArrayList<Tree> trees;
	private String[] imageEndings = {"East", "North", "Northeast", "Northwest", "South", "Southeast", "Southwest", "West"};
	private final static double backgroundScalarX = 1600 / (TitleView.FRAME_WIDTH * 3.0);
	private final static double backgroundScalarY = 1600 / (TitleView.FRAME_HEIGHT * 3.0);

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
		super.paintComponent(g);
		g.drawImage(images[0], 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT,
					(int)(backgroundScalarX * (harrier.getXPos() - TitleView.FRAME_WIDTH / 2) + 800),
					(int)(backgroundScalarY * (harrier.getYPos() - TitleView.FRAME_HEIGHT / 2) + 800),
					(int)(backgroundScalarX * (harrier.getXPos() + TitleView.FRAME_WIDTH / 2) + 800),
					(int)(backgroundScalarY * (harrier.getYPos() + TitleView.FRAME_HEIGHT / 2) + 800), this);
		/*g.setColor(Color.RED);
		g.drawRect(-TitleView.FRAME_WIDTH / 2 - (int)harrier.getXPos(),
				   -TitleView.FRAME_HEIGHT / 2 - (int)harrier.getYPos(), 
				   2 * TitleView.FRAME_WIDTH, 
				   2 * TitleView.FRAME_HEIGHT);
		g.setColor(Color.BLACK);*/
		int hx = TitleView.FRAME_WIDTH / 2 - (int)(harrier.getXWidth() / 2);
		int hy = TitleView.FRAME_HEIGHT / 2 - (int)(harrier.getYWidth() / 2);
		g.drawImage(animationFrames[harrier.getAnimation() + 4 * directionConverter(harrier.getDirection())], 
					hx - (int)(harrier.getXWidth() / 2), hy - (int)(harrier.getYWidth() / 2), (int)(harrier.getXWidth() * 2), (int)(harrier.getYWidth() * 2), this);
		if(isDebug) { g.drawRect(hx, hy, (int)harrier.getXWidth(), (int)harrier.getYWidth()); }
		for(Twig tw : twigs) {
			int x = (int)(tw.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tw.getXWidth() / 2);
			int y = (int)(tw.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tw.getYWidth() / 2);
			g.drawImage(images[25], x , y, (int)tw.getXWidth(), (int)tw.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)tw.getXWidth(), (int)tw.getYWidth()); }
		}
		for(Mouse m : mice) {
			int x = (int)(m.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(m.getXWidth() / 2);
			int y = (int)(m.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(m.getYWidth() / 2);
			g.drawImage(images[17 + directionConverter(m.getDirection())], x, y, (int)m.getXWidth(), (int)m.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)m.getXWidth(), (int)m.getYWidth()); }
		}
		for(Fox f : foxes) {
			int x = (int)(f.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(f.getXWidth() / 2);
			int y = (int)(f.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(f.getYWidth() / 2);
			g.drawImage(animationFrames[f.getAnimation() + 32 + 4 * directionConverter(f.getDirection())],
						x - (int)(f.getXWidth() / 2), y - (int)(f.getYWidth() / 2), (int)(f.getXWidth() * 2), (int)(f.getYWidth() * 2), this);
			if(isDebug) { g.drawRect(x, y, (int)f.getXWidth(), (int)f.getYWidth()); }
		}
		for(Tree tr : trees) {
			int x = (int)(tr.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tr.getXWidth() / 2);
			int y = (int)(tr.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tr.getYWidth() / 2);
			g.drawImage(images[26], x - (int)(tr.getXWidth() * .75), y - (int)(tr.getYWidth() / 2), (int)(tr.getXWidth() * 2.5), (int)(tr.getYWidth() * 2), this);
			if(isDebug) { g.drawRect(x, y, (int)tr.getXWidth(), (int)tr.getYWidth()); }
		}
		/*g.drawImage(images[27], 
						(int) (0 - harrier.getVision()*20), (int) (0 - harrier.getVision()*20),
						(int) (TitleView.FRAME_WIDTH + harrier.getVision()*20), (int) (TitleView.FRAME_HEIGHT + harrier.getVision()*20),
						0, 0, images[6].getWidth(this), images[6].getHeight(this), this);*/
	}

}

=======
//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.awt.image.*;
import javax.swing.*;

public class HarrierView extends GameView {

	private Harrier harrier;
	private ArrayList<Fox> foxes;
	private ArrayList<Mouse> mice;
	private ArrayList<Twig> twigs;
	private ArrayList<Tree> trees;
	private String[] imageEndings = {"East", "North", "Northeast", "Northwest", "South", "Southeast", "Southwest", "West"};

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
	 * private method initializeImages.
	 * Takes no parameter and returns nothing.
	 * Reads the images into the images array.
	 */
	private void initializeImages() {
		images = new BufferedImage[36];
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
						//g.fillOval(TitleView.FRAME_WIDTH / 2 + 10, TitleView.FRAME_HEIGHT / 2 - (int)harrier.getVision(), 20, 20);
					} else {
						g.drawImage(images[33], TitleView.FRAME_WIDTH / 2 + 10, 0, 30, 30, this);
						//g.fillOval(TitleView.FRAME_WIDTH / 2 + 10, 0, 20, 20);
					}
					break;
				case SOUTH:
					if (harrier.getVision() < TitleView.FRAME_HEIGHT / 2) {
						g.drawImage(images[35], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 + (int) harrier.getVision() - 30, 30, 30, this);
						//g.fillOval(TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT / 2 + (int)harrier.getVision() - 20, 20, 20);
					} else {
						g.drawImage(images[35], TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT - 30, 30, 30, this);
						//g.fillOval(TitleView.FRAME_WIDTH / 2 - 30, TitleView.FRAME_HEIGHT - 20, 20, 20);
					}
					break;
				case WEST:
					g.drawImage(images[32], TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / 2 + 10, 30, 30, this);
					//g.fillOval(TitleView.FRAME_WIDTH / 2 - (int)harrier.getVision(), TitleView.FRAME_HEIGHT / 2 + 10, 20, 20);
					break;
				case EAST:
					g.drawImage(images[34], TitleView.FRAME_WIDTH / 2 + (int)harrier.getVision() - 30, TitleView.FRAME_HEIGHT / 2 - 30, 30, 30, this);
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
		}
		g.setColor(Color.RED);
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
>>>>>>> a50fe777534a312aa19768ecfe732028af715260
