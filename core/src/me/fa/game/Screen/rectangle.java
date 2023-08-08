package me.fa.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import me.fa.game.PaintioGame;


public class rectangle extends Actor {
	
	private int cellsize = PaintioGame.CELLSIZE;

	
	private TextureRegion text;
	private Rectangle bounds;
	private Color color;
	private float x ;
	private float y ;


	
		
	//make rectangle and initialize it's properties	     
	public rectangle(float x, float y, int cellsize , Color color) {
					
		this.bounds = new Rectangle(x, y, cellsize , cellsize);
		this.color = color;
		this.x = x;
		this.y= y;
		this.cellsize = cellsize;
		this.text = new TextureRegion(new Texture(Gdx.files.internal("white.jpeg"))); // Load a white texture
			        
	}
			        
	

	//drawing rectangle method
	@Override
	public void draw(Batch batch, float parentAlpha) {
		
        // Set the color of the batch to this actor's color
		batch.setColor(color);

        // Draw a rectangle using the batch
        batch.draw(text, bounds.x, bounds.y, bounds.width, bounds.height);
        
	}



	@Override
	public void act(float delta) {

	}
	
	
	//getters and setters
	public Color getColor() {
		return color;
	}
	
	
	public int getCellsize() {
		return cellsize;
	}
	
	
	public float getX() {
		return x;
	}


	public float getY() {
		return y;
	}

	
	public void setColor(Color color) {
		this.color = color;
	}





	
}

