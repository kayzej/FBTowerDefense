package com.kayzej1126.friendstowerdefense;

import android.graphics.Point;

public class Bullet {
	int x, y, f, fLife, creepX, creepY;
	Creep creep;
	public Bullet(int x, int y){
		this.x = x;
		this.y = y;
		this.f = 1;
		this.fLife = 50;
	}
	
	public Point move(int creepXnew, int creepYnew){
		int ratio = f/fLife;
		f++;
		this.x = (int) x + (creepXnew - x) * ratio;
		this.y = (int) y + (creepYnew - y) * ratio;
		return (new Point(this.x, this.y));
	}

}
