package com.kayzej1126.friendstowerdefense;

public class Bullet {
	int x, y, dist, length, speed, creepX, creepY;
	Line l;
	Creep creep;
	boolean hit;
	public Bullet(int x, int y){
		this.x = x;
		this.y = y;
		this.hit = false;
		this.speed = 15;
	}
	
	public void move(int creepX, int creepY){
		if (creepX > this.x){
			this.x +=1;
		}
		else if (creepX < this.x){
			this.x -=1;
		}
		
		if (creepY > this.y){
			this.y+=1;
		}
		else if(creepY < this.y){
			this.y -=1;
		}
		if (this.x == creepX && this.y == creepY){
			hit = true;
		}
	}

}
