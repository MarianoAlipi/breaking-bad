/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author MarcelA00821875
 * @author MarianoA00822247
 */
public class Power extends Item {

    public enum Type {good, bad};
    private Type type;
    private BufferedImage sprite;
    private int speed;
    private boolean spawned;
    private SoundClip ping;
    private Game game;
    
    public Power (int x, int y, int width, int height, Type type, Game game) {
        super(x, y, width, height);
        this.type = type;
        this.sprite = (type == type.good) ? Assets.powerGood : Assets.powerBad;
        this.speed = (int) Math.floor(Math.random() * 3 + 1);
        this.spawned = true;
        this.hitbox = new Rectangle(x, y, width, height);
        this.ping = new SoundClip("/sounds/ping.wav");
        this.game = game;
    }
    
    @Override
    public void tick() {
        
        if (isSpawned()) {
            
            // Move down
            setY( getY() + speed );

            // Reposition hitbox
            getHitbox().setLocation(getX(), getY());

            if (getY() + getHeight() / 2 >= game.getHeight()) {
                setSpawned(false);
            }
            
            // Collision with bar
            if (getHitbox().intersects(game.getBar().getHitbox())) {
                game.setPowerState( (byte) (( Type.values()[0] == getType() ) ? 1 : 2) );
                setSpawned(false);
                ping.play();
            }
            
        }
            
        
    }

    @Override
    public void render(Graphics g) {
        if (isSpawned())
            g.drawImage(sprite, getX(), getY(), getWidth(), getHeight(), null);
    }

    /**
     * Get type
     * @return type
     */
    public Type getType() {
        return type;
    }
    
    /**
     * Get spawned
     * @return spawned
     */
    public boolean isSpawned() {
        return spawned;
    }

    /**
     * Set type
     * @param type 
     */
    public void setType(Type type) {
        this.type = type;
    }
    
    /**
     * Set spawned
     * @param spawned 
     */
    public void setSpawned(boolean spawned) {
        this.spawned = spawned;
    }
    
    
}
