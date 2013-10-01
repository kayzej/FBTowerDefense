package com.kayzej1126.friendstowerdefense;

import android.graphics.Point;

public class Tower {
	int x, y, range;
	Point drawHere;
	Line bulletLine;
	public Tower(int x, int y, Point drawAt){
		this.x = x;
		this.y = y;
		this.drawHere = drawAt;
		this.range = 150;
		this.bulletLine = new Line(0,0,0,0);
	}
	public void shoot(Creep creep, Line line){
		bulletLine = line;
	}
}
