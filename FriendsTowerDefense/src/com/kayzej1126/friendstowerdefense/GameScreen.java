package com.kayzej1126.friendstowerdefense;

import java.util.List;

import android.graphics.Point;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class GameScreen extends Screen{
	World world;
	Point curTouched = new Point();
	Point drawAt = new Point();
	boolean running = false;
	
	public GameScreen(Game game) {
		super(game);
		world = new World(game);
		world.path.PathSet(game);
	}

	@Override
	public void update(float deltaTime) {
    	List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
	    game.getInput().getKeyEvents();    
	    int len = touchEvents.size();
	   
	    for(int i = 0; i < len; i++) {
	        TouchEvent event = touchEvents.get(i);
	        curTouched.x = event.x;
	        curTouched.y = event.y;
	        
	        if (inBounds(event, 20, Assets.background.getHeight()/2, Assets.start_button.getWidth(), Assets.start_button.getHeight())){
	        	running = true;
	        	world.running = true;
	        }
	        
	        drawAt = world.checkSpot(curTouched);
	        if(!(drawAt.x == 9999 || drawAt.y == 9999)){
	        	if (world.money >= 10){
	        		world.towers.add(new Tower(event.x, event.y, drawAt));
	        		world.money -= 10;
	        	}
	        }
     	}  
	    world.update(deltaTime);
	}

	@Override
	public void present(float deltaTime) {
		drawBackground();
		drawWorld(world);
		if (!running){
			drawPre();
		}
	}

	public void drawWorld(World world){
		Graphics g = game.getGraphics();
		Pixmap creepPixmap= Assets.creep;
		for (int i=0; i<world.creeps.size();i++){
			if (world.creeps.get(i).x < 1920 - world.creepSpeed){
				g.drawPixmap(creepPixmap, world.creeps.get(i).x, world.creeps.get(i).y);
			}
		}

		if(world.towers.size() > 0){
			for (int i=0; i< world.towers.size();i++){
				g.drawPixmap(Assets.tower, world.towers.get(i).drawHere.x, world.towers.get(i).drawHere.y);
			}
		}
	}
	
	public void drawBackground(){
		Graphics g = game.getGraphics();
		int height = Assets.path.getHeight();
		int width = Assets.path.getWidth();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.path, 0, height);
		g.drawPixmap(Assets.path, width, height);
		g.drawPixmap(Assets.path, 2*width, height);
		for (int i=2; i<16; i++){
			g.drawPixmap(Assets.path, i*width, height*2);
		}
	}
	
	public void drawPre(){
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.start_button, 20, Assets.background.getHeight()/2);
	}
	
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 && 
           event.y > y && event.y < y + height - 1) 
            return true;
        else
            return false;
    }
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
