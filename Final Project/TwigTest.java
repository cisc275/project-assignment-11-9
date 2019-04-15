
import static org.junit.Assert.*;

import org.junit.Test;

public class TwigTest {

	@Test
	void testInteractHarrier() {
		Twig t = new Twig();
		Harrier h = new Harrier();
		
		t.interact(h);
		
		assertEquals(h.getScore(), 2);
		
		fail("Not yet implemented");
	}
}
