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
						hv.update(hm.getHarrier(), hm.getFoxes(), hm.getMice(), hm.getTwigs(), hm.getTrees(), hm.getStage());
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
	 * Parameters: none
	 * Returns: nothing
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
	 * Parameters: none
	 * Returns: nothing
	 * Opens the osprey game.
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
	 * Parameters: none
	 * Returns: nothing
	 * Opens the harrier game.
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
		switch(e.getKeyCode()) {
		case KeyEvent.VK_O:
			if (gs == GameState.TITLE) { startOsprey(); }
			break;
		case KeyEvent.VK_H:
			if (gs == GameState.TITLE) { startHarrier(); }
			break;
		case KeyEvent.VK_ESCAPE:
			if (gs == GameState.OSPREY) { timerO.stop(); } 
			else if (gs == GameState.HARRIER) { timerH.stop(); }
			
			if (gs != GameState.TITLE) { start(); }
			break;
		case KeyEvent.VK_CONTROL:
			if (gs != GameState.TITLE) { 
				GameView gv = (GameView)view.getComponent(gs.getNum()); 
				gv.setIsDebug(!gv.getIsDebug()); 
			}
			break;
		case KeyEvent.VK_P:
			if (gs != GameState.TITLE) { paused = !paused; }
			break;
		case KeyEvent.VK_SHIFT:
			if (gs == GameState.OSPREY || gs == GameState.HARRIER) { canSaveLoad = !canSaveLoad; }
			break;
		case KeyEvent.VK_S:
			if (canSaveLoad && (gs == GameState.OSPREY || gs == GameState.HARRIER)) { save(); }
			break;
		case KeyEvent.VK_L:
			if (canSaveLoad && (gs == GameState.OSPREY || gs == GameState.HARRIER)) { load(); }
			break;
		case KeyEvent.VK_SPACE:
			if (gs == GameState.OSPREY) {
				OspreyModel om = (OspreyModel) model;
				if (!om.getOsprey().getIsRecovering()) { om.getOsprey().dive(); }
			}
			break;
		case KeyEvent.VK_UP:
			if (gs == GameState.HARRIER) {
				HarrierModel hm = (HarrierModel) model;
				hm.getHarrier().goNorth();
			}
			break;
		case KeyEvent.VK_DOWN:
			if (gs == GameState.HARRIER) {
				HarrierModel hm = (HarrierModel) model;
				hm.getHarrier().goSouth();
			}
			break;
		case KeyEvent.VK_LEFT:
			if (gs == GameState.HARRIER) {
				HarrierModel hm = (HarrierModel) model;
				hm.getHarrier().goWest();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (gs == GameState.HARRIER) {
				HarrierModel hm = (HarrierModel) model;
				hm.getHarrier().goEast();
			}
			break;
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
	
	/*
	 * private method endOsprey
	 * Parameters: none
	 * Returns: nothing
	 * Ends the Osprey game, and switches to the OspreyEnding Screen.
	 */
	private void endOsprey() {
		gs = GameState.END;
		layout.show(view, "oe");
		frame.requestFocus();
		timerO.stop();

	}

	/*
	 * private method endHarrier
	 * Parameters: none
	 * Returns: nothing
	 * Ends the Harrier game, and switches to the HarrierEnding Screen.
	 */
	private void endHarrier() {
		gs = GameState.END;
		layout.show(view, "he");
		frame.requestFocus();
		timerH.stop();
	}

	/*
	 * private method gameOver
	 * Parameters: none
	 * Returns: nothing
	 * Ends the game, and switches to the GameOver Screen.
	 */
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
	
	/*
	 * private method save
	 * Parameters: none
	 * Returns: nothing
	 * Serializes the model variable and stores it in a file.
	 */
	private void save() {
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

	/*
	 * private method load
	 * Parameters: none
	 * Returns: nothing
	 * Loads a serialized model from a file and sets 
	 * the model to be the newly loaded one
	 */
	private void load() {
		try {
			FileInputStream fis = null;
			ObjectInputStream ois;
			if (gs == GameState.OSPREY) {
				fis = new FileInputStream("ospreyfile.ser");
				ois = new ObjectInputStream(fis);
				model = (OspreyModel) ois.readObject();
				ois.close();
			} else if (gs == GameState.HARRIER){
				fis = new FileInputStream("harrierfile.ser");
				ois = new ObjectInputStream(fis);
				model = (HarrierModel) ois.readObject();
				ois.close();
			}		
			canSaveLoad = false;
		}
		catch (Exception ex)
		{
			fail("Exception thrown during test: " + ex.toString());
		}
	}

}