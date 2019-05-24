package Project;
import static org.junit.Assert.*;

import org.junit.Test;

public class GoldenFishTest {

	@Test
	public void testInteract() {
		Osprey o = new Osprey();
		GoldenFish gf = new GoldenFish(0, 0);
		o.setXVel(5);
		gf.interact(o);
		assertEquals(o.getXVel(), 10, 0.0);
		
		o.setXVel(75);
		gf.interact(o);
		
	}
	
	@Test
	public void testGoldenFish() {
		GoldenFish gf = new GoldenFish(0, 0, true);
	}
	
}
