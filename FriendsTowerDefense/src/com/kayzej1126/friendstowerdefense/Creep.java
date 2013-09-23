package com.kayzej1126.friendstowerdefense;

public class Creep {
	public int x, y, speed;
	
	public Creep(int x, int y){
		this.x = x;
		this.y = y;
		this.speed = 1;
	}
	
	public void move(Path path){
		if (!(x>1910 || y>1070)){
			if (path.pixelPath[this.x+1][this.y] == 1){
				x += 1;
			}
			else if (path.pixelPath[this.x][this.y+1] == 1){
				y+=1;
			}
			else if (path.pixelPath[this.x + 1][this.y+1] == 1){
				x+=1;
				y+=1;
			}
			else {
				
			}
		}
	}
	
	public void remove(){
		this.x = 0;
		this.y = 0;
		this.speed = 0;
	}
}
