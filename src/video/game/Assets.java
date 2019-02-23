package video.game;

import java.awt.image.BufferedImage;

/**
 *
 * @author MarcelA00821875
 * @author MarianoA00822247
 */
public class Assets {
    public static BufferedImage background;
    public static BufferedImage bar;
    public static BufferedImage ball;
    public static void init(){
      background = ImageLoader.loadImage("/images/background.png");
      bar = ImageLoader.loadImage("/images/bar.png");
      ball = ImageLoader.loadImage("/images/ball.png");
    }
}
