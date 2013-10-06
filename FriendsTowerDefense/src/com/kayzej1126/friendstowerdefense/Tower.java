package com.kayzej1126.friendstowerdefense;

import android.graphics.Point;

public class Tower {
	int x, y, range, power;
	Point drawHere;
	Line bulletLine;
	boolean hasBullet;
	boolean drawLine;
	Bullet bullet;
	public Tower(int x, int y, Point drawAt){
		this.x = x;
		this.y = y;
		this.power = 5;
		this.drawHere = drawAt;
		this.range = 250;
		this.bulletLine = new Line(0,0,0,0);
		this.drawLine = false;
		this.hasBullet = false;
	}
	public void shoot(Creep creep, Line line){
		bulletLine = line;
		if (!hasBullet){
			this.bullet = new Bullet(this.x, this.y);
			hasBullet = true;
		}
		else {
			for (int i=0; i<this.bullet.speed; i++){
				this.bullet.move(creep.x, creep.y);
			}
			if (this.bullet.hit){
				creep.health -= power;
				this.hasBullet = false;
				this.bullet = null;
			}
		}
	}
}
