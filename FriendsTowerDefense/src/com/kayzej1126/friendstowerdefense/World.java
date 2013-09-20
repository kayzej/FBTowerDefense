package com.kayzej1126.friendstowerdefense;

public class World {
    static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INITIAL = 0.1f;
    static final float TICK_DECREMENT = 0.05f;

    public Creep creep;
    public Path path;
    float tickTime = 0;
    float tick = TICK_INITIAL;

    public World() {
        creep = new Creep(0, Assets.path.getHeight()/2);
        path = new Path();
    }

    public void update(float deltaTime) {       
        tickTime += deltaTime;
        if (creep.x < 1910){
        	for (int i=0;i<creep.speed;i++){
        		creep.move(path);
        	}
        }
    }
}
