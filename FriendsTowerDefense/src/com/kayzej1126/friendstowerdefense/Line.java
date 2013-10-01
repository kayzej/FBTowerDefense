package com.kayzej1126.friendstowerdefense;

public class Line {
	int x1, x2, y1, y2, length;
	public Line(int x1, int x2, int y1, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.length = (int) Math.sqrt((x2 - x1)^2 + (y2 - y1)^2);
	}
}
