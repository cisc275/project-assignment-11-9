package Project;
import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectTest {
	
	@Test
	public void directionGetNameTest() {
		assertEquals(Direction.EAST.getName(), "east");
	}

	@Test
	public void testAnimalInteract() {
		Animal a = new Fish(0, 0, 1);
		GameObject g = new Fish(0, 0, 1);
		double xVel = a.getXVel();
		double yVel = a.getYVel();
		a.interact(g);
		
		assertEquals(a.getXVel(), -1 * xVel, .00001);
		assertEquals(a.getYVel(), -1 * yVel, .00001);
	}
	
	@Test
	public void testAnimalMove() {
		Animal a = new Fish(0, 0, 1);
		double xPos = a.getXPos();
		double yPos = a.getYPos();
		double xVel = a.getXVel();
		double yVel = a.getYVel();
		a.move();
		
		assertEquals(a.getXPos(), xPos + xVel, .00001);
		assertEquals(a.getYPos(), yPos + yVel, .00001);
	}
	
	@Test
	public void testAnimalSetDirection() {
		Animal a = new Fish(0, 0, 1);
		a.setDirection(Direction.EAST);
		assertEquals(a.getDirection(), Direction.EAST);
		a.setDirection(Direction.SOUTHEAST);
		assertEquals(a.getDirection(), Direction.SOUTHEAST);
	}
	@Test
	public void testAnimalUpdateDirection() {
		Animal a = new Fish(0, 0, 1);
		
		a.setXVel(1);
		a.setYVel(1);
		assertEquals(a.getDirection(), Direction.SOUTHEAST);
		
		a.setYVel(0);
		assertEquals(a.getDirection(), Direction.EAST);
		
		a.setYVel(-1);
		assertEquals(a.getDirection(), Direction.NORTHEAST);
		
		a.setXVel(-1);
		a.setYVel(1);
		assertEquals(a.getDirection(), Direction.SOUTHWEST);
		
		a.setYVel(0);
		assertEquals(a.getDirection(), Direction.WEST);
		
		a.setYVel(-1);
		assertEquals(a.getDirection(), Direction.NORTHWEST);
		
		a.setXVel(0);
		a.setYVel(1);
		assertEquals(a.getDirection(), Direction.SOUTH);
		
		a.setYVel(0);
		assertEquals(a.getDirection(), Direction.SOUTH);
		
		a.setYVel(-1);
		assertEquals(a.getDirection(), Direction.NORTH);
	}

	@Test
	public void testFishInteract() {
		Osprey o = new Osprey();
		Fish f = new Fish(0, 0, 1);
		double xVel = o.getXVel();
		double yVel = o.getYVel();
		int size = f.getSize();
		f.interact(o);
		
		assertEquals(o.getXVel(), xVel + size, .00001);
		assertEquals(o.getYVel(), yVel, .00001);
		
		f.setSize(3);
		xVel = o.getXVel();
		yVel = o.getYVel();
		size = f.getSize();
		f.interact(o);
		
		assertEquals(o.getXVel(), xVel + size, .00001);
		assertEquals(o.getYVel(), yVel, .00001);
	}

	@Test
	public void testFoxInteract() {
		Fox f = new Fox(0, 0);
		Harrier h = new Harrier();
		int score = h.getScore();
		double vision = h.getVision();
		f.interact(h);
		
		assertEquals(h.getScore(), score - 30);
		assertEquals(h.getVision(), vision - 3, .00001);
		
		score = h.getScore();
		vision = h.getVision();
		f.interact(h);
		
		assertEquals(h.getScore(), score - 30);
		assertEquals(h.getVision(), vision - 3, .00001);
	}

	@Test
	public void testFoxChase() {
		Fox f = new Fox(0, 0);
		Harrier h = new Harrier();
		
		h.setXPos(100);
		h.setYPos(-100);
		f.chase(h);
		
		assertEquals(f.getXVel(), 5, .001);
		assertEquals(f.getYVel(), -5, .001);
		
		h.setXPos(0);
		h.setYPos(0);
		f.chase(h);
		
		assertEquals(f.getXVel(), 0, .001);
		assertEquals(f.getYVel(), 0, .001);
		
		h.setXPos(-100);
		h.setYPos(100);
		f.chase(h);
		
		assertEquals(f.getXVel(), -5, .001);
		assertEquals(f.getYVel(), 5, .001);
	}
	
	@Test
	public void testGoldenFishInteract() {
		Osprey o = new Osprey();
		GoldenFish gf = new GoldenFish(0, 0);
		double xVel = o.getXVel();
		double yVel = o.getYVel();
		gf.interact(o);
		
		assertEquals(o.getXVel(), xVel + 10, .001);
		assertEquals(o.getYVel(), yVel, .001);
	}
	
	@Test
	public void goldenMouseTestInteract() {
		GoldenMouse gm = new GoldenMouse(0, 0);
		Harrier h= new Harrier();
		
		
		
		gm.interact(h);
		
		assertEquals(h.getScore(), 100);
		assertEquals(15, (int)h.getVision());
		
		
	}
	
	@Test
	public void testHarrierModelIsEnd() {
		HarrierModel hm = new HarrierModel();
		
		assertEquals(hm.isEnd(), false);
		hm.setTime(100000000);
		assertEquals(hm.isEnd(), true);
	}

	@Test
	public void testHarrierModelIsWin() {
		HarrierModel hm = new HarrierModel();
		
		assertEquals(hm.isWin(), false);
	
	}

	@Test
	public void testHarrierModelUpdate() {/*
		HarrierModel hm = new HarrierModel();
		Mouse m = new Mouse(0, 0);
		Tree t = new Tree(10, 10);
		
		hm.objects.add(m);
		hm.objects.add(t);
		hm.update();
		
		assertEquals((int)m.getXPos(), 2);
		assertEquals((int)hm.getHarrier().getXPos(), 0);
		*/
	
	}

	@Test
	public void testHarrierModelCheckInteractions() {
		/*
		HarrierModel hm = new HarrierModel();
		
		assertEquals(hm.getObjects().size(), 0);
		
		hm.initialize();
		
		assertEquals(hm.getObjects().size(), 6);
		
		hm.checkInteractions();

		assertEquals(hm.getObjects().size(), 6);*/
	}
	
	@Test
	public void harrierTestMoveNorth() {
		
		Harrier h = new Harrier();
		h.goNorth();
		assertEquals(-3,h.getYVel(), 0.01);

	}
	
	@Test
	public void harrierTestMoveSouth() {
		
		Harrier h = new Harrier();
		h.goSouth();
		assertEquals(3,h.getYVel(), 0.01);
		
		

	}
	
	@Test
	public void harrierTestMoveEast() {
		
		Harrier h = new Harrier();
		h.goEast();
		assertEquals(3,h.getXVel(), 0.01);
		
		

	}
	
	@Test
	public void harrierTestMoveWest() {
		
		Harrier h = new Harrier();
		h.goWest();
		assertEquals(-3,h.getXVel(), 0.01);
		
		

	}
	
	@Test
	public void harrierTestScore() {
		Harrier h = new Harrier();
		h.setScore(1);
		assertEquals(h.getScore(), 1);
	
		
	}
	
	@Test
	public void harrierTestVision() {
		Harrier h = new Harrier();
		h.setVision(10);
		assertEquals(10, h.getVision(), 0.01);
		
	}
	
	@Test
	public void modelTestCollisions() {/*
		Model m = new Model() {

			@Override
			public void initialize() {
			
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isEnd() {
				// TODO Auto-generated method stub
				return false;
			};

			@Override
			public boolean isWin() {
				// TODO Auto-generated method stub
				return false;
			};

			@Override
			public void update() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void checkInteractions() {
				// TODO Auto-generated method stub
				
			}};
			
			Harrier h = new Harrier();
			Mouse mo = new Mouse(2,2);
			
		
			assertEquals(m.isCollision(h, mo), true);
			
			
			
		*/
	}
	
	
	
	@Test
	public void modelTimeTest() {/*
		Model m = new Model() {

			@Override
			public void initialize() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isEnd() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isWin() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void update() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void checkInteractions() {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		m.setTime(1);
		assertEquals(m.getTime(), 1);*/
	}
	
	@Test
	public void modelQuizTest() {/*
		Model m = new Model() {

			@Override
			public void initialize() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean isEnd() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isWin() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void update() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void checkInteractions() {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		Quiz quiz = null;
		m.setQuiz(quiz);
		assertEquals(m.getQuiz(), null);
		*/
	}
	
	@Test
	public void testMouseInteractHarrier() {
		Mouse m = new Mouse(0, 0);
		Harrier h = new Harrier();
		

			
		
		m.interact(h);
		
		assertEquals(h.getScore(), 10);
	}
	
	@Test
	public void testDive() {
		
		
		Osprey o = new Osprey();
		
		o.dive();
		
		assertEquals((int)o.getYVel(), 5);
	}

	@Test
	public void ospreyTestRise() {
		
		Osprey o = new Osprey();
		
		o.rise();
		
		assertEquals((int)o.getYVel(), -5);
		
	
	}

	@Test
	public void ospreyTestToString() {
		
		Osprey o = new Osprey();
		
		assertEquals(o.toString(), "Osprey @ (0,0)");
		
	}
	
	@Test
	public void seaweedInteractTest() {
		
		Osprey o= new Osprey();
		
		Seaweed s = new Seaweed(0,0);
		
		s.interact(o);
		
		
		assertEquals((int) s.getXPos(), 0);
		
		assertEquals((int) s.getYPos(), 0);
		
		s.setXPos(5);
		s.setYPos(5);
		
		assertEquals((int) s.getXPos(), 5);
		
		assertEquals((int) s.getYPos(), 5);
		
		s.interact(o);
		
		o.setXVel(2);
		s.interact(o);;
		assertEquals((int) o.getXVel(), 4);
		
	}
	
	@Test
	public void treeInteractTest() {
		
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
		
	}
	
	@Test
	public void twigTestInteractHarrier() {
		Twig t = new Twig(0, 0);
		Harrier h = new Harrier();
		
		t.interact(h);
		
		assertEquals(h.getScore(), 20);
		
		assertEquals((int) t.getXPos(), 0);
		
		assertEquals((int) t.getYPos(), 0);
		
		t.setXPos(5);
		t.setYPos(5);
		
		assertEquals((int) t.getXPos(), 5);
		
		assertEquals((int) t.getYPos(), 5);
		
		t.interact(h);
		
		assertEquals(h.getScore(), 40);
	}
	
	@Test
	public void quizTestIsRight() {/*
		String[] questions= {"test", "test2"};
		char[] answers= {'a','b'};
		Quiz q= new Quiz(questions, answers);
		assertTrue(q.isRight(1, 'b'));
		assertFalse(q.isRight(1, 'a'));
		
		*/

	}

	@Test
	public void quizTestGetQuestions() {/*
		String[] questions= {"test", "test2"};
		char[] answers= {'a','b'};
		Quiz q= new Quiz(questions, answers);
		assertArrayEquals(new String[] {"test", "test2"}, q.getQuestions()); 

	}

	@Test
	public void quizTestGetAnswers() {
		String[] questions= {"test", "test2"};
		char[] answers= {'a','b'};
		Quiz q= new Quiz(questions, answers);
		assertArrayEquals(new char[] {'a', 'b'}, q.getAnswers());
*/
	}
	
	@Test
	public void ospreyModelTestIsEnd() {
		OspreyModel om= new OspreyModel();
		
		
		assertEquals(om.isEnd(), false);

	}

	@Test
	public void ospreyModelTestIsWin() {
		OspreyModel om = new OspreyModel();
		
		assertEquals(om.isWin(), false);
		
	}

	@Test
	public void ospreyModelTestUpdate() {/*
		
		OspreyModel om = new OspreyModel();
		Fish f = new Fish(0, 0, 10);
		
		om.objects.add(f);
		om.update();
		
		assertEquals((int)f.getXPos(), 2);
		assertEquals((int)om.getOsprey().getXPos(), 10);*/
	}

	@Test
	public void ospreyModelTestCheckInteractions() {/*
		OspreyModel om = new OspreyModel();
		
		assertEquals(om.getObjects().size(), 6);
		
		om.initialize();
		
		assertEquals(om.getObjects().size(), 12);
		om.getObjects().add(new Fish(0, 0, 10));
		assertEquals(om.getObjects().size(), 13);
		om.checkInteractions();

		assertEquals(om.getObjects().size(), 12);
		
		assertEquals(om.getStage(), 0);
		
		om.getOsprey().setXVel(-1);
		
		assertTrue(om.isEnd());
		
		om.getOsprey().setXPos(1000000);
		assertTrue(om.isWin());
		
		om.setStage(4);
		
		assertEquals(om.getStage(), 4);*/
	}
}
