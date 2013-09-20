package com.kayzej1126.friendstowerdefense;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

public class TestScreen extends Screen{
	World world;
	
	public TestScreen(Game game) {
		super(game);
		world = new World();
		world.path.PathSet(game);
	}

	@Override
	public void update(float deltaTime) {
		world.update(deltaTime);
	}

	@Override
	public void present(float deltaTime) {
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
