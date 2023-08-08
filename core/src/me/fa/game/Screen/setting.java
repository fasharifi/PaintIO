package me.fa.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import me.fa.game.PaintioGame;

public class setting implements Screen{
	PaintioGame game;
	
	public static boolean isonhard= false;
	public static boolean isoneasy= false;
	public static boolean isonslow= false;
	public static boolean isonfast= false;
	public static boolean is2eon= false;
	public static boolean is3eon= false;
	public static boolean is4eon= false;


	private static final int EASY_BUTTON_WIDTH = 300 ; 
	private static final int EASY_BUTTON_HEIGHT = 200 ; 
	private static final int HARD_BUTTON_WIDTH = 300 ; 
	private static final int HARD_BUTTON_HEIGHT = 200 ; 
	private static final int SPEED_BUTTON_HEIGHT = 300 ; 
	private static final int SPEED_BUTTON_WIDTH = 200 ; 
	private static final int NENEMY_BUTTON_WIDTH = 120 ; 
	private static final int NENEMY_BUTTON_HEIGHT = 120 ; 
	private static final int BACK_BUTTON_HEIGHT = 300 ; 
	private static final int BACK_BUTTON_WIDTH = 200 ; 


	
	
	Texture easybuttonon;
	Texture easybuttonoff;
	Texture hardbuttonon;
	Texture hardbuttonoff;
	Texture slowbuttonoff;
	Texture slowbuttonon;
	Texture fastbuttonon;
	Texture fastbuttonoff;
	Texture towebuttonoff;
	Texture towebuttonon;
	Texture threebuttonoff;
	Texture threebuttonon;
	Texture fourebuttonoff;
	Texture fourebuttonon;
	Texture back;



	
	private Label chooseenemy ; 
	private Label chooselevel ; 
	private Label choosespeed ; 

	//load pictures and labels
	public setting(PaintioGame game) {
		this.game = game;
		easybuttonon = new Texture("easyon.png");
		easybuttonoff = new Texture ("easyoff.png");
		hardbuttonon = new Texture("hardon.png");
		hardbuttonoff = new Texture("hardoff.png");
		slowbuttonoff = new Texture("slowoff.png");
		slowbuttonon = new Texture("slowon.png");
		fastbuttonon = new Texture("faston.png");
		fastbuttonoff = new Texture("fastoff.png");
		towebuttonoff = new Texture("2eoff.png");
		towebuttonon = new Texture("2eon.png");
		threebuttonoff = new Texture("3eoff.png");
		threebuttonon = new Texture("3eon.png");
		fourebuttonoff = new Texture("4eoff.png");
		fourebuttonon = new Texture("4eon.png");
		back = new Texture("back.png");
	}

	
	//label's setting
	@Override
	public void show() {
		chooseenemy = new Label ("choose number of enemies", new Label.LabelStyle(new BitmapFont(),Color.BLACK));
		chooselevel = new Label ("choose mode of level", new Label.LabelStyle(new BitmapFont(),Color.BLACK));
		choosespeed = new Label ("choose speed of game", new Label.LabelStyle(new BitmapFont(),Color.BLACK));
		chooselevel.setPosition(280,650);
		chooseenemy.setPosition(270,230);
		choosespeed.setPosition(270,430);
		chooselevel.setAlignment(Align.center);
		chooseenemy.setAlignment(Align.center);
		choosespeed.setAlignment(Align.center);
		choosespeed.setFontScale(3f);
		chooseenemy.setFontScale(3f);
		chooselevel.setFontScale(3f);

		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0,0.5f,0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		
		
		//show buttons and handle mouse click
		game.batch.begin();
		showlevel();
		showspeed();
		shownenemy();
		bachbutton();
		
		
		//show labels
        chooseenemy.draw(game.batch, 1f);
        chooselevel.draw(game.batch, 1f);
        choosespeed.draw(game.batch, 1f);
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
	
	
	
	
	//buttons handling with mouse
	public void showlevel() {
		int x = 100;
		int y = 530; 
		if ((Gdx.input.getX() < x + EASY_BUTTON_WIDTH )&&( Gdx.input.getX() > x )&& (PaintioGame.HEIGHT - Gdx.input.getY() < y + EASY_BUTTON_HEIGHT) && (PaintioGame.HEIGHT - Gdx.input.getY()>y)) {
			game.batch.draw(easybuttonoff,x,y,EASY_BUTTON_WIDTH,EASY_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()){
				game.batch.draw(easybuttonoff,x,y,EASY_BUTTON_WIDTH,EASY_BUTTON_HEIGHT);
				isoneasy=true;
				isonhard=false;
				
			}
			
		}if(isoneasy) {
			game.batch.draw(easybuttonoff,x,y,EASY_BUTTON_WIDTH,EASY_BUTTON_HEIGHT);
		}
		else {
			game.batch.draw(easybuttonon,x,y,EASY_BUTTON_WIDTH,EASY_BUTTON_HEIGHT);
		}

		//isoneasy=false;
	
		x = 330;
		y = 530;
		if ((Gdx.input.getX() < x + HARD_BUTTON_WIDTH )&&( Gdx.input.getX() > x )&& (PaintioGame.HEIGHT - Gdx.input.getY() < y + HARD_BUTTON_HEIGHT) && (PaintioGame.HEIGHT - Gdx.input.getY()>y)) {
			game.batch.draw(hardbuttonon,x,y,HARD_BUTTON_WIDTH,HARD_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()){
				game.batch.draw(hardbuttonoff,x,y,HARD_BUTTON_WIDTH,HARD_BUTTON_HEIGHT);
				isonhard = true;
				isoneasy = false;
			}
		}if( isonhard ) {
				game.batch.draw(hardbuttonoff,x,y,HARD_BUTTON_WIDTH,HARD_BUTTON_HEIGHT);
		}
			
		else {
			game.batch.draw(hardbuttonon,x,y,HARD_BUTTON_WIDTH,HARD_BUTTON_HEIGHT);

	}
}

	public void showspeed() {
		int x = 150;
		int y = 270; 
		if ((Gdx.input.getX() < x + SPEED_BUTTON_WIDTH )&&( Gdx.input.getX() > x )&& (PaintioGame.HEIGHT - Gdx.input.getY() < y + SPEED_BUTTON_HEIGHT) && (PaintioGame.HEIGHT - Gdx.input.getY()>y)) {
			game.batch.draw(slowbuttonon,x,y,SPEED_BUTTON_WIDTH,SPEED_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()){
				game.batch.draw(slowbuttonoff,x,y,SPEED_BUTTON_WIDTH,SPEED_BUTTON_HEIGHT);
				isonslow=true;
				isonfast=false;
			//	isoneasy=false;
				
			}
			
		}if(isonslow) {
			game.batch.draw(slowbuttonoff,x,y,SPEED_BUTTON_WIDTH,SPEED_BUTTON_HEIGHT);
		}
		else {
			game.batch.draw(slowbuttonon,x,y,SPEED_BUTTON_WIDTH,SPEED_BUTTON_HEIGHT);
		}

		//isoneasy=false;
	
		x = 370;
		y = 270;
		if ((Gdx.input.getX() < x + SPEED_BUTTON_WIDTH )&&( Gdx.input.getX() > x )&& (PaintioGame.HEIGHT - Gdx.input.getY() < y + SPEED_BUTTON_HEIGHT) && (PaintioGame.HEIGHT - Gdx.input.getY()>y)) {
			game.batch.draw(fastbuttonon,x,y,SPEED_BUTTON_WIDTH,SPEED_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()){
				game.batch.draw(fastbuttonoff,x,y,SPEED_BUTTON_WIDTH,SPEED_BUTTON_HEIGHT);
				isonfast = true;
				isonslow = false;
			}
		}if( isonfast) {
				game.batch.draw(fastbuttonoff,x,y,SPEED_BUTTON_WIDTH,SPEED_BUTTON_HEIGHT);
		}
			
		else {
			game.batch.draw(fastbuttonon,x,y,SPEED_BUTTON_WIDTH,SPEED_BUTTON_HEIGHT);

		}
	}
	
	public void shownenemy() {

		int x = 150;
		int y = 90; 
		if ((Gdx.input.getX() < x + NENEMY_BUTTON_WIDTH )&&( Gdx.input.getX() > x )&& (PaintioGame.HEIGHT - Gdx.input.getY() < y + NENEMY_BUTTON_HEIGHT) && (PaintioGame.HEIGHT - Gdx.input.getY()>y)) {
			game.batch.draw(towebuttonon,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()){
				game.batch.draw(towebuttonoff,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);
				is2eon=true;
				is3eon=false;
				is4eon=false;
			}
			
		}if(is2eon) {
			game.batch.draw(towebuttonoff,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);
		}
		else {
			game.batch.draw(towebuttonon,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);
		}

		//isoneasy=false;
	
		x = 320;
		y = 90;
		if ((Gdx.input.getX() < x + NENEMY_BUTTON_WIDTH )&&( Gdx.input.getX() > x )&& (PaintioGame.HEIGHT - Gdx.input.getY() < y + NENEMY_BUTTON_HEIGHT) && (PaintioGame.HEIGHT - Gdx.input.getY()>y)) {
			game.batch.draw(threebuttonon,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()){
				game.batch.draw(threebuttonoff,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);
				is3eon = true;
				is2eon = false;
				is4eon=false;

			}
		}if(is3eon) {
				game.batch.draw(threebuttonoff,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);
		}
			
		else {
			game.batch.draw(threebuttonon,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);

		}
		x = 500;
		y = 90;
		if ((Gdx.input.getX() < x + NENEMY_BUTTON_WIDTH )&&( Gdx.input.getX() > x )&& (PaintioGame.HEIGHT - Gdx.input.getY() < y + NENEMY_BUTTON_HEIGHT) && (PaintioGame.HEIGHT - Gdx.input.getY()>y)) {
			game.batch.draw(fourebuttonon,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()){
				game.batch.draw(fourebuttonoff,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);
				is4eon = true;
				is3eon = false;
				is2eon = false;

			}
		}if(is4eon) {
				game.batch.draw(fourebuttonoff,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);
		}
			
		else {
			game.batch.draw(fourebuttonon,x,y,NENEMY_BUTTON_WIDTH,NENEMY_BUTTON_HEIGHT);

		}
	
	}

	public void bachbutton() {
		int x = -50;
		int y = -120; 
		if ((Gdx.input.getX() < x + BACK_BUTTON_WIDTH )&&( Gdx.input.getX() > x )&& (PaintioGame.HEIGHT - Gdx.input.getY() < y + BACK_BUTTON_HEIGHT) && (PaintioGame.HEIGHT - Gdx.input.getY()>y)) {
			game.batch.draw(back,x,y,BACK_BUTTON_WIDTH,BACK_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()){
				game.batch.draw(back,x,y,BACK_BUTTON_WIDTH,BACK_BUTTON_HEIGHT);
				game.setScreen(new MainMenuScreen(game));
			}
		}else {
			game.batch.draw(back,x,y,BACK_BUTTON_WIDTH,BACK_BUTTON_HEIGHT);
		}


	}

}
