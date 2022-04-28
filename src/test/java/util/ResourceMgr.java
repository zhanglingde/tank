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
    public static BufferedImage wall,water;
    // 爆炸图片数组
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            goodTankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            goodTankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            goodTankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));

            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

            // goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            // goodTankR = ImageUtil.rotateImage(goodTankU,90);
            // goodTankD = ImageUtil.rotateImage(goodTankU,180);
            // goodTankL = ImageUtil.rotateImage(goodTankU,270);

            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);
            badTankL = ImageUtil.rotateImage(badTankU, 270);

            bulletU2 = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletR2 = ImageUtil.rotateImage(bulletU2, 90);
            bulletD2 = ImageUtil.rotateImage(bulletU2, 180);
            bulletL2 = ImageUtil.rotateImage(bulletU2, 270);

            wall = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/wall.gif"));
            water = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/wall.gif"));

            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}