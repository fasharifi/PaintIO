package me.fa.GameLogic;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public interface enemycolor {
	

	public Color setrandomcolor1() ;
	public Color settcolor(Color color) ;
	

	//color pair choices for enemy
	public class enemy1 implements enemycolor {
		 
		Color ecolor ;
		Color etcolor ;
		Enemy e = new Enemy ();
		
		@Override
		public Color setrandomcolor1() {
			
			//random between tow colors
			Random random = new Random();
			int ra = random.nextInt();
			if(ra % 2 == 0 ) ecolor = (Color.BLUE);
			else  ecolor =(Color.CLEAR);
			
			return ecolor ;
		}
		
		public Color settcolor(Color color) {
			if(color== Color.BLUE)  etcolor=Color.SKY;
			else if(color == Color.CLEAR)  etcolor =Color.CYAN;
			return etcolor ;
		}
	}
	
	
	public class enemy2 implements enemycolor {
		
		Color ecolor ;
		Color etcolor ;
		Enemy e = new Enemy ();

		@Override
		public Color setrandomcolor1() {
			
			//random between tow colors
			Random random = new Random();
			int ra = random.nextInt();
			if(ra % 2 == 0 ) ecolor = (Color.RED);
			else ecolor = (Color.BROWN);
			
			return ecolor ;
		}

		@Override
		public Color settcolor(Color color) {
			if(color == Color.RED) return Color.ORANGE;
			else return Color.FIREBRICK;
		}
	}
	 
	
	public class enemy3 implements enemycolor {
		Color ecolor ;
		Color etcolor ;
		Enemy e = new Enemy ();

		@Override
		public Color setrandomcolor1() {
			
			Random random = new Random();
			int ra = random.nextInt();
			if(ra % 2 == 0 ) ecolor = (Color.YELLOW);
			else ecolor =(Color.GOLDENROD);
			
			return ecolor;
		}

		@Override
		public Color settcolor(Color color) {
			if(color == Color.YELLOW) return Color.CORAL;
			else return Color.GOLD;
		}
	}
	
	
	public class enemy4 implements enemycolor {
		Color ecolor ;
		Color etcolor ;
		Enemy e = new Enemy ();

		@Override
		public Color setrandomcolor1() {
			
			Random random = new Random();
			int ra = random.nextInt();
			if(ra % 2 == 0 ) ecolor = (Color.OLIVE);
			else ecolor=  (Color.FOREST);
			
			return ecolor;
		}

		@Override
		public Color settcolor(Color color) {
			if(color == Color.OLIVE) return Color.LIME;
			else return Color.GREEN;
		}
	}
	
	
}
