//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

import java.awt.event.*;

/* 
 * Public Controller class runs the game.
 */
public class Controller implements KeyListener {

	HarrierModel hModel;
	View hView;
	/*
	 * (non-Javadoc
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_UP:
			hModel.harrier.setXVel(0);
			hModel.harrier.setYVel(-10);
			break;
		case KeyEvent.VK_DOWN:
			hModel.harrier.setXVel(0);
			hModel.harrier.setYVel(10);
			break;
		case KeyEvent.VK_LEFT:
			hModel.harrier.setYVel(0);
			hModel.harrier.setXVel(-10);
			break;
		case KeyEvent.VK_RIGHT:
			hModel.harrier.setYVel(0);
			hModel.harrier.setXVel(10);
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}

	
	public void start() {
		hModel = new HarrierModel();
		hView = new View(hModel.getObjectTypes());
		hView.addListener(this);
		for (int i = 0; i < 50; i++) {
			hModel.update();
			hView.update(hModel.getXs(), hModel.getYs(), hModel.getDirs());
		}
		
	}
}
