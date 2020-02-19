/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raftmince2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author 639113
 */
public abstract class Entity {
    private int speed;
    private int x,y,vx,vy,strength;
    private int width,height;
    private Color color;
    private boolean active = true;
    private Rectangle bounds;
    private boolean reproduced = false;
    
    public Entity(int speed, int x, int y, int width, int height, Color color, int strength){
        this.speed = speed;
        this.x=x;
        this.y=y;
        this.vx=(int)(Math.random()*this.speed);
        this.vy=(int)(Math.random()*this.speed);
        this.width=width;
        this.height=height;
        this.color=color;
        this.bounds=new Rectangle(x,y,width,height);
        this.strength = strength;
    }
    public void update(){
        this.x += this.vx;
        this.y += this.vy;
        this.bounds = new Rectangle(x,y,width,height);
    }
    
    public abstract void draw(Graphics g);
    
    public boolean collide(Entity other) {
        boolean collided = (this.bounds.intersects(other.bounds));
        if (collided) {
            this.didCollide();
            other.didCollide();
        }
        return collided;
    }
    
    public void didCollide() {
        this.vx=-this.vx;
        this.vy=-this.vy;
    }
    
    public void ate(int foof) {
        this.width+=foof;
        this.height+=foof;
        this.strength+=foof;
    }
    
    public void die() {
        this.active = false;
    }
    
    public boolean fight(Entity other) {
        if (this.strength >= other.getStrength()) {
            other.die();
            return false;
        }
        else {
            this.die();
            return true;
        }
    }
    
    public void grow(int size) {
        this.strength+=size;
        this.height+=size;
        this.width+=size;
    }
    
    public Rectangle getBounds() {
        return bounds;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
    
    public boolean isActive(){
        return this.active;
    }
    
    public void setActive(boolean act) {
        this.active = act;
    }

    public int getStrength() {
        return strength;
    }
    
    public void setRep(boolean bool) {
        this.reproduced = bool;
    }
    
    public boolean didRep(){
        return this.reproduced;
    }
    
}
