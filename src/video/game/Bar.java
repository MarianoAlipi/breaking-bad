package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author MarcelA00821875
 * @author MarianoA00822247
 */
public class Bar extends Item{

    /*!Important: Now we have to use 
        public enum Direcction
        to save the direction and stuff
    */
    private int speed;
    private boolean collision;
    private Game game;
    
    public Bar(int x, int y, int width, int height, Game game){
      //send x and y to the Item constructor
      super(x, y);
      this.width = width;
      this.height = height;
      this.game = game;
      this.speed = 5;
      this.hitbox = new Rectangle(x, y, width, height / 6);
    }
    
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public int getSpeed() {return speed;}
    public boolean getCollision() {return collision;}
    /**
     * Get the hitbox
     * @return hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public void setWidth(int width){this.width = width;}
    public void setHeight(int height){this.height = height;}
    public void setSpeed(int speed) {this.speed = speed;}
    public void setCollision(boolean collision) {this.collision = collision;}
    
    //tick is used to check bar
    @Override
    public void tick() {
        //moving bar depending on flags
        if (game.getKeyManager().left) {
          setX(getX() - getSpeed());
        }
        if (game.getKeyManager().right) {
          setX(getX() + getSpeed());
        }
      
        //reset x position and y position if collision
        if (getX() + getWidth() >= game.getWidth()) {
          setX(game.getWidth() - getWidth());
        } else if (getX() <= 0) {
          setX(0);
        }
        
        // Relocate hitbox
        hitbox.setLocation(getX(), getY());
    }
    //displays aka renders
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bar, getX(), getY(), getWidth(), getHeight(), null);
        
        // Draw the hitbox (for debugging)
        //g.drawRect((int)getHitbox().getX(), (int)getHitbox().getY(), (int)getHitbox().getWidth(), (int)getHitbox().getHeight());
    }
    //Make sure collision animation lasts the nessesary seconds 
    //tick starts the process
    //render makes sure that it finishes at the seconds necessary

}
