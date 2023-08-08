package me.fa.GameLogic;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import me.fa.game.PaintioGame;
import me.fa.game.Screen.MainMenuScreen;
import me.fa.game.Screen.rectangle;




public class Backgroundhandling {
	
	private int cellsize = PaintioGame.CELLSIZE;
	private int screenwidth = PaintioGame.WIDTH ;
	private int screenheight = PaintioGame.HEIGHT;
	private Color playercolor = MainMenuScreen.playercolor;
	
	//make stage and group for handling all the rectangle together
	public static Stage stage ;  
	public static Group group = new Group() ;
	private FitViewport viewport;
	
	//save all the rectangle in this list 
	public static ArrayList <rectangle> rects = new ArrayList <rectangle>();
	
	
	
	
	
	public Backgroundhandling (SpriteBatch sb ) {
		
 		viewport = new FitViewport(PaintioGame.WIDTH,PaintioGame.HEIGHT,new OrthographicCamera());
 		stage = new Stage (viewport,sb); 
 		
		
 		// make rectangle and add them to the list
 		for(int x =0 ;  x <= screenwidth ; x+=cellsize) {
 			for(int y = 0 ; y <=screenheight ; y+=cellsize) {
 				rectangle rect = new rectangle(x,y,cellsize,Color.PINK);
 				rects.add(rect);
 			}
 		}
 		
 		//make the player rectangle
 		for(rectangle r : rects) {
 			if(r.getX()==360&&r.getY()==360) {
 				r.setColor(playercolor);
 			}
 		}


 		//add all rectangle to the group and stage
 		for(rectangle rect : rects) {
				group.addActor(rect);
 		}
		stage.addActor(group);
	}
	
	
	
	//getter and setter
	public Group getGroup() {
		return group;
	}
	@SuppressWarnings("static-access")
	public void setGroup(Group group) {
		this.group = group;
	}
	
	
	

}
