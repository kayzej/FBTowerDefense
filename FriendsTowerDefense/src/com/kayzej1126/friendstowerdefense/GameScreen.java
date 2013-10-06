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
	boolean readyToPlace = false;
	boolean healthBars = true;
	
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
	        
	        if (event.type == 1 && inBounds(event, 14*pathWidth, 2*pathHeight,towerWidth*2, towerHeight*2)){
	        	readyToPlace = !readyToPlace;
	        }
	        
	        if (readyToPlace){
	        	drawAt = world.checkSpot(curTouched);
	        	if(!(drawAt.x == 9999 || drawAt.y == 9999)){
	        		if (world.money >= 10){
	        			world.towers.add(new Tower(drawAt.x + Assets.tower.getWidth()/2, drawAt.y + Assets.tower.getHeight()/2, drawAt));
	        			world.money -= 10;
	        			readyToPlace = false;
	        		}
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
		for (int i=0; i<world.cur;i++){
			if (world.creeps[i]!= null){
				g.drawPixmap(creepPixmap, world.creeps[i].x, world.creeps[i].y);
				if (healthBars){
					g.drawRect(world.creeps[i].x, world.creeps[i].y - 10, world.creeps[i].health, 5, Color.GREEN);
					g.drawRect(world.creeps[i].x + world.creeps[i].health, world.creeps[i].y - 10, 100 - world.creeps[i].health, 5, Color.RED);
				}
			}
		}


		//if(world.towers.size() > 0){
			for (int i=0; i< world.towers.size();i++){
				g.drawPixmap(Assets.tower, world.towers.get(i).drawHere.x, world.towers.get(i).drawHere.y);
				if (world.towers.get(i).drawLine){
					g.drawLine(world.towers.get(i).bulletLine.x1, world.towers.get(i).bulletLine.y1, world.towers.get(i).bulletLine.x2, world.towers.get(i).bulletLine.y2, Color.YELLOW);
				}
				if (world.towers.get(i).hasBullet){
					g.drawPixmap(Assets.bullet, world.towers.get(i).bullet.x, world.towers.get(i).bullet.y);
				}
			}
		//}
		
//		for (int i=0; i<world.bullets.size();i++){
//			g.drawPixmap(Assets.bullet, world.bullets.get(i).x, world.bullets.get(i).y);
//		}
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
		int color;
		g.drawPixmap(Assets.mineral, 240, 0);
		if(readyToPlace){
			color = Color.GREEN;
		}
		else {
			color = Color.RED;
		}
		g.drawRect(3*pathWidth, 7*pathHeight, pathWidth/4, pathHeight/4, color);
	}
	
	public void drawButtons(){
		Graphics g = game.getGraphics();
		g.drawRect(14*pathWidth, 0, 1080, 240, Color.BLACK);
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
