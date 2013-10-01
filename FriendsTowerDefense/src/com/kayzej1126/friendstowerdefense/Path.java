package com.kayzej1126.friendstowerdefense;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

public class Path {
	public int[][] noTowers = new int[16][8];
	public boolean found = false;
	public List<Point> points;
	
	public Path(){
		points = new ArrayList<Point>();
		PathInit(this);
	}
	
	public void PathInit(Path path){
		int y=0;
		int x=0;
		int lengthX = 120;
		int lengthY = 135;
		String direction = "right";
		
		while (!found){
			if (levels.level1[y][x] == 1){
				found = true;
			}
			if(!found){
				y++;
			}
		}
		while (x<14){
			points.add(new Point(x*lengthX,y*lengthY));
			try {
				if (levels.level1[y][x+1] == 1 && (direction != "left")){
					x+=1;
					direction = "right";
				}
				else if (levels.level1[y+1][x] == 1 && (direction != "down")){
					y+=1;
					direction = "up";
				}
				else if (levels.level1[y-1][x] == 1 && (direction != "up")){
					y-=1;
					direction = "down";
				}
				else if (levels.level1[y][x-1] ==1 && (direction != "right")){
					x-=1;
					direction = "left";
				}
			}
			catch (Exception e) {
				points.add(new Point(x*lengthX,y*lengthY));
				x = 14;
			}
		}
	}
}
