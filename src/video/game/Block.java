/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package video.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author MarcelA00821875
 * @author MarianoA00822247
 */
public class Block extends Item {

    private int width;
    private int height;
    
    public Block(int x, int y) {
        super(x, y);
        width = 75;
        height = 25;
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
