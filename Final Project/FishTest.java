
import static org.junit.Assert.*;

import org.junit.Test;

public class FishTest {

	@Test
	void interactionTest() {
		
		Osprey o = new Osprey();
		Fish f = new Fish(0, 0, 20);
		
		f.interact(o);
		
		assertEquals(o.getXVel(), 1);
		
		fail("Not yet implemented");
	}
	
	@Test
	void moveTest() {
		
		Fish f = new Fish(0, 0, 20);
		
		f.move();
		
		assertEquals(f.getXPos(), -1);//moving to the left
	}

}
