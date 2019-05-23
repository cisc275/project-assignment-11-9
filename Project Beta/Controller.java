//Authors: Vincent Beardsley, Suryanash Gupta, Tyler Ballance, Brandon Raffa
package Project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import static org.junit.Assert.fail;

/* 
 * Public Controller class runs the game.
 */
public class Controller implements KeyListener, java.io.Serializable {

	private GameState gs;
	private Model model;
	private JPanel view;
	private JFrame frame;
	private CardLayout layout;
	private Timer timerO, timerH;
	private boolean paused;
	private boolean canSaveLoad;
	public final static int TICK_TIME = 20;
	
	public Controller() {
		view = new JPanel();
		layout = new CardLayout();
		frame = new JFrame();
		
		view.setLayout(layout);
		view.add(new TitleView(), "tv", GameState.TITLE.getNum());
		view.add(new OspreyView(), "ov", GameState.OSPREY.getNum());
		view.add(new HarrierView(), "hv", GameState.HARRIER.getNum());
		view.add(new GameOverView(), "go", GameState.END.getNum());
		view.add(new OspreyEnding(), "oe", GameState.END.getNum());
		view.add(new HarrierEnding(), "he", GameState.END.getNum());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT);
		frame.setBackground(Color.BLACK);
		frame.add(view);
		frame.setVisible(true);
		frame.addKeyListener(this);
		
		timerO = new Timer(TICK_TIME, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (!paused) {
					OspreyModel om = (OspreyModel) model;
					OspreyView ov = (OspreyView) view.getComponent(gs.getNum());
					if (om.isWin()) {
						ov.checkTime();
						endOsprey();
					} else if (om.isEnd()) {
						gameOver();
					} else {
						om.update();
						ov.update(om.getOsprey(), om.getFish(), om.getSeaweed(), om.getTutorial());
						frame.repaint();
					}
				}
				else {
					try { Thread.sleep(TICK_TIME); }
					catch(InterruptedException e) { e.printStackTrace(); }
				}
			}
		});
		timerH = new Timer(TICK_TIME, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (!paused) {
					HarrierModel hm = (HarrierModel) model;
					HarrierView hv = (HarrierView) view.getComponent(gs.getNum());
					if (hm.isWin()) {
						hv.checkScore();
						endHarrier();
					} else if (hm.isEnd()) {
						gameOver();
					} else {
						hm.update();
						hv.update(hm.getHarrier(), hm.getFoxes(), hm.getMice(), hm.getTwigs(), hm.getTrees(),
								hm.getTutorial());
						frame.repaint();
					}
				}
				else {
					try { Thread.sleep(TICK_TIME); }
					catch (InterruptedException e) { e.printStackTrace(); }
				}
			}
		});
	}
	
	/*
	 * public method start.
	 * Takes no parameters and returns nothing.
	 * Opens the title screen.
	 */
	public void start() {
		gs = GameState.TITLE;
		model = null;
		layout.show(view, "tv");
		paused = false;
	}

	/*
	 * public method startOsprey.
	 * Takes no parameters and returns nothing.
	 * Runs the game that involves the osprey.
	 */
	public void startOsprey() {
		gs = GameState.OSPREY;
		model = new OspreyModel();
		layout.show(view, "ov");
		frame.requestFocus();
		timerO.start();
	}
	
	/*
	 * public method startOsprey.
	 * Takes no parameters and returns nothing.
	 * Runs the game that involves the harrier.
	 */
	public void startHarrier() {
		gs = GameState.HARRIER;
		model = new HarrierModel();
		layout.show(view, "hv");
		frame.requestFocus();
		timerH.start();
	}

	/* 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(gs != GameState.TITLE) {
			if (e.getKeyCode() == KeyEvent.VK_A) {
				canSaveLoad = ! canSaveLoad;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				save();
			} else if (e.getKeyCode() == KeyEvent.VK_L) {
				load();
			}
			if (gs == GameState.OSPREY) {
				OspreyModel om = (OspreyModel)model;
				if (e.getKeyCode() == KeyEvent.VK_SPACE && !om.getOsprey().getIsRecovering()) { om.getOsprey().dive(); }
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { timerO.stop(); start(); }
			}
			else if(gs == GameState.HARRIER) {
				HarrierModel hm = (HarrierModel)model;
				if (e.getKeyCode() == KeyEvent.VK_UP) { hm.getHarrier().goNorth(); }
				if (e.getKeyCode() == KeyEvent.VK_DOWN) { hm.getHarrier().goSouth(); }
				if (e.getKeyCode() == KeyEvent.VK_LEFT) { hm.getHarrier().goWest(); }
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) { hm.getHarrier().goEast(); }
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { timerH.stop(); start(); }
			}
			if (e.getKeyCode() == KeyEvent.VK_CONTROL) { GameView gv = (GameView)view.getComponent(gs.getNum()); gv.setIsDebug(!gv.getIsDebug()); }
			if (e.getKeyCode() == KeyEvent.VK_P) { paused = !paused; }
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { start(); }
		}
		else {
			if(e.getKeyCode() == KeyEvent.VK_O) { startOsprey(); }
			else if(e.getKeyCode() == KeyEvent.VK_H) { startHarrier(); }
		}
	}

	/* 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (gs == GameState.OSPREY) {
				OspreyModel om = (OspreyModel)model;
				om.getOsprey().rise(); 
			}
		}
	}

	/*
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
	
	private void endOsprey() {
		gs = GameState.END;
		layout.show(view, "oe");
		frame.requestFocus();
		timerO.stop();

	}

	private void endHarrier() {
		gs = GameState.END;
		layout.show(view, "he");
		frame.requestFocus();
		timerH.stop();
	}

	private void gameOver() {
		if (gs == GameState.OSPREY) {
			timerO.stop();
		} else if (gs == GameState.HARRIER) {
			timerH.stop();
		}
		gs = GameState.END;
		layout.show(view, "go");
		frame.requestFocus();
	}
	
	public void save() {
		try {
			FileOutputStream fos = null;
			ObjectOutputStream oos;
			if (gs == GameState.OSPREY) {
				fos = new FileOutputStream("ospreyfile.ser");
			} else if (gs == GameState.HARRIER){
				fos = new FileOutputStream("harrierfile.ser");
			}		
			oos = new ObjectOutputStream(fos);
			oos.writeObject(model);
			oos.close();
			canSaveLoad = false;
		}
		catch (Exception ex)
		{
			fail("Exception thrown during test: " + ex.toString());
		}
	}

	public void load() {
		try {
			FileInputStream fis = null;
			ObjectInputStream ois;
			if (gs == GameState.OSPREY) {
				fis = new FileInputStream("ospreyfile.ser");
			} else if (gs == GameState.HARRIER){
				fis = new FileInputStream("harrierfile.ser");
			}		
			ois = new ObjectInputStream(fis);
			model = (OspreyModel) ois.readObject();
			ois.close();
			canSaveLoad = false;
		}
		catch (Exception ex)
		{
			fail("Exception thrown during test: " + ex.toString());
		}
	}

}