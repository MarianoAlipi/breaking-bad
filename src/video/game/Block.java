/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author MarcelA00821875
 * @author MarianoA00822247
 */
public class Block extends Item {
    
    private Game game;
    private int hits;           // the number of hits left to destroy the block
    private boolean visible;    // to show or hide the block whether it's destroyed or not
    
    private Rectangle hitboxSides;
    private Rectangle hitboxUpDown;
    
    public Block(int x, int y, int hits, Game g) {
        super(x, y);
        this.width = 75;
        this.height = 25;
        this.hits = hits;
        this.game = g;
        this.hitboxSides = new Rectangle(x, y + (height / 6), width, (int) (height * 0.7));
        this.hitboxUpDown = new Rectangle(x + (width / 15), y, (int) (width * 0.87), height);
        this.visible = true;
    }

    @Override
    public void tick() {
        // Check for collision with ball
        
        // Check for side collision
        if (getHitboxSides().intersects(game.getBall().getHitbox())) {
            if (--hits <= 0) {
                setVisible(false);
            }
            
            game.getBall().setXSpeed(-1 * game.getBall().getXSpeed());
        }
        
        // Check for top and bottom collision
        if (getHitboxUpDown().intersects(game.getBall().getHitbox())) {
            if (--hits <= 0) {
                setVisible(false);
            }
            
            game.getBall().setYSpeed(-1 * game.getBall().getYSpeed());
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
        g.setColor(Color.red);
        g.drawRect(x, y, width, height);
        
        // Sides hitbox
        g.setColor(Color.blue);
        g.drawRect(hitboxSides.x, hitboxSides.y, hitboxSides.width, hitboxSides.height);
        
        // Top and bottom hitbox
        g.setColor(Color.green);
        g.drawRect(hitboxUpDown.x, hitboxUpDown.y, hitboxUpDown.width, hitboxUpDown.height);
        
        // Draw number of hits
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("" + getHits(), getX() + getWidth() / 2, getY() + getHeight() / 2 + 5);
    }
    
    /**
     * Get the sides' hitbox
     * @return hitboxSides
     */
    public Rectangle getHitboxSides() {
        return hitboxSides;
    }

    /**
     * Get the top and bottom hitbox
     * @return hitboxUpDown
     */
    public Rectangle getHitboxUpDown() {
        return hitboxUpDown;
    }

    /**
     * Get hits
     * @return hits
     */
    public int getHits() {
        return hits;
    }

    /**
     * Get visible
     * @return visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Set hits
     * @param hits 
     */
    public void setHits(int hits) {
        this.hits = hits;
    }

    /**
     * Set visible
     * @param visible 
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
}
