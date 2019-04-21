package Project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModelTest {

	@Test
	void testCollisions() {
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
			
			
			
		
	}
	
	
	
	@Test
	public void timeTest() {
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
		assertEquals(m.getTime(), 1);
	}
	
	@Test
	public void quizTest() {
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
		
	}
	
	

}

