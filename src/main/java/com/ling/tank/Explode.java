package com.ling.tank;

import com.ling.util.ResourceMgr;

import java.awt.*;

/**
 * 爆炸类
 */
public class Explode {

    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;
    private boolean living = true;
    TankFrame tf = null;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(() -> {
            new Audio("audio/explode.wav").play();
        }).start();
    }

    public void paint(Graphics g){

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);

        if(step >= ResourceMgr.explodes.length){
            tf.getExplodes().remove(this);
            // step = 0;
        }
    }
}