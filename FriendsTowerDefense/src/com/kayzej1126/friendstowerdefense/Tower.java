package com.kayzej1126.friendstowerdefense;

import android.graphics.Point;

public class Tower {
	int x, y;
	Point drawHere;
	public Tower(int x, int y, Point drawAt){
		this.x = x;
		this.y = y;
		this.drawHere = drawAt;
	}
}
