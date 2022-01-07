package com.anish.calabashbros;

import java.awt.Color;

public class Thing {

    protected World world;

    public Tile<? extends Thing> tile;

    public boolean isDead()
    {
        return false;
    }
    public boolean isPlayer()
    {
        return false;
    }
    public boolean isMon()
    {
        return false;
    }
    public int getX() {
        return this.tile.getxPos();
    }

    public int getY() {
        return this.tile.getyPos();
    }

    public void setTile(Tile<? extends Thing> tile) {
        this.tile = tile;
    }
    public void getHurt(int num)
    {

    }

    public Thing(Color color, char glyph, World world) {
        this.color = color;
        this.glyph = glyph;
        this.world = world;
    }

    protected Color color;

    public Color getColor() {
        return this.color;
    }

    protected char glyph;

    public char getGlyph() {
        return this.glyph;
    }
    public void unmov()
    {
    }
    public void canmov()
    {
        
    }
    public void setColor(Color c)
    {
        this.color=c;
    }
}
