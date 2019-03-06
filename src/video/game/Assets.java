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
    public static BufferedImage block;
    public static BufferedImage policeSprite;
    public static BufferedImage policeCarCollision[];
    public static BufferedImage grenadeSprite;
    public static BufferedImage grenadeRotate[];

    public static void init() {
        background = ImageLoader.loadImage("/images/background.png");
        bar = ImageLoader.loadImage("/images/policeCar.png");
        ball = ImageLoader.loadImage("/images/ball.png");
        block = ImageLoader.loadImage("/images/meth.jpg");
        policeSprite = ImageLoader.loadImage("/images/policeCarSprite.png");
        SpriteSheet police = new SpriteSheet(policeSprite);
        policeCarCollision = new BufferedImage[8];
        grenadeSprite = ImageLoader.loadImage("/images/grenadeSprite.png");
        SpriteSheet grenade = new SpriteSheet(grenadeSprite);
        grenadeRotate = new BufferedImage[8];
        for(int i = 0; i < 8; i++){
            policeCarCollision[i] = police.crop(i * 352,0,352,134);
        }
        for(int i = 0; i < 8; i++){
            grenadeRotate[i] = grenade.crop(i * 246,0,246,285);
        }
    }
}

