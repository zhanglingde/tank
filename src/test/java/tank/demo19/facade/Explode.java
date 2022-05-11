package tank.demo19.facade;

import com.ling.util.ResourceMgr;
import tank.demo19.Audio;
import tank.demo19.mediator.GameObject;

import java.awt.*;

public class Explode extends GameObject {

    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;
    private boolean living = true;
    // GameModel gm;

    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        // this.gm = gm;

        new Thread(()-> {
            new Audio("audio/explode.wav").play();
        }).start();
    }

    public void paint(Graphics g){

        g.drawImage(ResourceMgr.explodes[step++],x,y,null);

        if(step >= ResourceMgr.explodes.length){
            // gm.getExplodes().remove(this);
            // gm.remove(this);
            GameModel.getInstance().remove(this);
            // step = 0;
        }
    }
}
