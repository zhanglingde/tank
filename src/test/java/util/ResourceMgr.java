package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * 资源管理：坦克图片，子弹图片
 */
public class ResourceMgr {
    public static BufferedImage tankL, tankU, tankR, tankD;
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
    public static BufferedImage badTankL, badTankU, badTankR, badTankD;
    public static BufferedImage bulletL2, bulletU2, bulletR2, bulletD2;
    public static BufferedImage wall,water,home,steel,quarterWall,grass;
    // 爆炸图片数组
    public static BufferedImage[] explodes = new BufferedImage[16];
    public static BufferedImage[] explodes2 = new BufferedImage[10];

    static {
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tank_yellow.png"));
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);
            goodTankL = ImageUtil.rotateImage(goodTankU, 270);

            // badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            // badTankR = ImageUtil.rotateImage(badTankU, 90);
            // badTankD = ImageUtil.rotateImage(badTankU, 180);
            // badTankL = ImageUtil.rotateImage(badTankU, 270);
            badTankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tank3R.png"));
            badTankU = ImageUtil.rotateImage(badTankR, 270);
            badTankD = ImageUtil.rotateImage(badTankR, 90);
            badTankL = ImageUtil.rotateImage(badTankR, 180);

            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));



            bulletU2 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletR2 = ImageUtil.rotateImage(bulletU2, 90);
            bulletD2 = ImageUtil.rotateImage(bulletU2, 180);
            bulletL2 = ImageUtil.rotateImage(bulletU2, 270);

            wall = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/wall.gif"));
            quarterWall = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/wall_quarter.gif"));

            water = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/water.gif"));
            home = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/home.gif"));
            steel = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/steel.gif"));
            grass = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/grass.gif"));

            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
            for (int i = 0; i < explodes2.length; i++) {
                explodes2[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/" + i + ".gif"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}