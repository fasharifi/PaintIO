package me.fa.GameLogic;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

import me.fa.game.PaintioGame;
import me.fa.game.Screen.MainMenuScreen;
import me.fa.game.Screen.rectangle;

public class HumanPlayer implements Screen {
	
	WeaponA wa1 = new WeaponA();
	PaintioGame game ;
	Enemy enemy1;
	
	public static rectangle rect ;
	private GameLogic gl = new GameLogic();
	private float elapsedSeconds;

	@SuppressWarnings("static-access")
	private int cellsize = game.CELLSIZE;
	@SuppressWarnings("static-access")
	private int screenheight = game.HEIGHT;
	@SuppressWarnings("static-access")
	private int screenwidth = game.WIDTH;
	private int xstart = 360;
	private int ystart = 360;
	public static int x = 360 ; 
	public static int y = 360 ;

	@SuppressWarnings({ "unused" })
	private ArrayList <Color> colors = Enemy.ecolor;
	private Backgroundhandling backgroundhandling ;
	@SuppressWarnings("static-access")
	private ArrayList <rectangle> rects = backgroundhandling.rects;
	@SuppressWarnings("static-access")
	private Group group =backgroundhandling.group ;
	private Color playertailcolor = Color.LIGHT_GRAY;
	private static ArrayList <rectangle> ownrects = new ArrayList <rectangle>();
	private static ArrayList <Enemy> enemy = new ArrayList <Enemy>();
	public static Color playercolor = MainMenuScreen.playercolor;
	@SuppressWarnings("static-access")
	public ArrayList <rectangle> tail =gl.playertail;
	@SuppressWarnings("static-access")
	public Stage stage = backgroundhandling.stage;  
	
	public static boolean isup = false ;
	public static boolean isleft = false ;
	public static boolean isright = false ;
	public static boolean isdown = false ;
	public static boolean isstop = true ;
	int numberofshot = 0 ;


	
	@SuppressWarnings("static-access")
	public HumanPlayer (PaintioGame game ,  ArrayList <Enemy> enemy) {
		this.game = game ;
		backgroundhandling = new Backgroundhandling(game.batch);
		gl.startrectangle(xstart, ystart, playercolor);
		this.enemy =  enemy;
	}
	

	
	@Override
	public void show() {
		  rect = new rectangle(360 ,360 , PaintioGame.CELLSIZE ,Color.BLACK);
			gl.startrectangle(xstart, ystart, playercolor);

	}
	
	//Handling keys and player movement
	@SuppressWarnings("static-access")
	@Override	
	public void render(float delta) {
		ScreenUtils.clear(0,0.5f,0.8f, 1);
		
		WeaponA wa = new WeaponA();
		WeaponB wb = new WeaponB();
		float deltaTime = Gdx.graphics.getDeltaTime();
	    elapsedSeconds += deltaTime;

		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			isup=true;
			isleft=false ;
			isright = false;
			isdown = false;
			group.moveBy(0, -PaintioGame.CELLSIZE);
			y+=PaintioGame.CELLSIZE;
			addrectangleup();
			killenemy(x,y);
			gl.check(x, y , playercolor,playertailcolor ,tail );
			gl.addtail(x, y , playertailcolor);
		}
		
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			isdown=true;
			isleft=false ;
			isright = false;
			isup = false ;
			group.moveBy(0, +PaintioGame.CELLSIZE);
			y-=PaintioGame.CELLSIZE;
			addrectangledown();
			killenemy(x,y);
			gl.check(x,y , playercolor,playertailcolor,tail);
			gl.addtail(x, y , playertailcolor);
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			isleft=true;
			isdown=false;
			isup = false;
			isright=false ;
			group.moveBy(+PaintioGame.CELLSIZE, 0);
			x-=PaintioGame.CELLSIZE;
			addrectangleleft();
			killenemy(x,y);
			gl.check(x, y , playercolor ,playertailcolor,tail);
			gl.addtail(x, y , playertailcolor);
		}
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			isright=true;
			isdown=false;
			isup = false;
			isleft=false;
			group.moveBy(-PaintioGame.CELLSIZE, 0);
			x+=PaintioGame.CELLSIZE;
			addrectangleright();
			killenemy(x,y);
			gl.check(x, y , playercolor,playertailcolor,tail);
			gl.addtail(x, y , playertailcolor);
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
			if(numberofshot < wa.getNumberofshots()) {
				wa.shooting(x, y, playercolor,isup , isleft , isright , isdown);
				for(Enemy e : enemy) {
					if(e.getColor()==wa.ecolor || e.getTailcolor()==wa.ecolor) {
						enemy1 = e;
						if(wa.isstop) {
							enemy1.bekilled();
							int newx = enemy1.setreandomx(); 
							int newy = enemy1.setreandomy();
							enemy1.killedbygun(newx , newy);
							wa.isstop=false;
			
						}
					}
				}
			}
			numberofshot++;
		}
	

		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			if(elapsedSeconds<=3) {
			}
			else {
				wb.shooting(x, y, playercolor,isup , isleft , isright , isdown);
			}
			elapsedSeconds=0;
			for(Enemy e : enemy) {
				if(e.getColor()==wb.ecolor || e.getTailcolor()==wb.ecolor) {
					enemy1 = e;
					if(wb.isstop) {
						e.bekilled();
						int newx = enemy1.setreandomx(); 
						int newy = enemy1.setreandomy();
						enemy1.killedbygun(newx , newy);
						wb.isstop=false;
					}
				}
			}
		}
			
		

		saveownrect(playercolor);

		game.batch.begin();
		rect.draw(game.batch, delta);
		game.batch.end();
		stage.draw();
		saveownrect(playercolor);
		game.batch.begin();
		rect.draw(game.batch, delta);
		game.batch.end();
		
		
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
	
	} 
	
	
	
	
	int height = 0;
   	int width = 0;
   	int height1 = screenheight;
    int width1 = screenwidth;
    int h = 30;
    int h1 = -30;
    int w = 30 ;
    int w1 = -30;
    
    
   	//extend background
    public synchronized void addrectangledown() {
    	float minx = gl.findminx();
    	float maxx = gl.findmaxx();
    	
   		for(float i = minx ; i <= maxx  ; i+=cellsize ) {
   			rectangle rect = new rectangle(i,height,cellsize,Color.PINK);
   			rects.add(rect);
	   }
   		height-=cellsize;
   		w1-=30;
   		group.clear();
   		for(rectangle rect : rects) {
   			group.addActor(rect);
   		}
   		setGroup(group);
   		stage.addActor(getGroup());

   }
  
   
  	public synchronized void addrectangleup() {
   		float minx = gl.findminx();
    	float maxx = gl.findmaxx();
   		for(float i = minx ; i <= maxx ; i+=cellsize ) {
   			rectangle rect = new rectangle(i,height1,cellsize,Color.PINK);
   			rects.add(rect);
   		}
   		
   		height1+=cellsize;
   		w=+30;
   		group.clear();
   		for(rectangle rect : rects) {
   			group.addActor(rect);
   		}
   		setGroup(group);
   		stage.addActor(getGroup());

   } 

  	
  	public synchronized void addrectangleleft() {
  		float miny = gl.findminy();
    	float maxy = gl.findmaxy();
	   for(float j =miny ; j <= maxy  ; j+=cellsize ) {
		   rectangle rect = new rectangle(width,j,cellsize,Color.PINK);
		   rects.add(rect);
	   }
	   width-=cellsize;
	   h1-=30;
	   group.clear();
	   for(rectangle rect : rects) {
		   group.addActor(rect);
	   }
	   setGroup(group);
	   stage.addActor(getGroup());

   }
  	

  	public synchronized void addrectangleright() {
  		float miny = gl.findminy();
    	float maxy = gl.findmaxy();
	   for(float j = miny ; j <= maxy ; j+=cellsize ) {
		   rectangle rect = new rectangle(width1,j,cellsize,Color.PINK);
		   rects.add(rect);
	   }
	   width1+=cellsize;
	   h+=30;
	   group.clear();
	   for(rectangle rect : rects) {
		   group.addActor(rect);
	   }

	   setGroup(group);
	   stage.addActor(getGroup());

   }
   

  	//enemy be killed by player
  	@SuppressWarnings("static-access")
	public void killenemy(int x , int y ) {
  		
  		Color tailcolor = Color.PINK ;
  		Color ecolor = Color.PINK ;
  		Enemy en = null;
  		boolean enemyisfound = false;
  		
  		for(rectangle r : rects) {
  			if(r.getX()==x && r.getY()==y) {
  				for(Color c : Enemy.ecolor) {
  					if(r.getColor()==c) {
  						tailcolor = c ;
  						enemyisfound = true;
  						for(Enemy e : enemy) {
  							if(e.tailcolor==tailcolor) {
  								ecolor = e.color;
  								en=e;
  							}
  						}
  					}
  				}
  			}
  		}
  		if(enemyisfound) {
  			en.bekilled();
  			en.regenerate(ecolor);
  			enemyisfound=false;
  		}
  	}
  	
  	
  	//setter and getter
  	public void saveownrect(Color color) {
  		for(rectangle rect : rects) {
  			if(rect.getColor() == color) {
  				ownrects.add(rect);
  			}
  		}
  	}
	
  	public static ArrayList<rectangle> getOwnrects() {
		return ownrects;
	}

	public static void setOwnrects(ArrayList<rectangle> ownrects) {
		HumanPlayer.ownrects = ownrects;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}


}