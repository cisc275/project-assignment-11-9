package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoldenFishTest {

	@Test
	void test() {
		Osprey o = new Osprey();
		GoldenFish gf = new GoldenFish();
		
		gf.interact(o);
		
		assertEquals(o.getXVel(), 2);
		
		fail("Not yet implemented");
	}
}
