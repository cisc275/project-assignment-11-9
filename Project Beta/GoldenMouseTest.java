package Project;
import static org.junit.Assert.*;

import org.junit.Test;

public class GoldenMouseTest {

	@Test
	public void testInteract() {
		GoldenMouse gm = new GoldenMouse(0, 0);
		Harrier h= new Harrier();
		
		h.setScore(200);
		
		gm.interact(h);
		assertEquals(h.getScore(), 300);
		
		h.setVision(600);
		gm.interact(h);
		assertEquals(h.getVision(), 600, 0);
	}
	
	@Test
	public void testGoldenMouse() {
		GoldenMouse gm = new GoldenMouse(0, 0, false);
	}
}
