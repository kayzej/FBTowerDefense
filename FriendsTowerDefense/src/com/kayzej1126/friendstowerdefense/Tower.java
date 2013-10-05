package com.kayzej1126.friendstowerdefense;

import android.graphics.Point;

public class Tower {
	int x, y, range;
	Point drawHere;
	Line bulletLine;
	boolean drawLine;
	public Tower(int x, int y, Point drawAt){
		this.x = x;
		this.y = y;
		this.drawHere = drawAt;
		this.range = 300;
		this.bulletLine = new Line(0,0,0,0);
		this.drawLine = false;
	}
	public void shoot(Creep creep, Line line){
		bulletLine = line;
	}
}
