
import static org.junit.Assert.*;

import org.junit.Test;

public class OspreyTest {

	@Test
	void isMovingtest() {
		
		Osprey o = new Osprey();
		
		o.move();
		assertEquals(o.getXPos(), 1);
		
		fail("Not yet implemented");
	}
	
	@Test
	void isDiving() {
		
		Osprey o = new Osprey();
		
		o.dive();
		assertEquals(o.getYPos(), -2);
		assertEquals(o.getYPos(), 2);
		
		fail("Not yet implemented");
	}

}
