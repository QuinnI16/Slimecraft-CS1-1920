/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raftmince2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author 639113
 */
public class World extends JPanel implements MouseListener{
    private ArrayList<Minceraft> boats = new ArrayList<>();
    private ArrayList<Sheep> baits = new ArrayList<>();
    private ArrayList<Entity> ents = new ArrayList<>();
    private ArrayList<Foof> foofs = new ArrayList<>();
    private ArrayList<Entity> deadeds = new ArrayList<>();
    private ArrayList<Sheep> newedsheps = new ArrayList<>();
    private ArrayList<Minceraft> newedboats = new ArrayList<>();
    Timer timer;
    public World() {
        this.addMouseListener(this);
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 100, 1000/30);
        for (int i = 0; i<50;i++) {
            int x1 = (int)(Math.random()*400);
            int y1 = (int)(Math.random()*300);
            Sheep shep = new Sheep(x1,y1);
            ents.add(shep);
            baits.add(shep);
        }
        for (int i = 0; i<50;i++) {
            int x2 = (int)(Math.random()*800);
            while (x2<400) {
                if (x2<400) {
                x2 = (int)(Math.random()*800);
                }
            }
            int y2 = (int)(Math.random()*600);
            while (y2<300) {
                if (y2<300) {
                y2 = (int)(Math.random()*600);
                }
            }
            Minceraft boat = new Minceraft(x2,y2);
            ents.add(boat);
            boats.add(boat);
        }
        for (int i = 0; i<100;i++) {
            int x3 = (int)(Math.random()*800);
            int y3 = (int)(Math.random()*600);
            Foof foo = new Foof(x3,y3);
            ents.add(foo);
            foofs.add(foo);
        }
        for (int i = 0; i<25;i++) {
            int x4 = (int)(Math.random()*800);
            int y4 = (int)(Math.random()*600);
            Virus fake = new Virus(x4,y4);
            ents.add(fake);
            foofs.add(fake);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    @Override 
    public void mousePressed(MouseEvent e) {
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("\nMouse click at (%d, %d)",e.getX(),e.getY());
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        for (Entity ent : ents) {
            ent.draw(g);
            ent.update();
            ent.collideWorldBounds(800,600);
           for (Entity ent2 : ents) {
                    ent.collide(ent2);
            }
        }
        /**for (Sheep shep : baits) {
                if (shep.isActive()) {
            for (Sheep shep2 : baits) {
                if (shep != shep2&&shep2.isActive()) {
                    shep.collide(shep2);
                    if (!(shep.didRep())) {
                    newedsheps.add(shep.reproduce(shep2));
                    }
                }
            }
                }     
        }
            for (Minceraft boat : boats) {
                if (boat.isActive()) {
            for (Minceraft boat2 : boats) {
                if (boat != boat2&&boat2.isActive()) {
                    boat.collide(boat2);
                    if (!(boat.didRep())) {
                    newedboats.add(boat.reproduce(boat2));
                    }
                }
            }
                }
        }**/
        for (Minceraft boat : boats) {
                if (boat.isActive()) {
                for (Sheep shep : baits) {
                    if (shep.isActive()) {
                        if (boat.collide(shep)) {
                            if (!(boat.fight(shep))) {
                                boat.grow(shep.getStrength());
                                
                            }
                            else {
                                shep.grow(boat.getStrength());
                            }
                        }
                    }
                }
              }
            }
        clearDead();
        //addNew("Sheep");
        //addNew("Minceraft");
    }
    private void clearDead() {
        for (Sheep shep : baits) {
            if (!shep.isActive()) deadeds.add(shep);
        }
        baits.removeAll(deadeds);
        ents.removeAll(deadeds);
        deadeds.clear();
        for (Minceraft boat : boats) {
            if (!boat.isActive()) deadeds.add(boat);
        }
        boats.removeAll(deadeds);
        ents.removeAll(deadeds);
        deadeds.clear();
        for (Foof foof : foofs) {
            if (!foof.isActive()) deadeds.add(foof);
        }
        foofs.removeAll(deadeds);
        ents.removeAll(deadeds);
        deadeds.clear();
    }
    private void addNew(String type) {
        if(type.equals("Minceraft")) {
            for (Entity newed : newedboats) {
                    ents.add(newed);
                    boats.add((Minceraft) newed);
                }
                newedsheps.clear();
        }
        else if (type.equals("Sheep")) {
            for (Entity newed : newedsheps) {
                    ents.add(newed);
                    baits.add((Sheep) newed);
                }
                newedboats.clear();
        }
    }
    public void createEnt(int x,int y, Entity type) {
        if (type.getType().equals("Food")) {
            
        }
        else if (type.getType().equals("Sheep")) {
            
        }
    }
    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            repaint();
        }
    }
}
