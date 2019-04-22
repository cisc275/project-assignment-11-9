package Project;
import static org.junit.Assert.*;

import org.junit.Test;

public class FishTest {

	@Test
	public void testInteract() {
		Osprey o = new Osprey();
		Fish f = new Fish(0, 0, 1);
		double xVel = o.getXVel();
		double yVel = o.getYVel();
		int size = f.getSize();
		f.interact(o);
		
		assertEquals(o.getXVel(), xVel + size, .00001);
		assertEquals(o.getYVel(), yVel, .00001);
		
		f.setSize(3);
		xVel = o.getXVel();
		yVel = o.getYVel();
		size = f.getSize();
		f.interact(o);
		
		assertEquals(o.getXVel(), xVel + size, .00001);
		assertEquals(o.getYVel(), yVel, .00001);
	}

}
