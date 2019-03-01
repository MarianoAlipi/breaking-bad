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
    private double xSpeed, ySpeed, speed;
    private boolean collision;
    private Game game;
    
    public Ball(int x, int y, int width, int height, Game game) {
      //send x and y to the Item constructor
      super(x, y);
      this.width = width;
      this.height = height;
      this.game = game;
      this.xSpeed = 4;
      this.ySpeed = 5;
      this.speed = Math.sqrt( (xSpeed * xSpeed) + (ySpeed * ySpeed) );
      this.collision = false;
      this.hitbox = new Rectangle(x, y, width, height);
    }
    
    public double getXSpeed() {return xSpeed;}
    public double getYSpeed() {return ySpeed;}
    public double getSpeed() {return speed;}
    public boolean isCollision() {return collision;}
    /**
     * Get the hitbox
     * @return hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public void setXSpeed(double xSpeed) {this.xSpeed = xSpeed;}
    public void setYSpeed(double ySpeed) {this.ySpeed = ySpeed;}
    public void setSpeed(double speed) {this.speed = speed;}
    public void setCollision(boolean collision) {this.collision = collision;}
    
    //tick is used to check bar
    @Override
    public void tick() {
        
        // Move ball
        setX( getX() + (int)getXSpeed() );
        setY( getY() + (int)getYSpeed() );
        
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
        setCollision(false);
        
        // Check for collision with bar
        // The farther from the center, the more horizontal the angle is.
        // The closer to the center, the more vertical the angle is.
        // Left side launches the ball to the left.
        // Right side launches the ball to the right.
        if (getHitbox().intersects(game.getBar().getHitbox())) {
            setYSpeed(-1 * Math.abs(getYSpeed()));
            
            // Get the bar's width.
            int barWidth = (int) game.getBar().getHitbox().getWidth();
            
            // Calculate the position where the ball hit the bar.
            int pos = (getX() + getWidth() / 2) - (int)game.getBar().getHitbox().getX();
            
            // If the position is off limits, set it to the limit.
            if (pos < 0)
                pos = 0;
            else if (pos > barWidth)
                pos = barWidth;
            
            // The resulting angle the ball will be bounced at.
            double angle;
            // Flag: bounced on the left or right side?
            boolean left = false;
            
            // Check if the ball hit the bar's left or right side.
            if (pos <= barWidth / 2) {
                left = true;
                // Calculate the new angle.
                angle = (pos / (barWidth / 2.0) ) * 40 + 30;
            } else {
                pos -= barWidth / 2;
                // Calculate the new angle.
                angle = ( ( barWidth / 2.0 - pos) / (barWidth / 2.0) ) * 40 + 30;
            }
            
            // Convert the angle from degrees to radians.
            angle = Math.toRadians(angle);
            
            // Update x and y speed.
            setXSpeed( (left ? -1 : 1) * getSpeed() * Math.cos(angle) );
            setYSpeed( -1 * getSpeed() * Math.sin(angle) );
        }
    }
    
    //displays aka renders
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.ball, getX(), getY(), getWidth(), getHeight(), null);
      
        // Draw the hitbox (for debugging)
        //g.drawRect(getX(), getY(), getWidth(), getHeight());
    }
}