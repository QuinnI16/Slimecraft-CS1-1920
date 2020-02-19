/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raftmince2;

import java.awt.Color;

/**
 *
 * @author 639113
 */
public class Virus extends Foof{
    private static final Color COLOR = Color.GREEN;
    public Virus(int x, int y){
        super(x,y,COLOR);
    }
    @Override
    public void foofCollide(Entity other) {
        if (super.getBounds().intersects(other.getBounds())) {
            other.die();
            super.die();
        }
    }
}
