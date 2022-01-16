package com.anish.calabashbros;

import java.awt.Color;

import java.awt.event.KeyEvent;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class World {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    protected int damage;
    private Tile<Thing>[][] tiles;
    private boolean alive;
    private boolean newgame;
    private boolean isbeg;
    private boolean isend;
    private Player player;
    public World() {
        alive=true;
        newgame=true;
        isbeg=true;
        isend=false;
        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                tiles[i][j].setThing(new Floor(this));
            }
        }
        damage=1;
    }
    public synchronized Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public synchronized void put(Thing t, int x, int y) {
        if (!this.isend)
        this.tiles[x][y].setThing(t);
    }
    public synchronized void show(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }
    public int getDamage()
    {
        return damage;
    }
    public void setDamage(int a)
    {
        damage=a;
    }
    public void end()
    {
        alive=false;
        isbeg=false;
        isend=true;
        try { 
            Thread.sleep(500);
        } catch (Exception e) { 
            System.out.println("err"); 
        }
        for(int i=0;i<this.WIDTH;i++)
        {
            for(int j=0;j<this.HEIGHT;j++)
            {
                show(new Thing(Color.black, (char)32, this), i, j);
            }
        }
        String message="You\0are\0dead!!";
        for(int i=0;i<message.length();i++)
        show(new Thing(Color.red, message.charAt(i), this), this.WIDTH/4+i, this.HEIGHT/3);
    }
    private void showMes(String mes,int wid,int hei,Color col)
    {
        
        for(int i=0;i<mes.length();i++)
        put(new Thing(col, mes.charAt(i), this), wid+i, hei);
    }
    public void beg()
    {
        alive=false;
        newgame=true;
        for(int i=0;i<this.WIDTH;i++)
        {
            for(int j=0;j<this.HEIGHT;j++)
            {
                put(new Thing(Color.black, (char)32, this), i, j);
            }
        }
        String message="New\0Game\0";
        showMes(message, this.WIDTH/4, this.HEIGHT/4, Color.yellow);
        message="Continue";
        showMes(message, this.WIDTH/4, this.HEIGHT/4+2, Color.white);
        message="Q\0to\0Change\0Skin";
        showMes(message, this.WIDTH/4, this.HEIGHT/4+4, Color.gray);
        message="E\0to\0Attack";
        showMes(message, this.WIDTH/4, this.HEIGHT/4+5, Color.gray);
        message="Arrow\0Keys\0to\0Move";
        showMes(message, this.WIDTH/4, this.HEIGHT/4+6, Color.gray);
        message="Space\0to\0Flash";
        showMes(message, this.WIDTH/4, this.HEIGHT/4+7, Color.gray);
    }
    public void begKey()
    {
        newgame=!newgame;
        if (newgame)
        {
            String message="New\0Game\0";
            showMes(message, this.WIDTH/4, this.HEIGHT/4, Color.yellow);
            message="Continue";
            showMes(message, this.WIDTH/4, this.HEIGHT/4+2, Color.white);
        }
        else
        {
            String message="New\0Game\0";
            showMes(message, this.WIDTH/4, this.HEIGHT/4, Color.white);
            message="Continue";
            showMes(message, this.WIDTH/4, this.HEIGHT/4+2, Color.yellow);
        }
    }
    public boolean go()
    {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                put(new Floor(this),i,j);
            }
        }
        alive=true;
        return newgame;
    }
    public boolean isAlive()
    {
        return alive;
    }
    public boolean isBeg()
    {
        return isbeg;
    }
    public void setPlayer(Player p)
    {
        player=p;
    }
    public Player getPlayer()
    {
        return player;
    }
    public boolean isNew()
    {
        return newgame;
    }
    public void newWorld()
    {
        alive=false;
    }
}
