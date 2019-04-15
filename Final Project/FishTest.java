package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class FishTest {

	@Test
	void interactionTest() {
		
		Osprey o = new Osprey();
		Fish f = new Fish();
		
		f.interact(o);
		
		assertEquals(o.getXVel(), 1);
		
		fail("Not yet implemented");
	}
	
	@Test
	void moveTest() {
		
		Fish f = new Fish();
		
		f.move();
		
		assertEquals(f.getXPos(), -1);//moving to the left
	}

}
