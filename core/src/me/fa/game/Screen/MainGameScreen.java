package me.fa.game.Screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import me.fa.GameLogic.Backgroundhandling;
import me.fa.GameLogic.Enemy;
import me.fa.GameLogic.HumanPlayer;
import me.fa.GameLogic.enemycolor.enemy1;
import me.fa.GameLogic.enemycolor.enemy2;
import me.fa.GameLogic.enemycolor.enemy3;
import me.fa.GameLogic.enemycolor.enemy4;
import me.fa.game.PaintioGame;

public class MainGameScreen implements Screen  {
	
	Random random = new Random();
	PaintioGame game ;
	int x = 360 ; 
	int y = 360 ;


	private Backgroundhandling backgroundhandling ;
	private HumanPlayer hp ;
	@SuppressWarnings("unused")
	private rectangle rect ;
	private enemy1 e1 = new enemy1();
	private enemy2 e2 = new enemy2();
	private enemy3 e3 = new enemy3();
	private enemy4 e4 = new enemy4();

	
	public static ArrayList<RenderThread> thread = new ArrayList<RenderThread>();
	public static HashMap<Enemy, RenderThread> the = new HashMap<>();
	public static ArrayList<Enemy> enemy = new ArrayList<Enemy>();
	@SuppressWarnings("static-access")
	public ArrayList<rectangle> rects = backgroundhandling.rects;
	public ArrayList<Color> colors = Enemy.ecolor;


	private boolean is2enemy = setting.is2eon;
	private boolean is3enemy = setting.is3eon;
	private boolean is4enemy = setting.is4eon;
	RenderThread renderThread ;


	
	public MainGameScreen (PaintioGame game) {
		this.game = game ;
		backgroundhandling = new Backgroundhandling(game.batch);
		//start making enemies and there's threads
		makeenemies();
	}
	
	

	@Override
	public void show() {
	    rect = new rectangle (360 ,360 , PaintioGame.CELLSIZE ,Color.BLACK);
		hp  = new HumanPlayer(game,enemy);
		//thread start
		for(RenderThread r : thread) {
			r.start();
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0.5f,0.8f, 1);
		
		//start player
		hp.show();
		hp.render(delta);
	
		//start rendering in thread and making enemies
		for(RenderThread r : thread) {
			  r.render();
			}
		//draw background
		backgroundhandling.stage.draw();
		
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
	public void hide() {			
	}

	@Override
	public void dispose() {
		for(RenderThread r : thread)
		r.stopRendering();

	}
	
	
	//make enemies and its thread
	@SuppressWarnings("static-access")
	public void makeenemies() {
		int x = 0 ;
		int y = 0;
		Color c ;
		if(is2enemy) {
			for(int i = 0; i <= 1 ; i++) {
				c = checkcolor(i);
				x = setrandx(x);
				y = setrandy(y);
				Enemy enemy = new Enemy (game,x,y,c);
				this.enemy.add(enemy);
				renderThread = new RenderThread(enemy );
				thread.add(renderThread);
				the.put(enemy, renderThread);
			}
		}
		else if(is3enemy){			
			for(int i = 0 ; i <= 2 ; i++) {
				c = checkcolor(i);
				x = setrandx(x);
				y = setrandy(y);
				Enemy enemy = new Enemy (game,x,y,c);
				this.enemy.add(enemy);
				renderThread = new RenderThread(enemy );
				thread.add(renderThread);
				the.put(enemy, renderThread);
			}
		}
		else if(is4enemy) {
			for(int i = 0 ; i <= 3 ; i++) {
				c = checkcolor(i);
				x =setrandx(x);
				y =setrandy(y);
				Enemy enemy = new Enemy (game,x,y,c);
				this.enemy.add(enemy);
				renderThread = new RenderThread(enemy );
				thread.add(renderThread);
				the.put(enemy, renderThread);
			}
		}
		else {
			c = e1.setrandomcolor1();
			Enemy enemy = new Enemy (game,90,90,c);
			this.enemy.add(enemy);
			 renderThread = new RenderThread(enemy );
			 thread.add(renderThread);
			 the.put(enemy, renderThread);

		}
	}
	
	
	// set x and y of enemy
	int randx = random.nextInt();
	int rand = random.nextInt();
	boolean right = true;
	boolean down = true;
	boolean left = true;
	boolean up = true;
	int n = 0 ;
	
	public int setrandx (int x ) {
		if(right) {
			if(rand%3==0) {
				x=360+180;
				rand++;
			}
			else if (rand%3==1) {
				x=360+210;
				rand++;
			}
			else if (rand % 3 ==2) {
				x=360+150;
				rand++;
			}
			else {
				x=360+240;
				rand++;
			}
			randx++;
			if(n==0) {
				down = true;
			}
			if(n==1) {
				down = false;
				right = false;
			}
			n++;
		}
		
		else  {
			if(rand%3==0) {
				x=360-150;
				rand++;
			}
			else if (rand%3==1) {
				x=360-180;
				rand++;
			}
			else if (rand % 3 ==2) {
				x=360-240;
				rand++;
			}
			else {
				x=360-210;
				rand++;
			}
			randx++;
			if(n==3) {
				down = true;
			}

			n++;
		}
		return x ;
	}
	int randy = random.nextInt();
	public int setrandy (int y ) {
		if(!down) {
			if(rand%2==0) {
				y=360+180;
				rand++;
			}
			else if (rand%3==1) {
				y=360+210;
				rand++;
			}
			else if (rand % 3 ==2) {
				y=360+240;
				rand++;

			}
			else {
				y=360+150;		
				rand++;

			}
		}
		else  {
			if(rand%3==0) {
				y=360-150;
				rand++;

			}
			else if (rand%3==1) {
				y=360-180;
				rand++;

			}
			else if (rand % 3 ==2) {
				y=360-240;
				rand++;

			}
			else {
				y=360-210;
				rand++;

			}			
		}
	
		return y ;
	}

	
	//set enemy's color
	public Color checkcolor(int i) {
		Color c ;
			if(i==0) {
				c = e1.setrandomcolor1();
				 return c ;
			}
			 if(i==1) {
				c = e2.setrandomcolor1();
				 return c ;
			}
			 if(i==2) {
				 c=e3.setrandomcolor1();
				 return c ;
			}
			 else {
				 c = e4.setrandomcolor1();
				 return c ;
			 }
	
	}

	
	// thread class and make enemy and it's render
	public static class RenderThread extends Thread {
		
		Enemy enemy ; 
		
		 public  RenderThread (Enemy enemy ) {
			 this.enemy = enemy;
			
		}

        private boolean running = true;

        @Override
        public void run() {
        	//set enemy starting point
         	enemy.startrectangle(enemy.getX(), enemy.getY());
            while (running) {
    
                Gdx.app.postRunnable(() -> {
                                        Gdx.graphics.requestRendering();
                });

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void render() {
        	//run the enemies 's render
        	enemy.render();

            Gdx.app.postRunnable(() -> {
                Gdx.graphics.requestRendering();
            });
        }

        public void stopRendering() {
            running = false;
        }
    }
}
