//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

package Project;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class OspreyView {
	
	private final int frameWidth = 1600;
	private final int frameHeight = 900;
	private final int drawDelay = 10;
	private JFrame frame;
	private OspreyHelper helper;
	
	public int getDelay() { return drawDelay; }
	
	public OspreyView() {
		helper = new OspreyHelper();
		frame = new JFrame();
		frame.getContentPane().add(helper);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		frame.repaint();
	}
	
	public void update(Osprey osprey, ArrayList<OspreyAble> objects) {
		helper.setOsprey(osprey);
		helper.setObjects(objects);
		frame.repaint();
	}
	
	public void addListener(Controller c) {
		frame.addKeyListener(c);
	}
	
	private class OspreyHelper extends JPanel {
		
		private Osprey osprey;
		private ArrayList<OspreyAble> objects;
		
		private OspreyHelper() {
			setOpaque(true);
			setBackground(Color.blue);
			osprey = new Osprey();
			objects = new ArrayList<>();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.fillRect(100, 100, (int)osprey.getXWidth(), (int)osprey.getYWidth());
			for(int i = 0; i < objects.size(); i++) {
				OspreyAble go = objects.get(i);
				int x = (int)(go.getXPos() - osprey.getXPos()) + 100;
				int y = (int)(go.getYPos() - osprey.getYPos()) + 100;
				if(x < frameWidth && x > 0 && y < frameHeight && y > 0) {
					g.fillRect(x, y, (int)go.getXWidth(), (int)go.getYWidth());
				}
			}
		}
		
		public Dimension getPreferredSize() { return new Dimension(frameWidth, frameHeight); }
		
		private void setOsprey(Osprey osprey) { this.osprey = osprey; }
		
		private void setObjects(ArrayList<OspreyAble> objects) { this.objects = objects; }
		
	}
	
}
