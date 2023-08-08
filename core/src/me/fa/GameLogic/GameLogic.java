package me.fa.GameLogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.Color;

import me.fa.game.PaintioGame;
import me.fa.game.Screen.MainGameScreen;
import me.fa.game.Screen.rectangle;

public class GameLogic {
	
	private int cellsize = PaintioGame.CELLSIZE;

	
	private Backgroundhandling backgroundhandling ;
	@SuppressWarnings("static-access")
	private ArrayList <rectangle> rects = backgroundhandling.rects;
	public static ArrayList <rectangle> playertail = new ArrayList <rectangle>();
	public ArrayList <rectangle> ownrect = new ArrayList<rectangle>();


	//add tail to player
	public void addtail(int x , int y , Color color) {
		
		for(rectangle rect : rects) {
			if(rect.getX()==x && rect.getY()==y) {
				rect.setColor(color);
				playertail.add(rect);
			}
		}		
	}
	
	
	//make starting place of player
	public void startrectangle(int x , int y , Color color) {
		
		for ( rectangle rect : rects) {
			if(rect.getX() == x + cellsize && rect.getY()== y ) { rect.setColor(color);
			// ownrect.add(rect);}
			}
 			if(rect.getX()== x+cellsize && rect.getY()==y+cellsize) { rect.setColor(color); 
 			//ownrect.add(rect);}
 			}
 			if(rect.getX()== x  && rect.getY()== y) {rect.setColor(color);
 			//ownrect.add(rect);}
 			}
 			if(rect.getX()== x && rect.getY()==y+cellsize) {rect.setColor(color);
 			// ownrect.add(rect);}
 			}
		}
	}
	  	
	
		
	//find minimum and maximum of the game board
	public float findmaxy(){
		rectangle max = rects.get(0); 
		float maxy = max.getY();
		for (int i = 1 ; i < rects.size() ; i++) {
			rectangle rect = rects.get(i);
			if (rect.getY()>maxy) {
				maxy = rect.getY();
			}
	      }
		return maxy;
}	

	public float findminx(){
		rectangle max = rects.get(0); 
		float minx = max.getX();
		for (int i = 1 ; i < rects.size() ; i++) {
			rectangle rect = rects.get(i);
			if (rect.getX()< minx) {
				minx = rect.getX();
				
			}
	      }
		return minx;
}	

	public float findminy(){
		rectangle max = rects.get(0);
		float miny = max.getY();
		for (int i = 1 ; i < rects.size() ; i++) {
			rectangle rect = rects.get(i);

			if (rect.getY()< miny) {
				miny = rect.getY();
			}
	      }
		return miny;
}	
	   	
	public float findmaxx(){
		rectangle max = rects.get(0); 
		float maxx = max.getX();
		for (int i = 1 ; i < rects.size() ; i++) {
			rectangle rect = rects.get(i);
			if (rect.getX()>maxx) {
				maxx = rect.getX();

			}
	      }
	return maxx;
}
	
	
	
	//color the area
	//check if it goes back to it's place 
	public void check(float x , float y , Color color,Color tcolor , ArrayList <rectangle> tail) {
   		for(rectangle rect : rects) {
   			if(rect.getX()==x && rect.getY()==y && rect.getColor()==color){
   				System.out.print("hi");
   				findstartingpoint(tcolor,color,tail);
   				//break;
   			}
   		}
   	}
	
	// after finding starting point start color the area by checking neighbors
	private void FloodFill(rectangle startrect, Color newcolor , Color tcolor , int minx , int maxx , int miny , int maxy,ArrayList <rectangle> tail ) {
		//check the color of point 
		if(startrect.getColor()==newcolor) return;
			
		
		// color the boundaries
		for(rectangle rect : rects) {
			if(rect.getColor()==tcolor) {
				rect.setColor(newcolor);
			}
		}
		
	    Queue<rectangle> queue=new LinkedList<>();
	    queue.add(startrect);
	    
	    while(!queue.isEmpty()){
	    	rectangle tempRect = queue.poll();
	        int x = (int) tempRect.getX();
            int y = (int) tempRect.getY();
            	
            	//check if is inside the shape and check color of it
            if(x < minx || x > maxx || y < miny || y > maxy || tempRect.getColor()==newcolor  ) {
	        	continue;
		    }
	            	
	            //add neighbors of the point
	        else {
	        	tempRect.setColor(newcolor);
				for(rectangle r1 : rects) {
				   	if(r1.getX()==x-30 && r1.getY()==y) {
				   		queue.add(r1);
				   	}
				   	if(r1.getX()==x+30 && r1.getY()==y) {
				   		queue.add(r1);
				   	}
				   	if(r1.getX()==x && r1.getY()==y-30) {
				   		queue.add(r1);
				   	}
				   	if(r1.getX()==x && r1.getY()==y+30) {
				   		queue.add(r1);
				   	}				   				
				}
			    tail.clear();

	        }
	    }
	}
	
	//find starting point then start color the area 
	@SuppressWarnings("static-access")
	private void findstartingpoint(Color tcolor , Color color , ArrayList <rectangle> tail) {
		if(color!=HumanPlayer.playercolor) {
			tail.clear();
			for(Enemy e : MainGameScreen.enemy) {
				if(e.color==color) {
					tail=e.enemytail;
				}
			}
		}
		
		float maxX = 0 ;
  		float maxY = 0 ;
  		float minX =findmaxx();
  		float minY = findmaxy();
  		
  		boolean hasright = false ;
  		boolean hasleft = false ;
  		boolean hasup = false ;
  		boolean hasdown = false ;
   		rectangle startingrect = null ;

  		
		ArrayList<rectangle> gamearea = new ArrayList <rectangle>();
		ArrayList<rectangle> checkrectright = new ArrayList <rectangle>();
		ArrayList<rectangle> checkrectleft = new ArrayList <rectangle>();
		ArrayList<rectangle> checkrectup = new ArrayList <rectangle>();
		ArrayList<rectangle> checkrectdown = new ArrayList <rectangle>();


		
		
  		//make area between minimum and maximum
		for(rectangle rect : tail) {
   			if(rect.getX()> maxX) maxX = rect.getX();  		
   			if(rect.getX()< minX) minX = rect.getX();  		
   			if(rect.getY()> maxY) maxY = rect.getY();  		
   			if(rect.getY()< minY) minY = rect.getY();  		
   			}
   		for(rectangle r : rects) {
   			if(r.getX() < maxX && r.getX() > minX && r.getY() > minY && r.getY() < maxY) {
   				gamearea.add(r);
   			}
   		}
   		
   		
   		//check adjacency to find boundary 
   		//add adjacency to ArrayList 
   		for(rectangle r : gamearea) {
   			if(r.getColor()==Color.PINK && r.getColor()!=color) {
   				int x = (int) r.getX() ; 
   				int y = (int) r.getY();
   				for(rectangle r1 : rects) {
   					if(r1.getX()==x && r1.getY()>y) {
   						checkrectup.add(r1);
   					}
   					else if(r1.getX()==x && r1.getY()<y) {
   						checkrectdown.add(r1);

   					}
   					else if(r1.getX()>x && r1.getY()==y) {
   						checkrectright.add(r1);

   					}
   					else if(r1.getX()<x && r1.getY()==y) {
   						checkrectleft.add(r1);
   					}
   				}
   				
   				
   				
   				//now finding starting point 
   		   		for(rectangle r1 : checkrectup) {
   		   			if(r1.getColor()==tcolor || (r1.getX()==HumanPlayer.rect.getX() || r1.getY()==HumanPlayer.rect.getY())) {
   		   				hasup=true;
   		   			}
   		   		}
   		   		for(rectangle r1 : checkrectdown){
		   			if(r1.getColor()==tcolor || (r1.getX()==HumanPlayer.rect.getX()  || r1.getY()==HumanPlayer.rect.getY())) {
		   				hasdown=true;
		   			}
		   		}
   		   		for(rectangle r1 : checkrectright) {
   		   			if(r1.getColor()==tcolor || (r1.getX()==HumanPlayer.rect.getX()  || r1.getY()==HumanPlayer.rect.getY())) {
   		   				hasright=true;
   		   			}
   		   		}
   		   		for(rectangle r1 : checkrectleft) {
   		   			if(r1.getColor()==tcolor || (r1.getX()==HumanPlayer.rect.getX()  || r1.getY()==HumanPlayer.rect.getY())) {
   		   				hasleft=true;
   		   			}
   		   		}
   		   		
   		   		
   		   		// check the point 
   		   		if(hasright && hasleft && hasdown && hasup) {
   		   			startingrect = r ;
   		   			break;
   		   		}
   			}
   		}
   		
   		
   		
   		//pass the point to FloodFill
   		if(startingrect!=null) {
			FloodFill(startingrect , color , tcolor , (int)minX , (int)maxX , (int)minY, (int)maxY,tail);
   		}
   		
   		
   		//if it can't find point just color the boundaries
   		else {
   			for(rectangle rect : rects) {
   				if(rect.getColor()==tcolor) {
   					rect.setColor(color);
   				}
   			}
   		}
	}
	
	
}
