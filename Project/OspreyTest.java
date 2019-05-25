package Project;

import static org.junit.Assert.*;

import org.junit.Test;

public class OspreyTest {

	@Test
	public void testDive() {

		Osprey o = new Osprey();

		o.dive();

		assertEquals((int) o.getYVel(), 15);
	}

	@Test
	public void testRise() {

		Osprey o = new Osprey();

		o.rise();

		assertEquals((int) o.getYVel(), -15);

	}

	@Test
	public void testmove() {
		Osprey o = new Osprey();
		o.setYVel(2);
		o.move();
		assertEquals((int) o.getXPos(), 5);
		assertEquals((int) o.getYPos(), 2);

	}

}