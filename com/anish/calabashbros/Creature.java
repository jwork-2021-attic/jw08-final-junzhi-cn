package com.anish.calabashbros;

import java.awt.Color;

public class Creature extends Thing {
    protected Thing pre;
    protected int hp;
    protected int lv;
    Creature(Color color, char glyph, World world) {
        super(color, glyph, world);
        pre=new Floor(world);
    }
    public void moveTo(int xPos, int yPos) {
        int px=this.getX();
        int py=this.getY();
        if (pre!=null && !pre.isPlayer() && pre.getGlyph()!=(char) 96 && !(pre instanceof Monster) )
        {
            this.world.put(pre, px, py);
        }
        else
        {
            this.world.put(new Floor(world), px, py);
        }
        pre=this.world.get(xPos, yPos);
        if(pre.getGlyph()!=(char) 96)
        this.world.put(this, xPos, yPos);
        if(pre.getGlyph()==(char) 212)
        getHurt(5);
    }
    public boolean isDead()
    {
        if (hp<=0)return true;
        return false;
    }

    public void getHurt(int num)
    {
        this.hp-=num*this.world.getDamage();
        if (isDead())
        {
                if (pre!=null && !pre.isPlayer() && pre.getGlyph()!=(char) 96 && !(pre instanceof Monster) )
            {
                this.world.put(pre, this.getX(), this.getY());
            }
            else
            {
                this.world.put(new Floor(world),this.getX(), this.getY());
            }
        }
    }
}
