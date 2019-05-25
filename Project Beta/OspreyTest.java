package Project;

import static org.junit.Assert.*;

import org.junit.Test;


class OspreyTest {


	@Test
	void testDive() {
		
		
		Osprey o = new Osprey();
		
		o.dive();
		
		assertEquals((int)o.getYVel(), 5);
		fail("Not yet implemented");
	}

	@Test
	void testRise() {
		
		Osprey o = new Osprey();
		
		o.rise();
		
		assertEquals((int)o.getYVel(), -5);
		
		
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		
		Osprey o = new Osprey();
		
		assertEquals(o.toString(), "Osprey @ (0,0)");
		

		
		fail("Not yet implemented");
	}

}
