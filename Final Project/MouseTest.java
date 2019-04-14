package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class MouseTest {

	@Test
	void testMove() {
		Mouse m = new Mouse();
		m.setXVel(1);
		m.setYVel(1);
		
		m.move();
		
		assertEquals(m.getXPos(),1);
		assertEquals(m.getYPos(),1);
		
		fail("Not yet implemented");
	}
	
	@Test
	void testInteractHarrier() {
		Mouse m = new Mouse();
		Harrier h = new Harrier();
		
		m.interact(h);
		
		assertEquals(h.getScore(), 1);
		
		fail("Not yet implemented");
	}

}
