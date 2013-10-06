package com.kayzej1126.friendstowerdefense;

import android.graphics.Point;

public class Creep {
	public int x, y, k, speed, health;
	boolean vert;
	
	public Creep(int x, int y){
		this.x = x;
		this.y = y;
		this.speed = 1;
		this.k = 0;
		this.health = 100;
	}
	
	public void move(Point point){
		if (!(x>1910 || y>1070)){
			if (x < point.x){
				x+=1;
				vert = false;
			}
			else if (y < point.y){
				y+=1;
				vert = true;
			}
			else if (y > point.y){
				y-=1;
				vert = true;
			}
			else if (x > point.x){
				x -=1;
				vert = false;
			}
			else{
				k+=1;
			}
		}
	}
}
