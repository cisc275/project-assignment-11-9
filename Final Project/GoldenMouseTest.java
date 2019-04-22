package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoldenMouseTest {

	@Test
	public void testInteract() {
		GoldenMouse gm = new GoldenMouse(0, 0);
		Harrier h= new Harrier();
		
		
		
		gm.interact(h);
		
		assertEquals(h.getScore(), 100);
		
		
		
		
	}
}
