package com.kayzej1126.friendstowerdefense;

import android.graphics.Point;

public class Creep {
	public int x, y, k, speed;
	
	public Creep(int x, int y){
		this.x = x;
		this.y = y;
		this.speed = 1;
		this.k = 0;
	}
	
	public void move(Point point){
		if (!(x>1910 || y>1070)){
			if (x < point.x){
				x+=1;
			}
			else if (y < point.y){
				y+=1;
			}
			else if (y > point.y){
				y-=1;
			}
			else{
				k+=1;
			}
		}
	}
}
