package Project;
import static org.junit.Assert.*;

import org.junit.Test;

public class FishTest {

	@Test
	public void testInteract() {
		Osprey o = new Osprey();
		Fish f = new Fish(0, 0, 1);
		f.setSize(2);
		o.setXVel(80);
		f.interact(o);
		assertEquals(o.getXVel(), 40, 0.0001);
		
		o.setXVel(18);
		f.interact(o);
	}
	
	@Test
	public void testGetSize() {
		Fish f = new Fish(0, 0, 1);
		f.setSize(2);
		assertEquals(f.getSize(), 2);
	}

}