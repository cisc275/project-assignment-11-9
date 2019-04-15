
import static org.junit.Assert.*;

import org.junit.Test;

public class FoxTest {

	@Test
	void testInteract() {
		Fox f = new Fox(0, 0);
		Harrier h = new Harrier();
		
		f.interact(h);
		
		assertEquals(h.getScore(), -5);
		fail("Not yet implemented");
	}

	@Test
	void testMove() {
		Fox f = new Fox(0, 0);
		f.setXVel(1);
		f.setYVel(1);
		
		f.move();
		
		assertEquals(f.getXPos(),1);
		assertEquals(f.getYPos(),1);
		
		fail("Not yet implemented");
		fail("Not yet implemented");
	}

	@Test
	void testChase() {
		Fox f = new Fox(0, 0);
		Harrier h = new Harrier();
		
		h.setXPos(10);
		h.setYPos(-5);
		
		f.chase(h);
		
		assertEquals(f.getXVel(), 2);
		assertEquals(f.getYVel(), -1);
		fail("Not yet implemented");
	}

}
