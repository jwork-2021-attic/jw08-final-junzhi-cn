package com.anish.calabashbros;
import java.awt.Color;
import java.math.*;

public class Player extends Creature{
    private int xPos;
    private int yPos;
    protected int lr;
    protected int ud;
    protected int att;
    protected int mov;
    public Player(Color color, World world, int xPos, int yPos) {
        super(color, (char) 2, world);
        this.xPos = xPos;
        this.yPos = yPos;
        lr=0;
        ud=0;
        hp=100;
        lv=0;
        att=1;
        mov=1;
        world.put(new Thing(Color.red, (char)181, this.world), 4, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)88, this.world), 6, world.WIDTH-7);
        if (hp>=100){
        world.put(new Thing(Color.white, (char)49, this.world), 8, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)79, this.world), 9, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)79, this.world), 10, world.WIDTH-7);
        }
        else
        {
        world.put(new Thing(Color.black, (char)49, this.world), 8, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)(48+hp/10), this.world), 9, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)(48+hp%10), this.world), 10, world.WIDTH-7);
        }
    }
    public Player(Color color, World world, int xPos, int yPos,int h) {
        super(color, (char) 2, world);
        this.xPos = xPos;
        this.yPos = yPos;
        lr=0;
        ud=0;
        hp=h;
        lv=0;
        att=1;
        mov=1;
        world.put(new Thing(Color.red, (char)181, this.world), 4, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)88, this.world), 6, world.WIDTH-7);
        if (hp>=100){
        world.put(new Thing(Color.white, (char)49, this.world), 8, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)79, this.world), 9, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)79, this.world), 10, world.WIDTH-7);
        }
        else
        {
        world.put(new Thing(Color.black, (char)49, this.world), 8, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)(48+hp/10), this.world), 9, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)(48+hp%10), this.world), 10, world.WIDTH-7);
        }
    }
    public void setPro(int h,int l,int a,int m)
    {
        hp=h;
        mov=m;
        lv=l;
        att=a;
    }
    public void setHp(int h)
    {
        hp=h;
    }
    public void setMov(int m)
    {
        mov=m;
    }
    public void setAtt(int m)
    {
        att=m;
    }
    public void setLv(int m)
    {
        lv=m;
    }
    @Override
    public boolean isPlayer()
    {
        return true;
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
    
    public void changes(){
        switch(this.getGlyph()){
            case (char) 2:
                this.glyph=(char)1;
                break;
            case (char) 1:
                this.glyph=(char)154;
                break;
            case (char) 154:
                this.glyph=(char)165;
                break;
            case (char) 165:
                this.glyph=(char)234;
                break;
            case (char) 234:
                this.glyph=(char)2;
                break;
            default:
            this.glyph=(char) 2 ;
                break;
            }
    }
    @Override
    public synchronized void moveTo(int xPos, int yPos) {
        int px=this.getX();
        int py=this.getY();
        if (pre!=null && !pre.isPlayer() && pre.getGlyph()!=(char) 96 && !(pre instanceof Monster))
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
        {
            getHurt(5);
        }
        if(pre.getGlyph()==(char) 47)
        {
            att++;
            pre=null;
        }
        else if(pre.getGlyph()==(char) 184)
        {
            mov++;
            pre=null;
        }
    }
    public void setlr(int a)
    {
        lr=a;
        ud=0;
    }
    public void setud(int a)
    {
        ud=a;
        lr=0;
    }
    public int getDir()
    {
        if(ud==1 && lr==0)
        return 1;
        else if(ud==-1 && lr==0)
        return 2;
        else if(ud==0 && lr==-1)
        return 3;
        else if(ud==0 && lr==1)
        return 4;
        return 0;
    }
    @Override
    public boolean isDead()
    {
        if (hp<=0)return true;
        return false;
    }
    @Override
    public void getHurt(int num)
    {
        this.hp=Math.max(hp-num*this.world.getDamage(),0);
        if (isDead())
        {
            world.end();
        }
        world.put(new Thing(Color.red, (char)181, this.world), 4, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)88, this.world), 6, world.WIDTH-7);
        if (hp>=100)
        world.put(new Thing(Color.white, (char)49, this.world), 8, world.WIDTH-7);
        else
        world.put(new Thing(Color.black, (char)49, this.world), 8, world.WIDTH-7);
        if (hp<100)
        world.put(new Thing(Color.white, (char)(48+hp/10), world), 9, world.WIDTH-7);
        else
        world.put(new Thing(Color.white, (char)(48), world), 9, world.WIDTH-7);
        world.put(new Thing(Color.white, (char)(48+hp%10), this.world), 10, world.WIDTH-7);
    }
    public int getAtt()
    {
        return att;
    }
    public int getMov()
    {
        if(mov<=3)
        return mov;
        else return 3;
    }
    public synchronized void attack()
    {
        Thing u,d,l,r;
        u=this.world.get(this.getxPos(), this.getyPos()+1);
        d=this.world.get(this.getxPos(), this.getyPos()-1);
        l=this.world.get(this.getxPos()-1, this.getyPos());
        r=this.world.get(this.getxPos()+1, this.getyPos());
        Color cu,cd,cl,cr;
        cu=u.getColor();
        cd=d.getColor();
        cl=l.getColor();
        cr=r.getColor();
        if(u instanceof Monster)
        {
            u.getHurt(25*att);
            if(u.isDead())
            world.put(new Wall(world), u.getX(), u.getY());
            else
            u.setColor(Color.red);
        }
        else 
        {
        world.put(new Thing(Color.yellow, (char)241, this.world), this.getxPos(), this.getyPos()+1);
        }
        if(d instanceof Monster)
        {
            d.getHurt(25*att);
            if(d.isDead())
            world.put(new Wall(world), d.getX(), d.getY());
            else
            d.setColor(Color.red);
        }
        else
        {
        world.put(new Thing(Color.yellow, (char)241, this.world), this.getxPos(), this.getyPos()-1);
        }
        if(l instanceof Monster)
        {
            l.getHurt(25*att);
            if(l.isDead())
            world.put(new Wall(world), l.getX(), l.getY());
            else
            l.setColor(Color.red);
        }
        else
        {
        world.put(new Thing(Color.yellow, (char)241, this.world), this.getxPos()-1, this.getyPos());
        }
        if(r instanceof Monster)
        {
            r.getHurt(25*att);
            if(r.isDead())
            world.put(new Wall(world), r.getX(), r.getY());
            else
            r.setColor(Color.red);
        }
        else
        {
        world.put(new Thing(Color.yellow, (char)241, this.world), this.getxPos()+1, this.getyPos());
        }
        try { 
            Thread.sleep(200);
        } catch (Exception e) { 
            System.out.println("err"); 
        }
        if(world.isAlive() && world.isBeg())
        {
            if(!(u instanceof Monster))this.world.put(u,this.getxPos(), this.getyPos()+1);
            else u.setColor(cu);
            if(!(d instanceof Monster))this.world.put(d,this.getxPos(), this.getyPos()-1);
            else d.setColor(cd);
            if(!(l instanceof Monster))this.world.put(l,this.getxPos()-1, this.getyPos());
            else l.setColor(cl);
            if(!(r instanceof Monster))this.world.put(r,this.getxPos()+1, this.getyPos());
            else r.setColor(cr);
            try { 
                Thread.sleep(2);
            } catch (Exception e) { 
                System.out.println("err"); 
            }
        }
    }
    public int getHp()
    {
        return hp;
    }
    public int getRealMov()
    {
        return mov;
    }
    public int getLv()
    {
        return lv;
    }
    public void setShape(char g)
    {
        this.glyph=g;
    }
}