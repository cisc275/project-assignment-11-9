package Project;
import static org.junit.Assert.*;

import org.junit.Test;

public class FoxTest {

	@Test
	public void testInteract() {
		Fox f = new Fox(0, 0);
		Harrier h = new Harrier();
		int score = h.getScore();
		double vision = h.getVision();
		f.interact(h);
		
		assertEquals(h.getScore(), score - 30);
	}

	@Test
	public void testChase() {
		Fox f = new Fox(0, 0);
		Harrier h = new Harrier();
		
		h.setXPos(100);
		h.setYPos(-100);
		f.chase(h);
	}
	
	@Test
	public void testRoam() {
		Fox f = new Fox(0,0);
		Harrier h = new Harrier();
		f.roam(h, TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, -1);
		
		h.setXPos(200);
		h.setYPos(200);
		f.setXPos(100);
		f.setYPos(25);
		f.roam(h, TitleView.FRAME_WIDTH, TitleView.FRAME_HEIGHT, -1);
	}

}