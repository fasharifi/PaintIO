package me.fa.game.Screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

import me.fa.game.PaintioGame;

public class MainMenuScreen implements Screen {
	
	//define size of the picture
	private static final int EXIT_BUTTON_WIDTH = 140 ; 
	private static final int EXIT_BUTTON_HEIGHT = 70 ; 
	private static final int PLAY_BUTTON_WIDTH = 250 ; 
	private static final int PLAY_BUTTON_HEIGHT = 100 ; 
	private static final int SETTING_HEIGHT = 60 ; 
	private static final int SETTING_WIDTH = 60 ; 

	public static Color playercolor ; 
	PaintioGame game;
	
	
	private SpriteBatch batch1 = new SpriteBatch();
	private Label heading ; 
	public rectangle rect;
	public rectangle rect1 ,rect2 , rect3;

	
	Texture exitButtonActive;
	Texture exitButtonInactive;
	Texture playButtonActive;
	Texture playButtonInactive;
	Texture settingbutton;
	Texture picofgame;
	
	
	//initialize the picture and other variable
	public MainMenuScreen (PaintioGame game) {
		this.game = game;
		playButtonActive = new Texture ("play_button_active.png");
		playButtonInactive = new Texture ("play_button_inactive.png");
		exitButtonActive = new Texture ("exit_button_active.png");
		exitButtonInactive = new Texture ("exit_button_inactive.png");
		settingbutton = new Texture ("setting.png");

		rect = new rectangle (280,430,60,Color.BLACK);
	    rect1 = new rectangle (430,430,60,Color.GRAY);
	    rect2 = new rectangle (430,430,60,Color.WHITE);
	    rect3 = new rectangle (280,430,60,Color.WHITE);
	    
	}

	
	@Override
	public void show() {

		//make headings and it's settings
		heading = new Label ("choose charector", new Label.LabelStyle(new BitmapFont(),Color.BLACK));
        heading.setPosition(300,600);
        heading.setAlignment(Align.center);
        heading.setFontScale(4f);

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0.5f,0.8f, 1);
		
		game.batch.begin();
		batch1.begin();
		
		//handling the mouse
		exitbutton();
		playbutton();
		
		//choosing character
		choosecharacter(delta);
		
		//setting button
		game.batch.draw(settingbutton,10, 680,SETTING_WIDTH,SETTING_HEIGHT) ;
		
		game.batch.end();
	    heading.draw(batch1, 1f);
		batch1.end();

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
		batch1.dispose();
		game.batch.dispose();
	}

	
	//handling picture and mouse events
	private void exitbutton() {
		int x = PaintioGame.WIDTH/2 - EXIT_BUTTON_WIDTH/2;
		int y = 50; 
		if ((Gdx.input.getX() < x + EXIT_BUTTON_WIDTH )&&( Gdx.input.getX() > x )&& (PaintioGame.HEIGHT - Gdx.input.getY() < y + EXIT_BUTTON_HEIGHT) && (PaintioGame.HEIGHT - Gdx.input.getY()>y)) {
			game.batch.draw(exitButtonActive,x,y,EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()){
				Gdx.app.exit();
			}
		}else {
			game.batch.draw(exitButtonInactive,x,y,EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
			}
	
	}
	
	private void playbutton() {
		
		int x = PaintioGame.WIDTH/2 - PLAY_BUTTON_WIDTH/2;
		int y = 200;
		if ((Gdx.input.getX() < x + PLAY_BUTTON_WIDTH )&&( Gdx.input.getX() > x )&& (PaintioGame.HEIGHT - Gdx.input.getY() < y + PLAY_BUTTON_HEIGHT) && (PaintioGame.HEIGHT - Gdx.input.getY()>y)) {
			game.batch.draw(playButtonActive,x,y,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
			if(Gdx.input.isTouched()){
				if(playercolor != null) {
					game.setScreen(new MainGameScreen(game));

				}
			}
			
		}else {
			game.batch.draw(playButtonInactive,x,y,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
		}
	}

	private void choosecharacter(float delta) {
		
		if(Gdx.input.getX() < 310 && Gdx.input.getX() > 250 && PaintioGame.HEIGHT - Gdx.input.getY()  > 400 && PaintioGame.HEIGHT - Gdx.input.getY() < 460) {
			if(Gdx.input.isTouched()){
				rect3.draw(batch1, delta);
				playercolor = Color.BLACK;
			}
			
		}else {
			rect.draw(batch1, delta);
		}
		if(Gdx.input.getX() < 460 && Gdx.input.getX() > 400 && PaintioGame.HEIGHT - Gdx.input.getY() > 400 && PaintioGame.HEIGHT - Gdx.input.getY()  < 460) {
			if(Gdx.input.isTouched()){
				rect2.draw(batch1, delta);
				playercolor = Color.GRAY;
			}
			
		}else {
			rect1.draw(batch1, delta);
		}
		if(Gdx.input.getX() < 70 && Gdx.input.getX() > 10 && PaintioGame.HEIGHT - Gdx.input.getY() > 650 && PaintioGame.HEIGHT - Gdx.input.getY()  < 710) {
			if(Gdx.input.isTouched()){
				game.setScreen(new setting(game));

			}
		}
	}

}
