package Project;
import static org.junit.Assert.*;

import org.junit.Test;

public class TreeTest {

	@Test
	public void Interacttest() {
		
		Harrier h= new Harrier();
		
		Tree t = new Tree(0,0);
		
		
		t.interact(h);
		
		
		assertEquals((int) h.getXPos(), 0);
		
		assertEquals((int) h.getYPos(), 0);
		
		t.setXPos(5);
		t.setYPos(5);
		
		assertEquals((int) t.getXPos(), 5);
		
		assertEquals((int) t.getYPos(), 5);
		
		h.setVision(135);
		t.interact(h);
		
		assertEquals(h.getScore(), -10);
			
		assertEquals((int)h.getVision(),135);
		
		h.setVision(15);
		t.interact(h);
		assertEquals((int)h.getVision(), (int)Harrier.MIN_VISION);
	}

}