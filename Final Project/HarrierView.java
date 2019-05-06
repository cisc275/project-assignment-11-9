//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.util.*;
import java.awt.image.*;
import javax.swing.*;

public class HarrierView extends GameView {

	private HarrierHelper helper;
	private BufferedImage[] images;

	public HarrierView() {
		super();
		initializeImages();
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
	
	private void initializeImages() {
		images = new BufferedImage[7];
		images[0] = createBufferedImage("HarrierBackground.png");
		images[1] = createBufferedImage("Harrier Overtop.png");
		images[2] = createBufferedImage("fox.png");
		images[3] = createBufferedImage("mouse.png");
		images[4] = createBufferedImage("twig.png");
		images[5] = createBufferedImage("Tree.png");
		images[6] = createBufferedImage("vision.png");
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
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			//g.drawImage(images[0], 0, 0,TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, null);
			g.drawImage(images[1],
						TitleView.FRAME_WIDTH / 2 - (int)(harrier.getXWidth() / 2),
						TitleView.FRAME_HEIGHT / 2 - (int)(harrier.getYWidth() / 2),
						(int)harrier.getXWidth(), (int)harrier.getYWidth(), this);
			for(Fox f : foxes) {
				int x = (int)(f.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(f.getXWidth() / 2);
				int y = (int)(f.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(f.getYWidth() / 2);
				g.drawImage(images[2], x, y, (int)f.getXWidth(), (int)f.getYWidth(), this);
			}
			for(Mouse m : mice) {
				int x = (int)(m.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(m.getXWidth() / 2);
				int y = (int)(m.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(m.getYWidth() / 2);
				g.drawImage(images[3], x, y, (int)m.getXWidth(), (int)m.getYWidth(), this);
			}
			for(Twig tw : twigs) {
				int x = (int)(tw.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tw.getXWidth() / 2);
				int y = (int)(tw.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tw.getYWidth() / 2);
				g.drawImage(images[4], x, y, (int)tw.getXWidth(), (int)tw.getYWidth(), this);
			}
			for(Tree tr : trees) {
				int x = (int)(tr.getXPos() - harrier.getXPos()) + TitleView.FRAME_WIDTH / 2 - (int)(tr.getXWidth() / 2);
				int y = (int)(tr.getYPos() - harrier.getYPos()) + TitleView.FRAME_HEIGHT / 2 - (int)(tr.getYWidth() / 2);
				g.drawImage(images[5], x, y, (int)tr.getXWidth(), (int)tr.getYWidth(), this);
			}
			g.drawImage(images[6], 
						(int) (0 - harrier.getVision()*20), (int) (0 - harrier.getVision()*20),
						(int) (TitleView.FRAME_WIDTH + harrier.getVision()*20), (int) (TitleView.FRAME_HEIGHT + harrier.getVision()*20),
						0, 0, images[6].getWidth(this), images[6].getHeight(this), this);
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

}
