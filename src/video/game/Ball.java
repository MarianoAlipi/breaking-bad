package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author MarcelA00821875
 * @author MarianoA00822247
 */
public class Ball extends Item{

    /*!Important: Now we have to use 
        public enum Direcction
        to save the direction and stuff
    */
    private int xSpeed, ySpeed;
    private boolean collision;
    private Game game;
    public Ball(int x, int y, int width, int height, Game game) {
      //send x and y to the Item constructor
      super(x, y);
      this.width = width;
      this.height = height;
      this.game = game;
      this.xSpeed = 2;
      this.ySpeed = 3;
      this.hitbox = new Rectangle(x, y, width, height);
    }
    
    public int getWidth()  {return width;}
    public int getHeight() {return height;}
    public int getXSpeed() {return xSpeed;}
    public int getYSpeed() {return ySpeed;}
    public boolean getCollision() {return collision;}
    
    public void setWidth(int width)   {this.width = width;}
    public void setHeight(int height) {this.height = height;}
    public void setXSpeed(int xSpeed) {this.xSpeed = xSpeed;}
    public void setYSpeed(int ySpeed) {this.ySpeed = ySpeed;}
    public void setCollision(boolean collision) {this.collision = collision;}
    
    //tick is used to check bar
    @Override
    public void tick() {
        
        // Move ball
        setX( getX() + getXSpeed() );
        setY( getY() + getYSpeed() );
        
        // Reset position and bounce if out of screen
        if (getX() + getWidth() > game.getWidth()) {
            setX(game.getWidth() - getWidth());
            setXSpeed(-1 * getXSpeed());
        } else if (getX() < 0) {
            setX(0);
            setXSpeed(-1 * getXSpeed());
        }
        if (getY() + getHeight() > game.getHeight()) {
            setY(game.getHeight() - getHeight());
            setYSpeed(-1 * getYSpeed());
        } else if (getY() < 0) {
            setY(0);
            setYSpeed(-1 * getYSpeed());
        }
        
        // Relocate hitbox
        hitbox.setLocation(getX(), getY());
        
        // Check for collision with bar
        if (getHitbox().intersects(game.getBar().getHitbox()))
            setYSpeed(-1 * getYSpeed());
    }
    
    //displays aka renders
    @Override
    public void render(Graphics g) {
      g.drawImage(Assets.ball, getX(), getY(), getWidth(), getHeight(), null);
    }

    /**
     * Get the hitbox
     * @return hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }
    
}