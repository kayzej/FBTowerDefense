package com.kayzej1126.friendstowerdefense;

import java.util.List;

import android.graphics.Point;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class TestScreen extends Screen{
	World world;
	Point curTouched = new Point();
	Point drawAt = new Point();
	
	public TestScreen(Game game) {
		super(game);
		world = new World(game);
		world.path.PathSet(game);
		world.openSpots = world.path.myPath;
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
	        drawAt = world.checkSpot(curTouched);
	        if(!(drawAt.x == 9999 || drawAt.y == 9999)){
	        	world.towers.add(new Tower(event.x, event.y, drawAt));
	        }
     	}
	    
	    world.update(deltaTime);
	}

	@Override
	public void present(float deltaTime) {
		drawBackground();
		drawWorld(world);
	}

	public void drawWorld(World world){
		Graphics g = game.getGraphics();
		Creep creep = world.creep;
		Pixmap creepPixmap= Assets.creep;
		int x = creep.x;
		int y = creep.y;
		
		if (creep.x < 1920){
			creep.speed = 10;
			g.drawPixmap(creepPixmap, x, y);
		}
		else{
			creep.remove();
		}

		if(world.towers.size() > 0){
			for (int i=0; i< world.towers.size();i++){
				g.drawPixmap(Assets.tower, world.towers.get(i).drawHere.x, world.towers.get(i).drawHere.y);
			}
		}
	}
	
	public void drawBackground(){
		Graphics g = game.getGraphics();
		int height = Assets.path.getHeight()/2;
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.path, 0, height);
		g.drawPixmap(Assets.path, Assets.path.getWidth(), height);
		g.drawPixmap(Assets.path, Assets.path.getWidth(), height*3);
		g.drawPixmap(Assets.path, 2*Assets.path.getWidth(), height*3);
		g.drawPixmap(Assets.path, 3*Assets.path.getWidth(), height*3);
		g.drawPixmap(Assets.path, 4*Assets.path.getWidth(), height*3);
		g.drawPixmap(Assets.path, 5*Assets.path.getWidth(), height*3);
		g.drawPixmap(Assets.path, 6*Assets.path.getWidth(), height*3);
		g.drawPixmap(Assets.path, 7*Assets.path.getWidth(), height*3);
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
