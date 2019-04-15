//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class HarrierView {

	private final int frameWidth = 1600;
	private final int frameHeight = 900;
	private final int drawDelay = 10;
	private JFrame frame;
	private HarrierHelper helper;

	public int getDelay() { return drawDelay; }

	public HarrierView() {
		helper = new HarrierHelper();
		frame = new JFrame();
		frame.getContentPane().add(helper);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.repaint();
	}

	public void update(Harrier harrier, ArrayList<HarrierAble> objects) {
		helper.setHarrier(harrier);
		helper.setObjects(objects);
		frame.repaint();
	}
	
	public void addListener(Controller c) {
		frame.addKeyListener(c);
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
			g.fillRect(frameWidth / 2, frameHeight / 2, (int)harrier.getXWidth(), (int)harrier.getYWidth());
			for(int i = 0; i < objects.size(); i++) {
				HarrierAble go = objects.get(i);
				int x = (int)(go.getXPos() - harrier.getXPos()) + frameWidth / 2;
				int y = (int)(go.getYPos() - harrier.getYPos()) + frameHeight / 2;
				if(x < frameWidth && x > 0 && y < frameHeight && y > 0) {
					g.fillRect(x, y, (int)go.getXWidth(), (int)go.getYWidth());
				}
			}
		}

		public Dimension getPreferredSize() { return new Dimension(frameWidth, frameHeight); }

		private void setHarrier(Harrier harrier) { this.harrier = harrier; }

		private void setObjects(ArrayList<HarrierAble> objects) { this.objects = objects; }
		
	}
}
