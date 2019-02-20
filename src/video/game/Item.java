package video.game;

import java.awt.Graphics;

/**
 *
 * @author MarcelA00821875
 */
public abstract class Item {
    protected int x; //Stores x position
    protected int y; //Stores y position
    /**
     * Set initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(int x, int y){
      this.x = x;
      this.y = y;
    }    

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public int getX() {return x;}
    public int getY() {return y;}

    /**
     * To update the positions of the item for every tick
     */
    public abstract void tick();
    /**
     * To paint the item
     * @param g <b>Graphics</b> object to paint the item
     */
    public abstract void render(Graphics g);
}
