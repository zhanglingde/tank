package tank.demo30.facade;

import util.ResourceMgr;
import tank.demo30.Audio;
import tank.demo30.mediator.GameObject;

import java.awt.*;

public class Explode2 extends GameObject {

    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;
    private boolean living = true;

    private int step = 0;

    public Explode2(int x, int y) {
        this.x = x;
        this.y = y;

        // new Thread(()-> {
        //     new Audio("audio/explode.wav").play();
        // }).start();
    }

    public void paint(Graphics g){

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);

        if(step >= ResourceMgr.explodes.length){
            GameModel.getInstance().remove(this);
        }
    }
}
