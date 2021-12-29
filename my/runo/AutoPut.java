package my.runo;
import java.util.Random;

import com.anish.calabashbros.Shield;
import com.anish.calabashbros.Sword;
import com.anish.calabashbros.World;

public class AutoPut implements Runnable {
    World world;
    Random r;
    public AutoPut(World w) 
    {
        world=w;
        r=new Random();
    }
    @Override
    public void run() {
        int i=0;
        while(world.isAlive()){
            try { 
                Thread.sleep(5000);
            } catch (Exception e) { 
                System.out.println("err"); 
            }
        Thread mono =new Thread(new Mono(world));
        mono.start();
        i++;
        int wx,wy;
        if(i%5==0)
        {
            do{
                wx=r.nextInt(20-1)+1;
                wy=r.nextInt(20-1)+1;
                }
                while(world.get(wx, wy).getGlyph()!=(char)161);
                world.put(new Shield(world), wx, wy);
            do{
                wx=r.nextInt(20-1)+1;
                wy=r.nextInt(20-1)+1;
                }
                while(world.get(wx, wy).getGlyph()!=(char)161);
                world.put(new Sword(world), wx, wy);
        }
        }        
        }
}
