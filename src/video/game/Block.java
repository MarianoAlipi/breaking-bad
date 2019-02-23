/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author MarcelA00821875
 * @author MarianoA00822247
 */
public class Block extends Item {
    
    private Game game;
    private boolean collision;
    private boolean visible;    // to show or hide the block whether it's destroyed or not
    
    public Block(int x, int y, Game g) {
        super(x, y);
        this.width = 75;
        this.height = 25;
        this.game = g;
        this.hitbox = new Rectangle(x, y, width, height);
        this.visible = true;
    }

    @Override
    public void tick() {
        // Check for collision with ball
        if (getHitbox().intersects(game.getBall().getHitbox())) {
            setCollision(true);
            game.getBall().setYSpeed(-1 * game.getBall().getYSpeed());
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
        g.setColor(Color.red);
        g.drawRect(x, y, width, height);
    }

    /**
     * Get the hitbox
     * @return hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Get collision
     * @return collision
     */
    public boolean isCollision() {
        return collision;
    }

    /**
     * Get visible
     * @return visible
     */
    public boolean isVisible() {
        return visible;
    }
    
    /**
     * Set collision
     * @param collision 
     */
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    /**
     * Set visible
     * @param visible 
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
}
