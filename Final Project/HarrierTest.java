package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class HarrierTest {

	@Test
	void testMove() {
		Harrier h = new Harrier();
		
		h.move();
		
		assertEquals(h.getXVel(),1);
		assertEquals(h.getYVel(), 1);
		
		fail("Not yet implemented");
	}

}
