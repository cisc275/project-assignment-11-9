
package Project;
import static org.junit.Assert.*;

import org.junit.Test;


class OspreyModelTest {

	@Test
	void testIsEnd() {
		OspreyModel om= new OspreyModel();
		
		
		assertEquals(om.isEnd(), false);

	
		fail("Not yet implemented");
	}

	@Test
	void testIsWin() {
		OspreyModel om = new OspreyModel();
		
		assertEquals(om.isWin(), false);
		
		
		
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		
		OspreyModel om = new OspreyModel();
		Mouse m = new Mouse(0, 0);
		Tree t = new Tree(10, 10);
		
		om.objects.add((OspreyAble) m);
		om.objects.add((OspreyAble) t);
		om.update();
		
		assertEquals((int)m.getXPos(), 2);
		assertEquals((int)om.getOsprey().getXPos(), 0);
		
		
		
		
		
		fail("Not yet implemented");
	}

	@Test
	void testCheckInteractions() {
		fail("Not yet implemented");
	}

}
