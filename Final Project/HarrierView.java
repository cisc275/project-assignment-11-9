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

		public HarrierHelper() {
			setOpaque(true);
			setBackground(Color.green);
			harrier = new Harrier();
			foxes = new ArrayList<>();
			mice = new ArrayList<>();
			twigs = new ArrayList<>();
			trees = new ArrayList<>();
		}
		
		public void setHarrier(Harrier harrier) { this.harrier = harrier; }
		
		public void setFoxes(ArrayList<Fox> foxes) { this.foxes = foxes; }
		
		public void setMice(ArrayList<Mouse> mice) { this.mice = mice; }
		
		public void setTwigs(ArrayList<Twig> twigs) {this.twigs = twigs; }
		
		public void setTrees(ArrayList<Tree> trees) { this.trees = trees; }

		public Dimension getPreferredSize() { return new Dimension(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT); }
		
		
		
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
			g.drawImage(ImagePanel("HarrierBackground.png"), 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, null);
			g.drawImage(ImagePanel("Harrier Overtop.png"), TitleView.FRAME_WIDTH / 2, TitleView.FRAME_HEIGHT / 2, (int)harrier.getXWidth(), (int)harrier.getYWidth(), this);
			for(Fox f : foxes) {
				int x = (int)(f.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2;
				int y = (int)(f.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2;
				g.drawImage(ImagePanel("fox.png"), x, y, (int)f.getXWidth(), (int)f.getYWidth(), this);
				
				
				
			}
			for(Mouse m : mice) {
				int x = (int)(m.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2;
				int y = (int)(m.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2;
				g.drawImage(ImagePanel("mouse.png"), x, y, (int)m.getXWidth(), (int)m.getYWidth(), this);
			}
			for(Twig tw : twigs) {
				int x = (int)(tw.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2;
				int y = (int)(tw.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2;
				g.drawImage(ImagePanel("twig.png"), x, y, (int)tw.getXWidth(), (int)tw.getYWidth(), this);
			}
			for(Tree tr : trees) {
				int x = (int)(tr.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - 30;
				int y = (int)(tr.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - 30;
				g.drawImage(ImagePanel("Tree.png"), x, y, (int)tr.getXWidth(), (int)tr.getYWidth(), this);
			}
		}
		
	}

}
