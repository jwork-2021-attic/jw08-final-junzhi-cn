package my.runo;

import com.anish.calabashbros.Monster;
import com.anish.calabashbros.Wall;
import com.anish.calabashbros.World;

import java.awt.Color;
import java.util.Random;

public class Mono implements Runnable {
    private Monster mon;
    Random r = new Random();
    private World world;
    public Mono(World w) 
    {
        world=w;
        int myx,myy;
        do
        {
        myx=r.nextInt(20);
        myy=r.nextInt(20);
        }
        while (world.get(myx, myy).getGlyph()!=161);
        mon=new Monster(Color.blue, world, myx, myy);
        world.put(mon, myx, myy);
    }
    private boolean isOK(int xPos, int yPos){
        if(xPos >= 0 && xPos < 20 && yPos >= 0 && yPos < 20 && (world.get(xPos,yPos).getGlyph()!=(char)177) && !world.get(xPos,yPos).isPlayer() && !world.get(xPos,yPos).isMon() ){
            return true;
        }
        return false;
    }
    @Override
    public void run() {
            int xPos=0,yPos=0;
                while (!mon.isDead()&& world.isAlive()){
                    int next=r.nextInt(5);
                    xPos = mon.getxPos();
                    yPos = mon.getyPos();
                    if (world.get(xPos+1, yPos).isPlayer())
                    world.get(xPos+1, yPos).getHurt(10);
                    if (world.get(xPos-1, yPos).isPlayer())
                    world.get(xPos+1, yPos).getHurt(10);
                    if (world.get(xPos, yPos+1).isPlayer())
                    world.get(xPos+1, yPos).getHurt(10);
                    if (world.get(xPos, yPos-1).isPlayer())
                    world.get(xPos+1, yPos).getHurt(10);
                    switch (next)
                    {
                        case 1://←
                            if(isOK(xPos - 1, yPos)){
                                mon.moveTo(xPos - 1, yPos);
                                mon.setxPos(xPos - 1);
                                mon.setyPos(yPos);
                            }
                            break;
                        case 2://↑
                            if(isOK(xPos, yPos - 1)){
                                mon.moveTo(xPos, yPos - 1);
                                mon.setxPos(xPos);
                                mon.setyPos(yPos - 1);
                            }
                            break;
                        case 3://→
                            if(isOK(xPos + 1, yPos)){
                                mon.moveTo(xPos + 1, yPos);
                                mon.setxPos(xPos + 1);
                                mon.setyPos(yPos);
                            }
                            break;
                        case 4://↓
                            if(isOK(xPos, yPos + 1)){
                                mon.moveTo(xPos, yPos + 1);
                                mon.setxPos(xPos);
                                mon.setyPos(yPos + 1);
                            }
                            break;
                        default:
                            break;
                    }
                    if (world.get(xPos+1, yPos).isPlayer())
                    world.get(xPos+1, yPos).getHurt(10);
                    if (world.get(xPos-1, yPos).isPlayer())
                    world.get(xPos+1, yPos).getHurt(10);
                    if (world.get(xPos, yPos+1).isPlayer())
                    world.get(xPos+1, yPos).getHurt(10);
                    if (world.get(xPos, yPos-1).isPlayer())
                    world.get(xPos+1, yPos).getHurt(10);
                    try { 
                        Thread.sleep(500);
                    } catch (Exception e) { 
                        System.out.println("err"); 
                    }
                }
                world.put(new Wall(world), xPos, yPos);
        }
}
