
package Project;
import static org.junit.Assert.*;

import org.junit.Test;


class OspreyModelTest {

	@Test
	void testIsEnd() {
		OspreyModel om= new OspreyModel();
		
		
		assertEquals(om.isEnd(), false);
	}

	@Test
	void testIsWin() {
		OspreyModel om = new OspreyModel();
		
		assertEquals(om.isWin(), false);
	}

	@Test
	void testUpdate() {
		
		OspreyModel om = new OspreyModel();
		Fish f = new Fish(0, 0, 1);
		Seaweed s = new Seaweed(10, 10);
		
		om.getFish().add(f);
		om.getSeaweed().add(s);
		om.update();
		
		assertEquals((int)f.getXPos(), 2);
		assertEquals((int)om.getOsprey().getXPos(), 0);
		
	}

}
