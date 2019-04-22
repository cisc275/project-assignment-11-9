package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoldenFishTest {

	@Test
	public void testInteract() {
		Osprey o = new Osprey();
		GoldenFish gf = new GoldenFish(0, 0);
		double xVel = o.getXVel();
		double yVel = o.getYVel();
		gf.interact(o);
		
		assertEquals(o.getXVel(), xVel + 10, .001);
		assertEquals(o.getYVel(), yVel, .001);
	}
	
}
