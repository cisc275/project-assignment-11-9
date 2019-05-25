package Project;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameObjectTest {

	@Test
	public void testCalcDist() {
		GameObject o = new Osprey();
		o.calcDist(o);
		assertEquals(o.calcDist(o), 0);
		
		o.calcDist();
		assertEquals(o.calcDist(), 0);
	}

	@Test
	public void testApproximateDirection() {
		GameObject o = new Harrier();
		Mouse m = new Mouse(0, 0);
		o.getApproximateDirection(m);
		assertEquals(o.getApproximateDirection(m),Direction.SOUTH);
		
		o.setXPos(50);
		m.setXPos(75);
		o.setYPos(50);
		m.setYPos(60);
		o.getApproximateDirection(m);
		assertEquals(o.getApproximateDirection(m), Direction.WEST);
		
		o.setXPos(75);
		m.setXPos(50);
		o.setYPos(60);
		m.setYPos(50);
		o.getApproximateDirection(m);
		assertEquals(o.getApproximateDirection(m), Direction.EAST);
		
		o.setXPos(0);
		m.setXPos(0);
		o.setYPos(50);
		m.setYPos(60);
		o.getApproximateDirection(m);
		assertEquals(o.getApproximateDirection(m), Direction.NORTH);
		
	}
	
	@Test
	public void testGetX() {
		GameObject o = new Osprey();
		o.setXPos(0);
		assertEquals(o.getXPos(), 0);
	}
	
	@Test
	public void testGetY() {
		GameObject o = new Osprey();
		o.setYPos(0);
		assertEquals(o.getYPos(), 0);
	}
	
	@Test
	public void testGetXWidth() {
		GameObject o = new Osprey();
		o.setXWidth(0);
		assertEquals(o.getXWidth(), 0);
	}
	
	@Test
	public void testGetYWidth() {
		GameObject o = new Osprey();
		o.setYWidth(0);
		assertEquals(o.getYWidth(), 0);
	}
}
