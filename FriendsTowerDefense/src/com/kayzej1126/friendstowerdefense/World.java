package com.kayzej1126.friendstowerdefense;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

import com.badlogic.androidgames.framework.Game;

public class World {
    static final float TICK_INITIAL = 0.1f;
    static final float TICK_DECREMENT = 0.05f;

    List<Creep> creeps;
    public Path path;
    List<Tower> towers; 
    float tickTime = 0;
    float tick = TICK_INITIAL;
    public Game game;
    int openSpots[][] = new int[16][8];
    int open, creepSpeed;

    public World(Game game1) {
    	System.out.println("before creep add");
    	creeps = new ArrayList<Creep>();
    	creeps.add(new Creep(0, Assets.path.getHeight()/2));
        path = new Path();
        towers = new ArrayList<Tower>();
        creepSpeed = 10;
        SpotsInit();
    }

    public void update(float deltaTime) {       
     
        for (int i=0; i< creeps.size();i++){
        	if (creeps.get(i).x < 1920 - creepSpeed){
        		for (int j=0;j<creepSpeed;j++){
        			creeps.get(i).move(path);
        		}
        	}
        }
        
        tickTime += deltaTime;
        if (tickTime > 1){
        	creeps.add(new Creep(0, Assets.path.getHeight()/2));
        	tickTime = 0;
        }
    }
    
    public void SpotsInit(){
		for (int i=0; i<16; i++){
			for (int j=0; j<8;j++){
				openSpots[i][j] = 0;
			}
		}
		
		openSpots[0][1] = 1;
		openSpots[1][1] = 1;
		openSpots[2][1] = 1;
		for (int j=2; j<16; j++){
			openSpots[j][2] = 1;
		}
	}
    
    public Point checkSpot(Point touched){
    	int x = touched.x;
    	int y = touched.y;
    	int checkX = 0;
    	int checkY = 0;
    	Point drawAt = new Point();
    	
    	for (int i=1;i<=16;i++){
    		if (x > (i-1)*120 && x < i*120){
    			drawAt.x = (i-1)*120;
    			checkX = i-1;
    		}
    	}
    	
    	for (int j=0; j<=8; j++){
    		if (y > (j-1)*135 && y < j*135){
    			drawAt.y = (j-1)*135;
    			checkY = j -1;
    		}
		}
    	
    	if (openSpots[checkX][checkY] == 0){
    		openSpots[checkX][checkY] = 1;
    		return drawAt;
    	}
    	else{
    		drawAt.x = 9999;
    		drawAt.y = 9999;
    		return drawAt;
    	}
    }
}
