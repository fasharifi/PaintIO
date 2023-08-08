package me.fa.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.fa.game.PaintioGame;

public class Splash  implements Screen{
	
	private Sprite splash ; 
	private SpriteBatch batch;
	private PaintioGame game;
	
	
	private static final int TEXT_BUTTON_WIDTH = 400 ; 
	private static final int TEXT_BUTTON_HEIGHT = 50 ; 
	
	Texture text;

	
	public Splash(PaintioGame game) {
		this.game=game;
	}

	@Override
	public void show() {
		//make a text and picture
		batch = new SpriteBatch();
		Texture splashtexture = new Texture("download.jpeg");
		splash = new Sprite(splashtexture);
		splash.setSize(400,400);
		splash.setCenter(390,450);
		text = new Texture("splashtext.png");
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0.5f,0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//starting game condition
		if(Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			game.setScreen(new MainMenuScreen (game));
		}
		
		//show picture and text
		int x = PaintioGame.WIDTH/2 - TEXT_BUTTON_WIDTH/2;
		batch.begin();
		splash.draw(batch);
		batch.draw(text,x,90,TEXT_BUTTON_WIDTH,TEXT_BUTTON_HEIGHT);
		batch.end();
		
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
		batch.dispose();
		splash.getTexture().dispose();
	}

}
