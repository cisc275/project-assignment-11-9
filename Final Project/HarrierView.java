//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HarrierView extends GameView {
	
	private HarrierHelper helper;

	public HarrierView() {
		super();
		helper = new HarrierHelper();
		frame.getContentPane().add(helper);
		frame.repaint();
	}

	public void update(Harrier harrier, ArrayList<Fox> foxes, ArrayList<Mouse> mice, ArrayList<Twig> twigs, ArrayList<Tree> trees) {
		helper.setHarrier(harrier);
		helper.setFoxes(foxes);
		helper.setMice(mice);
		helper.setTwigs(twigs);
		helper.setTrees(trees);
		frame.repaint();
	}

	private class HarrierHelper extends JPanel {

		private Harrier harrier;
		private ArrayList<Fox> foxes;
		private ArrayList<Mouse> mice;
		private ArrayList<Twig> twigs;
		private ArrayList<Tree> trees;
		private Image[] images;

		public HarrierHelper() {
			setOpaque(true);
			setBackground(Color.green);
			harrier = new Harrier();
			foxes = new ArrayList<>();
			mice = new ArrayList<>();
			twigs = new ArrayList<>();
			trees = new ArrayList<>();
			initializeImages();
		}
		
		public void setHarrier(Harrier harrier) { this.harrier = harrier; }
		
		public void setFoxes(ArrayList<Fox> foxes) { this.foxes = foxes; }
		
		public void setMice(ArrayList<Mouse> mice) { this.mice = mice; }
		
		public void setTwigs(ArrayList<Twig> twigs) {this.twigs = twigs; }
		
		public void setTrees(ArrayList<Tree> trees) { this.trees = trees; }

		public Dimension getPreferredSize() { return new Dimension(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT); }
		
		
		private void initializeImages() {
			images = new Image[7];
			images[0] = ImagePanel("HarrierBackground.png");
			images[1] = ImagePanel("Harrier Overtop.png");
			images[2] = ImagePanel("fox.png");
			images[3] = ImagePanel("mouse.png");
			images[4] = ImagePanel("twig.png");
			images[5] = ImagePanel("Tree.png");
			images[6] = ImagePanel("vision.png");
		}
		
		//Read image from file and return
	    private BufferedImage createImage(String file){
	    	BufferedImage bufferedImage;
	    	try {
	    		String path = "src/images/";
	    		path += file;
	    		bufferedImage = ImageIO.read(new File(path));
	    		return bufferedImage;
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	return null;
	    }
	
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
			g.drawImage(images[0], 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, null);
			g.drawImage(images[1], TitleView.FRAME_WIDTH / 2, TitleView.FRAME_HEIGHT / 2, (int)harrier.getXWidth(), (int)harrier.getYWidth(), this);
			for(Fox f : foxes) {
				int x = (int)(f.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2;
				int y = (int)(f.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2;
				g.drawImage(images[2], x, y, (int)f.getXWidth(), (int)f.getYWidth(), this);
			}
			for(Mouse m : mice) {
				int x = (int)(m.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2;
				int y = (int)(m.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2;
				g.drawImage(images[3], x, y, (int)m.getXWidth(), (int)m.getYWidth(), this);
			}
			for(Twig tw : twigs) {
				int x = (int)(tw.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2;
				int y = (int)(tw.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2;
				g.drawImage(images[4], x, y, (int)tw.getXWidth(), (int)tw.getYWidth(), this);
			}
			for(Tree tr : trees) {
				int x = (int)(tr.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - 30;
				int y = (int)(tr.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - 30;
				g.drawImage(images[5], x, y, (int)tr.getXWidth(), (int)tr.getYWidth(), this);
			}
			g.drawImage(images[6], (int) (0 - harrier.getVision()*10), (int) (0 - harrier.getVision()*10), (int) (900 + harrier.getVision()*10), (int) (900 + harrier.getVision()*10), 0, 0, images[6].getWidth(this), images[6].getHeight(this), this);
			
		}
		
	}

}