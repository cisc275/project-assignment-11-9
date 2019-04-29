
//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

import java.util.*;
import java.awt.*;
import javax.swing.*;

import java.awt.geom.Ellipse2D;

import java.awt.geom.Area;

public class HarrierView extends View{

	
	
	private JFrame frameH;
	private HarrierHelper helper;



	public HarrierView() {
		helper = new HarrierHelper();
		frameH = new JFrame();
		frameH.getContentPane().add(helper);
		frameH.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameH.setSize(FRAMEWIDTH, FRAMEHEIGHT);
		frameH.setVisible(true);
		frameH.repaint();
	}

	public void update(Harrier harrier, ArrayList<HarrierAble> objects) {
		helper.setHarrier(harrier);
		helper.setObjects(objects);
		frameH.repaint();
	}
	
	public void addListener(Controller c) {
		frameH.addKeyListener(c);
	}

	private class HarrierHelper extends JPanel {

		private Harrier harrier;
		private ArrayList<HarrierAble> objects;

		private HarrierHelper() {
			setOpaque(true);
			setBackground(Color.green);
			harrier = new Harrier();
			objects = new ArrayList<>();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.fillRect(FRAMEWIDTH / 2, FRAMEHEIGHT / 2, (int)harrier.getXWidth(), (int)harrier.getYWidth());
			for(int i = 0; i < objects.size(); i++) {
				HarrierAble go = objects.get(i);
				int x = (int)(go.getXPos() - harrier.getXPos()) + FRAMEWIDTH / 2;
				int y = (int)(go.getYPos() - harrier.getYPos()) + FRAMEHEIGHT / 2;
				if(go.getXPos() <= harrier.getXPos() + harrier.getVision() && 
						go.getXPos() >= harrier.getXPos() - harrier.getVision() && 
						go.getYPos() <= harrier.getYPos() + harrier.getVision() && 
						go.getYPos() >= harrier.getYPos() - harrier.getVision()) {
					g.fillRect(x, y, (int)go.getXWidth(), (int)go.getYWidth());
				}
			}
		}

		public Dimension getPreferredSize() { return new Dimension(FRAMEWIDTH, FRAMEHEIGHT); }

		private void setHarrier(Harrier harrier) { this.harrier = harrier; }

		private void setObjects(ArrayList<HarrierAble> objects) { this.objects = objects; }
		
	}
}
