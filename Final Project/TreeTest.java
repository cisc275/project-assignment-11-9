
import static org.junit.Assert.*;

import org.junit.Test;

public class TreeTest {

	@Test
	void testInteract() {
		
		Harrier h= new Harrier();
		
		Tree t = new Tree();
		
		t.interact(h);
		
		assertEquals(h.getScore(),-1);
		
		fail("Not yet implemented");
	}

}
