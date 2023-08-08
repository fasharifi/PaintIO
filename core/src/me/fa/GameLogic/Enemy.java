package me.fa.GameLogic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;

import me.fa.game.PaintioGame;
import me.fa.game.Screen.MainGameScreen;
import me.fa.game.Screen.rectangle;
import me.fa.game.Screen.setting;

public class Enemy extends Game {
	
	PaintioGame game;
	Random random = new Random();
	private float elapsedSeconds;
	private float seconds ;
	private int x ;
	private int y ;
	public Color color ;
	public Color tailcolor ;
	
	
	public static ArrayList <Color> ecolor = new ArrayList <Color>();
	public static ArrayList <rectangle> enemyownrect = new ArrayList <rectangle>();

	
	private Backgroundhandling backgroundhandling ;
	private GameLogic gl = new GameLogic();
	@SuppressWarnings("static-access")
	private ArrayList <rectangle> rects = backgroundhandling.rects;
	private ArrayList <rectangle> playertail = GameLogic.playertail ;
	@SuppressWarnings("static-access")
	public Stage stage = backgroundhandling.stage;  
	public static ArrayList <rectangle> enemytail = new ArrayList<rectangle>();
	public static boolean isdead = false;
	public static boolean isstop = true;

	
	rectangle rect = new rectangle(x,y,30,color);
	
	
	
	
	public Enemy(PaintioGame game , int x , int y ,Color color ) {
		this.game=game;
		this.x=x;
		this.y=y;
		this.color=color;
		backgroundhandling = new Backgroundhandling(game.batch);
		tailcolor = setrandomcolor(color);	
		if(setting.isonfast) {
			seconds = 0.2f;
		}
		else {
			seconds = 0.5f;
		}
	}
	
	public Enemy() {
		
	}
	

	
	
	@Override
	public void create() {

	
	}

	@Override		
	public  void render() {
		
		float deltaTime = Gdx.graphics.getDeltaTime();
	    elapsedSeconds += deltaTime;
	        
	    if(setting.isonhard) {
	    	if (elapsedSeconds >= seconds) {
	        	hardmood();
	            elapsedSeconds = 0;
	        }
	    }
	    else {
	        if(elapsedSeconds >= seconds) {
	        	easymood();
	        	elapsedSeconds=0;
	        }
	    }
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
	


	
	//enemy's logics

	//check if enemy is out of the game board
	public void outofboard(float x , float y , Color color , Color tailcolor) {
		if (x > gl.findmaxx() || x < gl.findminx() || y > gl.findmaxy() || y < gl.findminy()) {
			bekilled();
			regenerate(color);
			isdead = true ;
		}						
	}
	
	//set colors and it's tail
	public Color setrandomcolor(Color c) {
		
		Color color;
		
		if(c == Color.BLUE) color = Color.SKY;
		else if(c == Color.CLEAR) color = Color.CYAN;
		else if(c == Color.RED) color = Color.ORANGE;
		else if(c == Color.BROWN) color = Color.FIREBRICK;
		else if(c == Color.YELLOW) color = Color.CORAL;
		else if(c == Color.GOLDENROD) color = Color.GOLD;
		else if(c == Color.OLIVE) color = Color.LIME;
		else if(c == Color.FOREST) color = Color.GREEN;
		else color = Color.BLACK;
		
		ecolor.add(Color.SKY);
		ecolor.add(Color.CYAN);
		ecolor.add(Color.ORANGE);
		ecolor.add(Color.FIREBRICK);
		ecolor.add(Color.CORAL);
		ecolor.add(Color.GOLD);
		ecolor.add(Color.LIME);
		ecolor.add(Color.GREEN);

		return color ;
	}
	
	//for regenerating you should write a random for chose new place.
	public  void regenerate(Color color) {
		
		int randn = random.nextInt();
		if(randn % 3 == 0) {
			x=150+60;
		}
		if(randn % 3 == 1) {
			x=150-60;
		}
		if(randn % 3 == 2) {
			y=150+60;
		}
		else {
			y=150-60;
		}
		x =x - 90;
		y =y + 180;
		isdead = true ;
		startrectangle(x,y);

	}
	
	//game over check and call game over screen
	public void GameOver (int x , int y ) {
		for(rectangle rect : playertail) {			
			if (x==rect.getX() && y == rect.getY()) {
				game.setScreen(new GameOverScreen(game));			
			}
		}
	}
	
	//remove all the enemy's rectangles
	@SuppressWarnings("static-access")
	public Color bekilled () {
		for (rectangle rect : rects) {
			if(rect.getColor()==color || rect.getColor()==tailcolor) {
				rect.setColor(color.PINK);
				enemytail.clear();
				//enemyownrect.clear();
			}
		}
		return color ;
	}
	
	//enemy's starting place
	public void startrectangle(int x , int y ) {
		
		for ( rectangle rect : rects) {
			if(rect.getX() == x + 30 && rect.getY()== y ) { rect.setColor(color);
			// enemyownrect.add(rect);
			}
			
 			if(rect.getX()== x+30 && rect.getY()==y+30) { rect.setColor(color); 
 			//enemyownrect.add(rect);
 			}
 			
 			if(rect.getX()== x  && rect.getY()== y) {rect.setColor(color);
 			//enemyownrect.add(rect);
 			}
 			
 			if(rect.getX()== x && rect.getY()==y +30) {rect.setColor(color);
 			//enemyownrect.add(rect);
 			}
 			}
 			
		}
	
	//add tail to enemy
	public void addtail(int x , int y , Color color) {
		for(rectangle rect : rects) {
			if(rect.getX()==x && rect.getY()==y) {
				rect.setColor(color);
				enemytail.add(rect);
			}
		}		
	}
	
	//killed by gun 
	public void killedbygun(int newx , int newy ) {
		x= newx + 30 ;
		y= newy + 30 ;
		regenerate(color);
		//startrectangle(x, y);
	
	}

	//set random x 
	public int setreandomx() {
		int newx;
		newx = getX() + 30;
		return newx ;
		
	}
	
	//random y
	public int setreandomy() {
		int ra = random.nextInt(3);
		int newy = getY()+ra*30;
		return newy ;
	}

	//check if contact to other enemies
	public void checkanotherenemy(float x , float y) {
		 for(rectangle r : rects) {
			 if(r.getX()==x && r.getY()==y && r.getColor()!=Color.PINK && r.getColor()!=color && r.getColor()!=tailcolor ) {
				 //yes so remove and kill that enemy
				 removeenemy(r.getColor());
			 }
		 }
	 }

	//removing the enemy
	@SuppressWarnings("static-access")
	private void removeenemy(Color retcolor) {
		Color recolor =retcolor;

		for(Enemy e : MainGameScreen.enemy) {
			if(e.tailcolor==retcolor)	{
				recolor = e.color;
				for(rectangle r : e.enemytail) {
					if(r.getColor()==retcolor ) {
						r.setColor(Color.PINK);
					}
				}
				for(rectangle r : rects) {
					if(r.getColor() == recolor) {
						r.setColor(Color.PINK);
					}
					if(r.getColor()==retcolor) {
						r.setColor(Color.PINK);							
					}
				}
				e.regenerate(recolor);
			}
		}
	}

	
	
	
	//Easy and Hard level
	
	//Hard
	float xp;
	float yp ;
	rectangle erect;
	int nup = 0 ; 
	int ndown = 0 ;
	int nleft = 0 ;
	int nright = 0 ;
	boolean isx = false ;
	boolean isy = false ;

	//start
	public void hardmood() {
		checkplayerxy();
	}
	
	//Check x , y to start
	public void checkplayerxy() {
		if(playertail.isEmpty()) {
			xp=HumanPlayer.x;
			yp=HumanPlayer.y;
			compair(xp,yp);
			
		}
		else {
			findlasttail();
			compair(xp,yp);
		}
	}

	//Compare enemy x and y to player x and y
	private void compair(float xhplayer, float yhplayer) {
		
		int rand = random.nextInt();
		if(rand % 10 <=5) {
			isx=true;
			isy=false;
		}
		else {
			isy=true;
			isx=false;
		}
		
		if(isx) {
			if(xhplayer - x > 0) {
				x+=30;
				checkanotherenemy(x,y);
				colortherect(x,y);
				GameOver(x,y);
				nright++;
				checkplayertail(xhplayer,yhplayer);


			}
			else if(xhplayer - x < 0) {
				x-=30;
				checkanotherenemy(x,y);
				colortherect(x,y);
				GameOver(x,y);
				nleft++;
				checkplayertail(xhplayer,yhplayer);


			}
			else if(yhplayer - y > 0) {
				y+=30;
				checkanotherenemy(x,y);
				colortherect(x,y);
				GameOver(x,y);
				nup++;
				checkplayertail(xhplayer,yhplayer);


			}
			else if(yhplayer - y < 0) {
				y-=30;
				checkanotherenemy(x,y);
				colortherect(x,y);
				GameOver(x,y);
				ndown++;
				checkplayertail(xhplayer,yhplayer);


			}
		}
		else if(isy) {
			if(yhplayer - y >= 0) {
				y+=30;
				checkanotherenemy(x,y);
				colortherect(x,y);
				GameOver(x,y);
				nup++;
				checkplayertail(xhplayer,yhplayer);


			}
			else if(yhplayer - y <= 0) {
				y-=30;
				checkanotherenemy(x,y);
				colortherect(x,y);
				GameOver(x,y);
				ndown++;
				checkplayertail(xhplayer,yhplayer);

			}
			else if(xhplayer - x >= 0) {
				x+=30;
				checkanotherenemy(x,y);
				colortherect(x,y);
				GameOver(x,y);
				nright++;
				checkplayertail(xhplayer,yhplayer);


			}
			else if(xhplayer - x <= 0) {
				x-=30;
				checkanotherenemy(x,y);
				colortherect(x,y);
				GameOver(x,y);
				nleft++;
				checkplayertail(xhplayer,yhplayer);



			}
		}
	}

	//start attacking
	private void colortherect(float newx , float newy) {
		for(rectangle r : rects) {
			if(r.getX()==newx && r.getY()==newy) {
				r.setColor(tailcolor);
				enemytail.add(r);

			}
		}
	}

	//find last tail to attack
	private void findlasttail() {
		if(HumanPlayer.isdown) {
			xp = HumanPlayer.x;
			yp=HumanPlayer.y + 30;
		}
		else if(HumanPlayer.isup) {
			xp = HumanPlayer.x;
			yp=HumanPlayer.y - 30;
		}
		else if(HumanPlayer.isright) {
			xp = HumanPlayer.x-30;
			yp=HumanPlayer.y;
		}
		else if(HumanPlayer.isleft) {
			xp = HumanPlayer.x + 30;
			yp=HumanPlayer.y;
		}
		
	}

	//check if player get rectangle or not
	private void checkplayertail(float xh , float yh) {
		for(rectangle r : rects) {
			if(r.getX()==xh && r.getY()==yh && r.getX()!=360 && r.getY()!=360) {
				if(r.getColor()==HumanPlayer.playercolor) {
					//enemy starts turning back to add rectangle 
					getrect();
				}
			}
		}
	}

	//start collecting rectangle 
	private void getrect() {

		if(nup > 0) {
			y-=30;
			nup--;
			checkanotherenemy(x,y);
			gl.check(x, y, color,tailcolor, enemytail);
			colortherect(x,y);
			checkenemytail(x,y);
			checkanotherenemy(x,y);


		}
		else if (ndown > 0) {
			
			y+=30;
			ndown--;
			checkanotherenemy(x,y);
			gl.check(x, y, color,tailcolor, enemytail);
			colortherect(x,y);
			checkenemytail(x,y);
	
		}
		else if(nright > 0) {
			x-=30;
			nright--;
			checkanotherenemy(x,y);
			gl.check(x, y, color,tailcolor, enemytail);
			colortherect(x,y);
			checkenemytail(x,y);

		}
		else if ( nleft > 0) {
			x+=30;
			nleft--;
			checkanotherenemy(x,y);
			gl.check(x, y,color,tailcolor, enemytail);
			colortherect(x,y);
			checkenemytail(x,y);
	
		}
	}
	
	//check if enemy arrive to own home and attack again
	public void checkenemytail(float xb , float yb) {
		 for(rectangle r : rects ) {
			if(r.getX()==xb && r.getY()==yb && r.getColor()==color) {
				gl.check(x, y, color,tailcolor, enemytail);
				compair(xp,yp);
				enemytail.clear();					
			}
		}
	 }

	
	//easy
	boolean up = true;
	boolean down = true;
	boolean right = true;
	boolean left = true;
	//start
	@SuppressWarnings("static-access")
	private void easymood() {
		WeaponA wa = new WeaponA();
	
		int rand = random.nextInt();
		
		if(!wa.isstop && HumanPlayer.isstop && isstop) {
			
			if(rand%4==0) {
				if(right) {
					x += 30;
					rect.moveBy(x, y);
					checkanotherenemy(x,y);
					addtail(x, y, tailcolor);
					GameOver(x,y);
					outofboard(x,y,color,tailcolor);
					gl.check(x, y, color,tailcolor,enemytail);
					left=false;
					up=true;
					down=true;
					right=true;
				}
			}
		
			if(rand%4==1) {
				if(left) {
					x -= 30;
					rect.moveBy(x, y);
					checkanotherenemy(x,y);
					addtail(x, y, tailcolor);
					GameOver(x,y);
					outofboard(x,y,color,tailcolor);
					gl.check(x, y, color,tailcolor,enemytail);
					right=false;
					up=true;
					down=true;
					left=true;
				}							
			}
			
			if(rand%4==2) {
				if(up) {
					y += 30;
					rect.moveBy(x, y);
					checkanotherenemy(x,y);
					addtail(x, y, tailcolor);
					GameOver(x,y);
					outofboard(x,y,color,tailcolor);
					gl.check(x, y, color,tailcolor,enemytail);
					down=false;
					right=true;
					left=true;
					up=true;
				}			
			}
			
			if(rand%4==3) {
				if(down) {
					y -= 30;
					rect.moveBy(x, y);
					checkanotherenemy(x,y);
					addtail(x, y, tailcolor);
					GameOver(x,y);
					outofboard(x,y,color,tailcolor);
					gl.check(x, y, color,tailcolor,enemytail);
					up=false;
					right=true;
					left=true;
					down=true;
				}
				
			}
		}
	}
	
	
	        

	//setter and getter
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Color getTailcolor() {
		return tailcolor;
	}

	public void setTailcolor(Color tailcolor) {
		this.tailcolor = tailcolor;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}


