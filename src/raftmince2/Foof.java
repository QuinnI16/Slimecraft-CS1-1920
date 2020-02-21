/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raftmince2;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author 639113
 */
public class Foof extends Entity {
    private static final String TYPE = "Food";
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;
    private static final int WEIGHT = ((int) (Math.random()*5));
    private static final Color COLOR = Color.BLUE;
    
    public Foof(int x, int y){
        super(0,x,y,WIDTH,HEIGHT,COLOR,0,TYPE);
    }
    public Foof(int x, int y, Color color) {
        super(0,x,y,WIDTH,HEIGHT,color,0,TYPE);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.drawRect(super.getX(),super.getY(),super.getWidth(),super.getHeight());
    }
    
    public boolean collide(Entity other) {
        boolean collided = false;
        if (!(other.getType().equals("Food"))) {
        collided = (this.getBounds().intersects(other.getBounds()));
        }
        else {
            collided = false;
        }
        if (collided) {
            this.foofCollide(other);
            other.didCollide();
        }
        return collided;
    }
    
    public void foofCollide(Entity other) {
        if (super.getBounds().intersects(other.getBounds())) {
            other.ate(this.WEIGHT);
            super.die();
        }
    }
}
