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
		assertEquals(h.getVision(), vision - 3, .00001);
		
		score = h.getScore();
		vision = h.getVision();
		f.interact(h);
		
		assertEquals(h.getScore(), score - 30);
		assertEquals(h.getVision(), vision - 3, .00001);
	}

	@Test
	public void testChase() {
		Fox f = new Fox(0, 0);
		Harrier h = new Harrier();
		
		h.setXPos(100);
		h.setYPos(-100);
		f.chase(h);
		
		assertEquals(f.getXVel(), 5, .001);
		assertEquals(f.getYVel(), -5, .001);
		
		h.setXPos(0);
		h.setYPos(0);
		f.chase(h);
		
		assertEquals(f.getXVel(), 0, .001);
		assertEquals(f.getYVel(), 0, .001);
		
		h.setXPos(-100);
		h.setYPos(100);
		f.chase(h);
		
		assertEquals(f.getXVel(), -5, .001);
		assertEquals(f.getYVel(), 5, .001);
	}

}
