//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class OspreyView extends JPanel{

	final static int X_OFFSET = 100;
	final static int Y_OFFSET = 100;
	private TitleView tv;
	private Osprey osprey;
	private ArrayList<Fish> fish;
	private ArrayList<Seaweed> seaweed;
	Image background, ospreyflying, fishpic, seaweedpic;

	public OspreyView() {

		osprey = new Osprey();
		fish = new ArrayList<>();
		seaweed = new ArrayList<>();
		
		try {
		background = ImageIO.read(new File("src/images/OspreyBackground3.png"));
		ospreyflying = ImageIO.read(new File("src/images/OspreyFlying.png"));
		fishpic = ImageIO.read(new File("src/images/fish.png"));
		seaweedpic = ImageIO.read(new File("src/images/seaweed.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	




	public void setOsprey(Osprey osprey) { this.osprey = osprey; }

	public void setFish(ArrayList<Fish> fish) { this.fish = fish; }

	public void setSeaweed(ArrayList<Seaweed> seaweed) { this.seaweed = seaweed; }

	public Dimension getPreferredSize() { return new Dimension(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT); }

	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawImage(background, 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, null);
		g.drawImage(ospreyflying, 
				X_OFFSET - (int)(osprey.getXWidth() / 2), 
				(int)osprey.getYPos() + Y_OFFSET - (int)(osprey.getYWidth() / 2), 
				(int)osprey.getXWidth(), (int)osprey.getYWidth(), this);
		for(Fish f : fish) {
			int x = (int)(f.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(f.getXWidth() / 2);
			int y = (int)f.getYPos() + Y_OFFSET - (int)(f.getYWidth() / 2);
			g.drawImage(fishpic, x, y, (int)f.getXWidth(), (int)f.getYWidth(), this);
		}
		for(Seaweed s : seaweed) {
			int x = (int)(s.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(s.getXWidth() / 2);
			int y = (int)s.getYPos() + Y_OFFSET - (int)(s.getYWidth() / 2);
			g.drawImage(seaweedpic, x, y, (int)s.getXWidth(), (int)s.getYWidth(), this);
		}
		g.setColor(Color.BLACK);
		g.fillRect(10, 10, 100, 50);
		g.setColor(Color.GREEN);
		g.fillRect(108, 10, 2, 50);
		g.setColor(Color.RED);
		g.fillRect((int) (osprey.getXPos()/500 % 100) + 10, 35, 2, 2);
	}

	/*@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(createBufferedImage("OspreyBackground3.png"), 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, null);
			g.fillRect(X_OFFSET - (int)(osprey.getXWidth() / 2), (int)osprey.getYPos() + Y_OFFSET - (int)(osprey.getYWidth() / 2), (int)osprey.getXWidth(), (int)osprey.getYWidth());
			for(Fish f : fish) {
				int x = (int)(f.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(f.getXWidth() / 2);
				int y = (int)f.getYPos() + Y_OFFSET - (int)(f.getYWidth() / 2);
				g.fillRect(x, y, (int)f.getXWidth(), (int)f.getYWidth());
			}
			for(Seaweed s : seaweed) {
				int x = (int)(s.getXPos() - osprey.getXPos()) + X_OFFSET - (int)(s.getXWidth() / 2);
				int y = (int)s.getYPos() + Y_OFFSET - (int)(s.getYWidth() / 2);
				g.fillRect(x, y, (int)s.getXWidth(), (int)s.getYWidth());
			}
		}*/

}

