package com.kayzej1126.friendstowerdefense;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.jpg", PixmapFormat.RGB565);
        Assets.creep = g.newPixmap("zergling.png", PixmapFormat.ARGB4444);
        Assets.path = g.newPixmap("path.jpg", PixmapFormat.ARGB4444);
        Assets.tower = g.newPixmap("tower.png", PixmapFormat.ARGB4444);
        game.setScreen(new TestScreen(game));
    }
    
    public void present(float deltaTime) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}
