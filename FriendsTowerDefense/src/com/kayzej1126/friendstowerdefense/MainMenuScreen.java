package com.kayzej1126.friendstowerdefense;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);               
    }   

    public void update(float deltaTime) {
        //Graphics g = game.getGraphics();
        int backWidth = Assets.background.getWidth();
        int backHeight = Assets.background.getHeight();
        //int logoWidth = Assets.logo.getWidth();
        int logoHeight = Assets.logo.getHeight();
        int playWidth = Assets.play.getWidth();
        int playHeight = Assets.play.getHeight();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, backWidth/2 - playWidth/2, backHeight/2 + logoHeight, playWidth, playHeight)) {
                	game.setScreen(new GameScreen(game));
                }
            }
        }
    }
    
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 && 
           event.y > y && event.y < y + height - 1) 
            return true;
        else
            return false;
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        
        int backWidth = Assets.background.getWidth();
        int backHeight = Assets.background.getHeight();
        int logoWidth = Assets.logo.getWidth();
        int logoHeight = Assets.logo.getHeight();
        int playWidth = Assets.play.getWidth();
       // int playHeight = Assets.play.getHeight();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, backWidth/2 - logoWidth/2, backHeight/2 - logoHeight);
        g.drawPixmap(Assets.play, backWidth/2 - playWidth/2, backHeight/2 + logoHeight);
        if(Settings.soundEnabled)
            g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
        else
            g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
    }

    public void pause() {        
        Settings.save(game.getFileIO());
    }

    public void resume() {

    }

    public void dispose() {

    }
}

