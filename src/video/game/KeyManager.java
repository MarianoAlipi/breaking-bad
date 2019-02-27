package video.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author MarcelA00821875
 * @author MarianoA00822247
 */
public class KeyManager implements KeyListener{
    public boolean up;    //flag to move up the player
    public boolean down;  //flag to move down the player
    public boolean left;  //flag to move left the player
    public boolean right; //flag to move right the player
    public boolean p;     //flag to pause the game
    public boolean speed; //flag to change speed
    
    public boolean keys[]; //stores all the flags for every key
    
    public boolean hasSpeed(){return this.speed;}
    
    public void setSpeed(boolean speed){this.speed = speed;}
    
    public KeyManager() {
      keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e){
    }
    
    @Override
    public void keyPressed(KeyEvent e){
       keys[e.getKeyCode()] = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        keys[e.getKeyCode()] = false;
    }
    
    public void tick(){
      up = keys[KeyEvent.VK_UP];
      down = keys[KeyEvent.VK_DOWN];
      left = keys[KeyEvent.VK_LEFT];
      right = keys[KeyEvent.VK_RIGHT];
      p = keys[KeyEvent.VK_P];
    }
}
