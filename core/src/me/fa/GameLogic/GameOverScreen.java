package me.fa.GameLogic;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import me.fa.game.PaintioGame;

public class GameOverScreen implements Screen  {
	
	PaintioGame game;
	
	
	private static final int GAMEOVER_HEIGHT = 500 ; 
	private static final int GAMEOVER_WIDTH = 500 ; 
	
	
	private SpriteBatch batch1 = new SpriteBatch();
	private Texture gameover;
	
	
	public GameOverScreen(PaintioGame game) {
		//load the image
		this.game = game;
		gameover = new Texture ("gameover.png");
		
		
		
	}

	@Override
	public void show() {		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0,0.5f,0.8f, 1);
		
		//draw game over picture
		game.batch.begin();
		batch1.begin();
		game.batch.draw(gameover,PaintioGame.WIDTH/2 - GAMEOVER_WIDTH/2,500,GAMEOVER_WIDTH,GAMEOVER_HEIGHT);
		game.batch.end();
		batch1.draw(gameover,PaintioGame.WIDTH/2 - GAMEOVER_WIDTH/2,200,GAMEOVER_WIDTH,GAMEOVER_HEIGHT);
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
	}

}
