package com.anish.calabashbros;
import java.awt.Color;
import java.util.jar.Attributes.Name;

public class Monster extends Creature{
    private int xPos;
    private int yPos;
    protected int lr;
    protected int ud;
    public Monster(Color color, World world, int xPos, int yPos) {
        super(color, (char) 64, world);
        this.xPos = xPos;
        this.yPos = yPos;
        lr=0;
        ud=0;
        hp=100;
        lv=0;
    }
    public int getxPos(){
        return xPos;
    }
    public int getyPos(){
        return yPos;
    }
    public void setxPos(int xPos){
        this.xPos = xPos;
    }
    public void setyPos(int yPos){
        this.yPos = yPos;
    }
    @Override
    public boolean isMon()
    {
        return true;
    }
    @Override
    public void moveTo(int xPos, int yPos) {
        int px=this.getX();
        int py=this.getY();
        if(world.get(xPos, yPos) instanceof Player)
        {
            world.get(xPos, yPos).getHurt(10);
        }
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
        getHurt(50/world.getDamage()/world.getDamage());
        
    }
    
}