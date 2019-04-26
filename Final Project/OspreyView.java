package Project;
//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class OspreyView extends View{
	
	private OspreyHelper helper;
	private JFrame frameO;
	
	
	
	public OspreyView() {
		helper = new OspreyHelper();
		frameO = new JFrame();
		frameO.getContentPane().add(helper);
		frameO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameO.setSize(FRAMEWIDTH, FRAMEHEIGHT);
		frameO.setVisible(true);
		frameO.repaint();
	
		
	}
	
	public void update(Osprey osprey, ArrayList<OspreyAble> objects) {
		helper.setOsprey(osprey);
		helper.setObjects(objects);
		frameO.repaint();
	}
	
	public void addListener(Controller c) {
		frameO.addKeyListener(c);
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
				if(x < FRAMEWIDTH && x > 0 && y < FRAMEHEIGHT && y > 0) {
					g.fillRect(x, y, (int)go.getXWidth(), (int)go.getYWidth());
				}
			}
		}
		
		public Dimension getPreferredSize() { return new Dimension(FRAMEWIDTH, FRAMEHEIGHT); }
		
		private void setOsprey(Osprey osprey) { this.osprey = osprey; }
		
		private void setObjects(ArrayList<OspreyAble> objects) { this.objects = objects; }
		
	}
	
}
