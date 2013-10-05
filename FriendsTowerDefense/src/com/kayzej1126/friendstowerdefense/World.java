package com.kayzej1126.friendstowerdefense;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

import com.badlogic.androidgames.framework.Game;

public class World {
    static final float TICK_INITIAL = 0.1f;
    static final float TICK_DECREMENT = 0.05f;

    //List<Creep> creeps;
    Creep [] creeps;
    List<Tower> towers;
    List<Bullets> bullets;
    public Path path;
    float tickTime = 0;
    float tick = TICK_INITIAL;
    public Game game;
    int openSpots[][] = new int[16][8];
    int open, creepSpeed, money, creepNums, cur;
    boolean running, inRange;

    public World(Game game1) {
    	//creeps = new ArrayList<Creep>();
    	creepNums = 100;
        creeps = new Creep [creepNums];
    	towers = new ArrayList<Tower>();
        bullets = new ArrayList<Bullets>();
        path = new Path();
        creepSpeed = 10;
        SpotsInit();
        money = 100;
        cur = 0;
    }

    public void update(float deltaTime) {       
//        for (int i=0; i< creeps.size();i++){
//    		for (int j=0;j<creepSpeed;j++){
//    			if (creeps.get(i).k < path.points.size()){
//    				creeps.get(i).move(path.points.get(creeps.get(i).k));
//    			}
//    			else{
//    				creeps.remove(i);
//    			}
//        	}
//        }
    	
    	 for (int i=0; i< cur;i++){
     		for (int j=0;j<creepSpeed;j++){
     			if (creeps[i] != null){
	     			if (creeps[i].k < path.points.size()){
	     				creeps[i].move(path.points.get(creeps[i].k));
	     			}
	     			else{
	     				creeps[i] = null;
	     			}
     			}
         	}
         }
        
        tickTime += deltaTime;
        if (tickTime > 1 && cur < 100){
        	if (running){
        		//creeps.add(new Creep(path.points.get(0).x, path.points.get(0).y));
        		creeps[cur] = new Creep(path.points.get(0).x, path.points.get(0).y);
        		cur+=1;
        	}
        	tickTime = 0;
        }
        
        Line line;
//        if (creeps.size() > 0){
//	    	for (int i=0;i<towers.size();i++){
//	    		for (int j=0;j<creeps.size();j++){
//	    			line = new Line(towers.get(i).x, creeps.get(j).x, towers.get(i).y, creeps.get(j).y);
//	    			if (line.length < towers.get(i).range){
//	    				towers.get(i).shoot(creeps.get(j), line);
//	    				towers.get(i).drawLine = true;
//	    			}
//	    			else{
//	    				towers.get(i).drawLine = false;
//	    			}
//	    		}
//			}
//        }
        
        if (cur > 0){
	    	for (int i=0;i<towers.size();i++){
	    		int j=0;
	    		inRange = false;
	    		towers.get(i).drawLine = false;
	    		while (j<creeps.length && !inRange){
	    			if (creeps[j] != null){
		    			line = new Line(towers.get(i).x, creeps[j].x, towers.get(i).y, creeps[j].y);
		    			if (line.length < towers.get(i).range){
		    				inRange = true;
		    				towers.get(i).shoot(creeps[j], line);
		    				towers.get(i).drawLine = true;
		    			}
		    			j++;
	    			}
	    		}
			}
        }
    }
    
    public void SpotsInit(){
    	for (int j=0; j<8;j++){
    		for (int i=0; i<15; i++){	
				openSpots[i][j] = levels.level1[j][i];
			}
		}
    	
    	for (int i=0; i<16; i++){
    		openSpots[i][0] = 1;
    		openSpots[i][7] = 1;
    	}
    	
    	for (int i=0; i<8; i++){
    		openSpots[14][i] = 1;
    		openSpots[15][i] = 1;
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
