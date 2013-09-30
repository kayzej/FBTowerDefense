package com.kayzej1126.friendstowerdefense;

import java.util.List;

import android.graphics.Color;
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
	
	int levelX = 0;
	int levelY = 0;
	int towerHeight = Assets.tower.getHeight();
	int towerWidth = Assets.tower.getWidth();
	int pathHeight = Assets.path.getHeight();
	int pathWidth = Assets.path.getWidth();
	int start_buttonX = 20;
	int start_buttonY = pathHeight * 7 - 50;
	
	public GameScreen(Game game) {
		super(game);
		world = new World(game);
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
	        
	        if (inBounds(event, start_buttonX, start_buttonY, Assets.start_button.getWidth(), Assets.start_button.getHeight())){
	        	running = true;
	        	world.running = true;
	        }
	        
	        //if (inBounds(event, 15*pathWidth, 0,towerWidth, towerHeight))
	        
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
		drawButtons();
		drawBanner();
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
		g.drawPixmap(Assets.background, 0, 0);
		int xCC = world.path.points.get(world.path.points.size() - 1).x;
		int yCC = world.path.points.get(world.path.points.size() - 1).y;
		for (int y=0;y<8;y++){
			for (int x=0;x<16;x++){
				if (levels.level1[y][x] == 1){
					g.drawPixmap(Assets.path, x*pathWidth, y*pathHeight);
				}
			}
		}
		g.drawPixmap(Assets.command_center, xCC, yCC);
	}
	
	public void drawBanner(){
		Graphics g = game.getGraphics();
		//g.drawRect(0, 0, 13*pathWidth, 135, Color.BLACK);
		g.drawPixmap(Assets.mineral, 240, 0);
		//g.drawString("abc", 250, 0);
	}
	
	public void drawButtons(){
		Graphics g = game.getGraphics();
		g.drawRect(14*pathWidth, 0, 1080, 240, Color.BLACK);
		g.drawRect(0, 6*pathHeight, 1920, 270, Color.BLACK);
		g.drawPixmap(Assets.barracks, 14*pathWidth, 0);
		
		for (int i=2;i<8;i++){
			g.drawPixmap(Assets.tower, 14*towerWidth, i*towerHeight);
			g.drawPixmap(Assets.tower, 15*towerWidth, i*towerHeight);
		}
	}
	
	public void drawPre(){
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.start_button, start_buttonX, start_buttonY);
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
