
import static org.junit.Assert.*;

import org.junit.Test;

public class HarrierModelTest {

	@Test
	void testIsEnd() {
		HarrierModel hm = new HarrierModel();
		
		assertEquals(hm.isEnd(), false);
		
		fail("Not yet implemented");
	}

	@Test
	void testIsWin() {
		HarrierModel hm = new HarrierModel();
		
		assertEquals(hm.isWin(), false);
		
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		HarrierModel hm = new HarrierModel();
		Harrier h = new Harrier();
		Mouse m = new Mouse(0, 0);
		Tree t = new Tree(10, 10);
		
		hm.update();
		
		assertEquals(m.getXPos(), -1);
		assertEquals(h.getXPos(), 1);
		
		
		fail("Not yet implemented");
	}

	@Test
	void testCheckInteractions() {
		
		HarrierModel hm = new HarrierModel();
		
		hm.checkInteractions();

		assertEquals(hm.equals(new HarrierModel()), true);
		fail("Not yet implemented");
	}
}
