package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class TwigTest {

	@Test
	public void testInteractHarrier() {
		Twig t = new Twig(0, 0);
		Harrier h = new Harrier();
		
		t.interact(h);
		
		assertEquals(h.getScore(), 20);
		
		assertEquals((int) t.getXPos(), 0);
		
		assertEquals((int) t.getYPos(), 0);
		
		t.setXPos(5);
		t.setYPos(5);
		
		assertEquals((int) t.getXPos(), 5);
		
		assertEquals((int) t.getYPos(), 5);
		
		t.interact(h);
		
		assertEquals(h.getScore(), 40);
		
		
		h.setVision(75);
		t.interact(h);
		
		assertEquals(h.getScore(), -10);
			
		assertEquals((int)h.getVision(),63);
		
		h.setVision(700);
		t.interact(h);
		assertEquals((int)h.getVision(), (int)Harrier.MAX_VISION);
		
		
		
		
		
	}
}
