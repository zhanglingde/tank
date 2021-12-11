package com.ling.facade;

import com.ling.facade.GameModel;
import com.ling.mediator.GameObject;
import com.ling.tank.Audio;
import com.ling.tank.TankFrame;
import com.ling.util.ResourceMgr;

import java.awt.*;

/**
 * 爆炸类
 */
public class Explode extends GameObject {

    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    // private int x, y;
    private boolean living = true;
    TankFrame tf = null;
    /**
     * 实现了Model:Tank,Bullet,Explode等和View：TankFrame的分离，
     */
    // private GameModel gm;

    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        GameModel.getInstant().add(this);
        new Thread(() -> {
            new Audio("audio/explode.wav").play();
        }).start();
    }

    @Override
    public void paint(Graphics g){

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);

        if(step >= ResourceMgr.explodes.length){
            GameModel.getInstant().remove(this);
            // step = 0;
        }
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}