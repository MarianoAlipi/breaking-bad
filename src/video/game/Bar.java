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
    private Animation policeCarCollision;
    private Game game;
    private int powerFrames;
    
    public Bar(int x, int y, int width, int height, Game game){
      //send x and y to the Item constructor
      super(x, y);
      this.width = width;
      this.height = height;
      this.game = game;
      this.speed = 5;
      powerFrames = 0;
      this.hitbox = new Rectangle(x, y + 15, width, height / 6);
      
      this.policeCarCollision = new Animation(Assets.policeCarCollision,100);
    }
    
    public int getSpeed() {return speed;}
    public boolean getCollision() {return collision;}
    
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
        if (getX() + getWidth() >= game.getWidth()){
          setX(game.getWidth() - getWidth());
        } else if (getX() <= 0) {
          setX(0);
        }
        
        // Relocate hitbox
        hitbox.setLocation(getX(), getY() + 15);
        
        // Apply power effect
        if (game.getPowerState() == 1 || game.getPowerState() == 2) {
            if (powerFrames++ <= 600) {
                // Good power
                if (game.getPowerState() == 1) {
                    setWidth(120);
                    hitbox = new Rectangle(getX(), getY() + 15, getWidth(), getHeight() / 6);
                } else if (game.getPowerState() == 2) {
                    // Bad power
                    setWidth(80);
                    hitbox = new Rectangle(getX(), getY() + 15 , getWidth(), getHeight() / 6);
                }
            } else {
                powerFrames = 0;
                game.setPowerState((byte)0);
                setWidth(100);
                hitbox = new Rectangle(getX(), getY() + 15, getWidth(), getHeight() / 6);
            }
        }
        
        hitbox.setLocation(getX(), getY()+15);
        
        //update collision animation
        this.policeCarCollision.tick();
    }
    //displays aka renders
    @Override
    public void render(Graphics g){
        g.drawImage(policeCarCollision.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
        // Draw the hitbox (for debugging)
        // g.drawRect((int)getHitbox().getX(), (int)getHitbox().getY(), (int)getHitbox().getWidth(), (int)getHitbox().getHeight());
    }

}
