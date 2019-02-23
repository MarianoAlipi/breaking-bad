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
    
    public Block(int x, int y) {
        super(x, y);
        this.width = 75;
        this.height = 25;
        this.hitbox = new Rectangle(x, y, width, height);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
        g.setColor(Color.red);
        g.drawRect(x, y, width, height);
    }
    
}
