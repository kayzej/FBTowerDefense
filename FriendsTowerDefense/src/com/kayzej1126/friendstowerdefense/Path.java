package com.kayzej1126.friendstowerdefense;

import com.badlogic.androidgames.framework.Game;

public class Path {
	public int [][] pixelPath = new int[1920][1080];
	public int[][] noTowers = new int[16][8];
	
	public Path(){
		PathInit(this);
	}
	
	public void PathInit(Path path){
		for (int i=0; i<1920; i++){
			for (int j=0; j<1080;j++){
				this.pixelPath[i][j] = 0;
			}
		}
	}
	
	public void PathSet(Game g){
		g.getGraphics();
		int width = Assets.path.getWidth();
		int height = Assets.path.getHeight();
		
		for (int i=0; i<width;i++){
			pixelPath[i][height/2] = 1;
		}
		
		for (int j=height/2;j<height + height/2;j++){
			pixelPath[width][j] = 1;
		}
		
		for (int i=width; i< 1920; i++){
			pixelPath[i][height + height/2] = 1;
		}
	}
}
