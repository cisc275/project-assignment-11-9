package Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreeTest {

	@Test
	void Interacttest() {
		
		Harrier h= new Harrier();
		
		Tree t = new Tree(0,0);
		
		t.interact(h);
		
		
		assertEquals((int) h.getXPos(), 0);
		
		assertEquals((int) h.getYPos(), 0);
		
		t.setXPos(5);
		t.setYPos(5);
		
		assertEquals((int) t.getXPos(), 5);
		
		assertEquals((int) t.getYPos(), 5);
		
		t.interact(h);
		
		assertEquals(h.getScore(), -20);
		
		fail("Not yet implemented");
	}

}
