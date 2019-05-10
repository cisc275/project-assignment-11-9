//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.util.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HarrierView extends JPanel {

	private TitleView tv;
	private BufferedImage[] images;
	private Harrier harrier;
	private ArrayList<Fox> foxes;
	private ArrayList<Mouse> mice;
	private ArrayList<Twig> twigs;
	private ArrayList<Tree> trees;
	private Image background, harrierfly, fox, mouse, twig, tree, vision;

	public HarrierView() {
		harrier = new Harrier();
		foxes = new ArrayList<>();
		mice = new ArrayList<>();
		twigs = new ArrayList<>();
		trees = new ArrayList<>();
		
		try {
			background = ImageIO.read(new File("src/images/HarrierBackground.png"));
			harrierfly = ImageIO.read(new File("src/images/Harrier Overtop.png"));
			fox = ImageIO.read(new File("src/images/fox.png"));
			mouse = ImageIO.read(new File("src/images/mouse.png"));
			twig = ImageIO.read(new File("src/images/twig.png"));
			tree = ImageIO.read(new File("src/images/tree.png"));
			twig = ImageIO.read(new File("src/images/twig.png"));
			vision = ImageIO.read(new File("src/images/vision.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
	}

	
	

	
		
		public void setHarrier(Harrier harrier) { this.harrier = harrier; }
		
		public void setFoxes(ArrayList<Fox> foxes) { this.foxes = foxes; }
		
		public void setMice(ArrayList<Mouse> mice) { this.mice = mice; }
		
		public void setTwigs(ArrayList<Twig> twigs) {this.twigs = twigs; }
		
		public void setTrees(ArrayList<Tree> trees) { this.trees = trees; }

		public Dimension getPreferredSize() { return new Dimension(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT); }
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background, 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, null);
			g.drawImage(harrierfly,
						TitleView.FRAME_WIDTH / 2 - (int)(harrier.getXWidth() / 2),
						TitleView.FRAME_HEIGHT / 2 - (int)(harrier.getYWidth() / 2),
						(int)harrier.getXWidth(), (int)harrier.getYWidth(), this);
			for(Fox f : foxes) {
				int x = (int)(f.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(f.getXWidth() / 2);
				int y = (int)(f.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(f.getYWidth() / 2);
				g.drawImage(fox, x, y, (int)f.getXWidth(), (int)f.getYWidth(), this);
			}
			for(Mouse m : mice) {
				int x = (int)(m.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(m.getXWidth() / 2);
				int y = (int)(m.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(m.getYWidth() / 2);
				g.drawImage(mouse, x, y, (int)m.getXWidth(), (int)m.getYWidth(), this);
			}
			for(Twig tw : twigs) {
				int x = (int)(tw.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tw.getXWidth() / 2);
				int y = (int)(tw.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tw.getYWidth() / 2);
				g.drawImage(twig, x, y, (int)tw.getXWidth(), (int)tw.getYWidth(), this);
			}
			for(Tree tr : trees) {
				int x = (int)(tr.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tr.getXWidth() / 2);
				int y = (int)(tr.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tr.getYWidth() / 2);
				g.drawImage(tree, x, y, (int)tr.getXWidth(), (int)tr.getYWidth(), this);
			}
			g.drawImage(vision, 
						(int) (0 - harrier.getVision()*20), (int) (0 - harrier.getVision()*20),
						(int) (TitleView.FRAME_WIDTH + harrier.getVision()*20), (int) (TitleView.FRAME_HEIGHT + harrier.getVision()*20),
						0, 0, vision.getWidth(this), vision.getHeight(this), this);
		}
		
		/*@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.fillRect(TitleView.FRAME_WIDTH / 2 - (int)(harrier.getXWidth() / 2),
					   TitleView.FRAME_HEIGHT / 2 - (int)(harrier.getYWidth() / 2),
					   (int)harrier.getXWidth(), (int)harrier.getYWidth());
			for(Fox f : foxes) {
				int x = (int)(f.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(f.getXWidth() / 2);
				int y = (int)(f.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(f.getYWidth() / 2);
				g.fillRect(x, y, (int)f.getXWidth(), (int)f.getYWidth());
			}
			for(Mouse m : mice) {
				int x = (int)(m.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(m.getXWidth() / 2);
				int y = (int)(m.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(m.getYWidth() / 2);
				g.fillRect(x, y, (int)m.getXWidth(), (int)m.getYWidth());
			}
			for(Twig tw : twigs) {
				int x = (int)(tw.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tw.getXWidth() / 2);
				int y = (int)(tw.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tw.getYWidth() / 2);
				g.fillRect(x, y, (int)tw.getXWidth(), (int)tw.getYWidth());
			}
			for(Tree tr : trees) {
				int x = (int)(tr.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tr.getXWidth() / 2);
				int y = (int)(tr.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tr.getYWidth() / 2);
				g.fillRect(x, y, (int)tr.getXWidth(), (int)tr.getYWidth());
			}
		}*/
		
	}


