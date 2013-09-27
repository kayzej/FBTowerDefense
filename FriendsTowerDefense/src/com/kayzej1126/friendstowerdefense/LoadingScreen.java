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
        Assets.path = g.newPixmap("path.png", PixmapFormat.ARGB4444);
        Assets.tower = g.newPixmap("tower_sized.png", PixmapFormat.ARGB4444);
        Assets.bullet = g.newPixmap("bullet.png", PixmapFormat.ARGB4444);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
        Assets.start_button = g.newPixmap("start_button.png", PixmapFormat.ARGB4444);
        Assets.play = g.newPixmap("play_button.PNG",PixmapFormat.ARGB4444);
        game.setScreen(new MainMenuScreen(game));
        //game.setScreen(new GameScreen(game));
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
