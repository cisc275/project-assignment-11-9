
import static org.junit.Assert.*;

import org.junit.Test;

public class OspreyTest {

	@Test
	void interactFish() {
		
		Osprey o = new Osprey();
		Fish f = new Fish(0, 0, 10);
		o.setXVel(2);
		
		
		o.interact(f);
		
		assertEquals(o.getXVel(), 4);
		
		fail("Not yet implemented");
	}
	
	@Test
	void interactSeaweed() {
		
		Osprey o = new Osprey();
		Seaweed s = new Seaweed(2, 2);
		o.setXVel(2);
		
		
		o.interact(s);
		
		assertEquals(o.getXVel(), -2);
		
		fail("Not yet implemented");
	}
	
	@Test
	void interactGoldenFish() {
		
		Osprey o = new Osprey();
		GoldenFish gf = new GoldenFish(0, 0);
		o.setXVel(2);
		
		
		o.interact(gf);
		
		assertEquals(o.getXVel(), 6);
		
		fail("Not yet implemented");
	}
	
	
	@Test
	void isDiving() {
		
		Osprey o = new Osprey();
		o.setYVel(1);
		
		o.dive();
		
		assertEquals(o.getYVel(), 2);

		
		fail("Not yet implemented");
	}

}
