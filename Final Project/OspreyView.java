//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class OspreyView extends GameView{
	
	private OspreyHelper helper;
	final static int X_OFFSET = 100;
	final static int Y_OFFSET = 100;

	public OspreyView() {
		super();
		helper = new OspreyHelper();
		frame.getContentPane().add(helper);
		frame.repaint();
	}
	
	public void update(Osprey osprey, ArrayList<Fish> fish, ArrayList<Seaweed> seaweed) {
		helper.setOsprey(osprey);
		helper.setFish(fish);
		helper.setSeaweed(seaweed);
		frame.repaint();
	}
	
	private class OspreyHelper extends JPanel {
		
		private Osprey osprey;
		private ArrayList<Fish> fish;
		private ArrayList<Seaweed> seaweed;
		
		private OspreyHelper() {
			setOpaque(true);
			setBackground(Color.blue);
			osprey = new Osprey();
			fish = new ArrayList<>();
			seaweed = new ArrayList<>();
		}
		
	
		public void setOsprey(Osprey osprey) { this.osprey = osprey; }
		
		public void setFish(ArrayList<Fish> fish) { this.fish = fish; }
		
		public void setSeaweed(ArrayList<Seaweed> seaweed) { this.seaweed = seaweed; }
		
		public Dimension getPreferredSize() { return new Dimension(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT); }
		
		public Image ImagePanel(String file) {
	        try {   
	        	String path = "src/images/";
	    		path += file;
	           Image image = ImageIO.read(new File(path));
	           return image;
	        } catch (IOException ex) {
	             // handle exception...
	        }
			return null;
	     }
		
		
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(ImagePanel("OspreyBackground3.png"), 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, null);
			g.drawImage(ImagePanel("OspreyFlying.png"), X_OFFSET, (int)osprey.getYPos() + Y_OFFSET, (int)osprey.getXWidth(), (int)osprey.getYWidth(), this);
			//g.fillRect(X_OFFSET, (int)osprey.getYPos() + Y_OFFSET, (int)osprey.getXWidth(), (int)osprey.getYWidth());
			for(Fish f : fish) {
				int x = (int)(f.getXPos() - osprey.getXPos()) + X_OFFSET;
				int y = (int)f.getYPos() + Y_OFFSET;
				g.drawImage(ImagePanel("fish.png"), x, y, (int)f.getXWidth(), (int)f.getYWidth(), this);
			}
			for(Seaweed s : seaweed) {
				int x = (int)(s.getXPos() - osprey.getXPos()) + X_OFFSET;
				int y = (int)s.getYPos() + Y_OFFSET;
				g.drawImage(ImagePanel("seaweed.png"), x, y, (int)s.getXWidth(), (int)s.getYWidth(), this);
			}
		}

	}
	
}
