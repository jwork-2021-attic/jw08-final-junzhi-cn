package com.anish.calabashbros;

import java.awt.Color;

public class World {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    protected int damage;
    private Tile<Thing>[][] tiles;
    private boolean alive;
    public World() {
        alive=true;
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
        try { 
            Thread.sleep(500);
        } catch (Exception e) { 
            System.out.println("err"); 
        }
        for(int i=0;i<this.WIDTH;i++)
        {
            for(int j=0;j<this.HEIGHT;j++)
            {
                put(new Thing(Color.black, (char)32, this), i, j);
            }
        }
        String message="You\0are\0dead!!";
        for(int i=0;i<message.length();i++)
        put(new Thing(Color.red, message.charAt(i), this), this.WIDTH/4+i, this.HEIGHT/3);
    }
    public boolean isAlive()
    {
        return alive;
    }
}
