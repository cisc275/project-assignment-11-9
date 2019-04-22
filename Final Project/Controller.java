//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa

import java.util.*;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.EventQueue;
import java.awt.event.*;

/* 
 * Public Controller class runs the game.
 */
public class Controller implements KeyListener {

	int num;
	private OspreyModel om;
	private OspreyView ov;

	private HarrierModel hm;
	private HarrierView hv;
	View view;

	public void start(int num) {

		this.num = num;

		if(num == 1) {

			om = new OspreyModel();
			om.initialize();
			ov = new OspreyView();

			ov.addListener(this);

			Timer timer = new Timer();
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					om.update();
					ov.update(om.getOsprey(), om.getObjects());
				}
			};
			timer.schedule(timerTask, 0, 50);

		}
		else {

			hm = new HarrierModel();
			hm.initialize();
			hv = new HarrierView();

			hv.addListener(this);

			Timer timer = new Timer();
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					hm.update();
					hv.update(hm.getHarrier(), hm.getObjects());
				}
			};
			timer.schedule(timerTask, 0, 50);
		}
	}



	/*
	 * (non-Javadoc)
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
		if(num == 1) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE && !om.getOsprey().isRising()) {
				om.getOsprey().dive();
			}
		}
		else {
			if(e.getKeyCode() == KeyEvent.VK_UP) { hm.getHarrier().goNorth(); }
			if(e.getKeyCode() == KeyEvent.VK_DOWN) { hm.getHarrier().goSouth(); }
			if(e.getKeyCode() == KeyEvent.VK_LEFT) { hm.getHarrier().goWest(); }
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) { hm.getHarrier().goEast(); }
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(num == 1) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				om.getOsprey().rise();
			}
		}
		else {
			
		}
	}

	public Controller() {
		view = new View();
	}
	public void setNum(int i) {
		num = i;
	}
	public int getNum() {
		return num;
	}

}
