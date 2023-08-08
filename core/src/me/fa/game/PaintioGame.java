package me.fa.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.fa.game.Screen.Splash;

public class PaintioGame extends Game {
	
	public static final int WIDTH = 750;
	public static final int HEIGHT = 750;
	public static final int CELLSIZE = 30;
	public SpriteBatch batch;
	
	// start the splash screen 
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new Splash (this));
	}
	
	//render of splash screen
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void resize(int width, int height) {		
		super.resize(width, height);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
