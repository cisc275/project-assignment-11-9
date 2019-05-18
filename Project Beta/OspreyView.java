//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;

public class OspreyView extends GameView {

	private Osprey osprey;
	private ArrayList<Fish> fish;
	private ArrayList<Seaweed> seaweed;
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
	public void update(Osprey osprey, ArrayList<Fish> fish, ArrayList<Seaweed> seaweed) {
		this.osprey = osprey;
		this.fish = fish;
		this.seaweed = seaweed;
	}

	/*
	 * private method initializeImages.
	 * Takes no parameter and returns nothing.
	 * Reads the images into the images array.
	 */
	private void initializeImages() {
		images = new BufferedImage[5];
		images[0] = createBufferedImage("Osprey Background.png");
		images[1] = createBufferedImage("Osprey.png");
		images[2] = createBufferedImage("FishEast.png");
		images[3] = createBufferedImage("FishWest.png");
		images[4] = createBufferedImage("Seaweed.png");
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
	 * public method paintComponent.
	 * Takes Graphics as parameter and returns nothing.
	 * Draws the images onto the graphics object for display.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(images[0], 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, this);
		int ox = X_OFFSET - (int)(osprey.getXWidth() / 2);
		int oy = (int)osprey.getYPos() + Y_OFFSET - (int)(osprey.getYWidth() / 2);
		g.drawImage(animationFrames[osprey.getAnimation()], ox, oy, (int)osprey.getXWidth(), (int)osprey.getYWidth(), this);
		if(isDebug) { g.drawRect(ox, oy, (int)osprey.getXWidth(), (int)osprey.getYWidth()); }
		for(Fish f : fish) {
			int x = (int)(f.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(f.getXWidth() / 2);
			int y = (int)f.getYPos() + Y_OFFSET - (int)(f.getYWidth() / 2);
			g.drawImage(animationFrames[f.getAnimation() + 4 + 4* directionConverter(f.getDirection())], x, y, (int)f.getXWidth(), (int)f.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)f.getXWidth(), (int)f.getYWidth()); }
		}
		for(Seaweed s : seaweed) {
			int x = (int)(s.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(s.getXWidth() / 2);
			int y = (int)s.getYPos() + Y_OFFSET - (int)(s.getYWidth() / 2);
			g.drawImage(images[4], x, y, (int)s.getXWidth(), (int)s.getYWidth(), this);
			if(isDebug) { g.drawRect(x, y, (int)s.getXWidth(), (int)s.getYWidth()); }
		}
	}

}
