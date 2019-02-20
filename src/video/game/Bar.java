package video.game;

import java.awt.Graphics;

/**
 *
 * @author MarcelA00821875
 * @author Mariano
 */
public class Bar extends Item{

    /*!Important: Now we have to use 
        public enum Direcction
        to save the direction and stuff
    */
    private int width;
    private int height;
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
    }
    
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public int getSpeed() {return speed;}
    public boolean getCollision() {return collision;}
    
    public void setWidth(int width){this.width = width;}
    public void setHeight(int height){this.height = height;}
    public void setSpeed(int speed) {this.speed = speed;}
    public void setCollision(boolean collision) {this.collision = collision;}
    
    //tick is used to check bar
    @Override
    public void tick(){
        
    //moving bar depending on flags
        if(game.getKeyManager().left){
          setX(getX() - getSpeed());
        }
        if(game.getKeyManager().right){
          setX(getX() + getSpeed());
        }
      
      //reset x position and y position if collision
      if(getX() + 100 >= game.getWidth()){
        setX(game.getWidth() - 100);
      }else if(getX() <= 0){
        setX(0);
      }
    }
    //displays aka renders
    @Override
    public void render(Graphics g){
      g.drawImage(Assets.bar, getX(), getY(), getWidth(), getHeight(), null);
    }
    //Make sure collision animation lasts the nessesary seconds 
    //tick starts the process
    //render makes sure that it finishes at the seconds necessary
}