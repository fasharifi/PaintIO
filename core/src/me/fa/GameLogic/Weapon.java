package me.fa.GameLogic;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Color;

import me.fa.game.PaintioGame;
import me.fa.game.Screen.MainGameScreen;
import me.fa.game.Screen.rectangle;

abstract class  Weapon {
	
	private Backgroundhandling backgroundhandling ;
	
	protected int cellsize = PaintioGame.CELLSIZE;
	@SuppressWarnings("static-access")
	protected ArrayList <rectangle> rects = backgroundhandling.rects;
	protected ArrayList <rectangle> checkrects = new  ArrayList <rectangle> ();
	protected ArrayList <rectangle> checkrects1 = new  ArrayList <rectangle> ();
	protected ArrayList <Enemy> enemy = MainGameScreen.enemy;

	
	public static Color ecolor ;
	public static Color tcolor ;
	public static boolean isstop = false ;

	
	//abstract methods
	public abstract void shooting(float x , float y , Color color,boolean isup , boolean isleft, boolean isright, boolean isdown) ;
	
	public abstract void checkenemy();
	
}	
	class WeaponA extends Weapon {
		
		//it has number of shots
		private final int numberofshots = 4 ;
		
		@Override
		public void shooting(float x , float y , Color color,boolean isup , boolean isleft, boolean isright, boolean isdown) {
			
			//save all the possible neighbors to the list
			for(rectangle rect : rects) {
				if(isleft) {
					float newx=x-(6*cellsize);
					for(float j=y ; j < y+(3*cellsize) ; j+=cellsize) {
						for(float i=newx ; i > newx-(3*cellsize) ; i-=cellsize) {
							if(rect.getX()==i && rect.getY()==j) {
								checkrects.add(rect);
								checkenemy();
								rect.setColor(color);
							}
						}
					}
				}
				if(isright) {
					float newx=x+(6*cellsize);
					for(float j=y ; j < y+(3*cellsize) ; j+=cellsize) {
						for(float i=newx ; i < newx+(3*cellsize) ; i+=cellsize) {
							if(rect.getX()==i && rect.getY()==j) {
								checkrects.add(rect);
								checkenemy();
								rect.setColor(color);

							}
						}
					}
				}
								
				if(isdown) {
					float newy=y-(6*cellsize);
					for(float j=newy ; j > newy-(3*cellsize) ; j-=cellsize) {
						for(float i=x ; i < x+(3*cellsize) ; i+=cellsize ) {
							if(rect.getX()==i && rect.getY()==j) {
								checkrects.add(rect);
								checkenemy();
								rect.setColor(color);

							}
						}
					}
				}	
				if(isup) {
					float newy=y+(6*cellsize);
					for(float j = newy ; j < newy+(3*cellsize) ; j+=cellsize) {
						for(float i = x ; i < x+(3*cellsize); i+=cellsize) {
							if(rect.getX()==i && rect.getY()==j) {
								checkrects.add(rect);
								checkenemy();
								rect.setColor(color);

							}
						}
					}
				}
			}
		}
		
		//check the enemy if it's exist kill it
		public void checkenemy(){
			for(rectangle rect : checkrects) {
				for(Enemy e : enemy) {
					if(rect.getColor()==e.getColor() || rect.getColor()==e.tailcolor) {
						ecolor = rect.getColor();
						isstop = true;
					}
				}
			}
		}
		
		//getter for number of shots
		public int getNumberofshots() {
			return numberofshots;
		}
			
	}
	
	class WeaponB extends Weapon{
		
		//add possible neghbors to list
		@Override
		public void shooting(float x, float y, Color color, boolean isup, boolean isleft, boolean isright,boolean isdown) {
			if(isup) {
				for(rectangle rect : rects) {
					if(rect.getX()==x && rect.getY()>y ) {
					checkrects1.add(rect);
					}
				}
				checkenemy();
			}
			
			else if(isdown) {
				for(rectangle rect : rects) {
					if(rect.getX()==x && rect.getY()<y) {
						checkrects1.add(rect);
					}
				}
				checkenemy();
			}
		
			else if(isleft) {
				for(rectangle rect : rects) {
					if(rect.getX()<x && rect.getY()==y) {
						checkrects1.add(rect);
					}
				}
				checkenemy();
			}
		
			else if(isright) {
				for(rectangle rect : rects) {
					if(rect.getX()>x && rect.getY()==y ) {
						checkrects1.add(rect);
					}
				}
				checkenemy();
			}
		}
		
		//check the other enemies's coordinates to kill the possible enemy
		@Override
		public void checkenemy() {
			for(rectangle r : checkrects1) {
				for(Enemy e : enemy) {
					if(r.getColor()==e.color || r.getColor()==e.tailcolor) {
						ecolor = r.getColor();
						isstop = true;
					}
				}
			}
			checkrects1.clear();
						
		}
		
	}

		 

		
		
	
	


