

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author zhangling  2021/12/7 22:03
 */
public class ImageTest {

    @Test
    public void test() {

        try {
            // BufferedImage image = ImageIO.read(new File("D:\\AAAshuju\\IntelliJIDEA\\Spring\\tank\\src\\images\\bulletD.gif"));
            // assertNotNull(image);
            // 读取图片资源
            BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            if (image2 == null) {
                System.out.println("null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
