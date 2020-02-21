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
public class Sheep extends Entity {
    private static final String TYPE = "Sheep";
    private static final int WIDTH = 10;
    private static final int HEIGHT = 5;
    private static final int SPEED = 10;
    private static final int STR = ((int) (Math.random()*3));
    private static final Color COLOR = Color.white;
    
    public Sheep(int x, int y){
        super(SPEED,x,y,WIDTH,HEIGHT,COLOR,STR,TYPE);
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.drawRect(super.getX(),super.getY(),super.getWidth(),super.getHeight());
    }
    public Sheep reproduce(Sheep parent) {
        super.setRep(true);
        parent.setRep(true);
        parent.die();
        return (new Sheep(super.getX(),parent.getY()));
    }
}
