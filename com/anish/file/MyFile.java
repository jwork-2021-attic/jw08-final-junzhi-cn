package com.anish.file;

import com.anish.*;
import com.anish.calabashbros.Floor;
import com.anish.calabashbros.Grass;
import com.anish.calabashbros.Monster;
import com.anish.calabashbros.Player;
import com.anish.calabashbros.Shield;
import com.anish.calabashbros.Sword;
import com.anish.calabashbros.Thing;
import com.anish.calabashbros.Thorn;
import com.anish.calabashbros.Wall;
import com.anish.calabashbros.World;
import com.anish.screen.WorldScreen;

import my.runo.Mono;

import java.awt.Color;
import java.io.*;

public class MyFile {
    public void file()
    {
        
    }
    public synchronized void out(World world)
    {
        try {
            OutputStream os = new FileOutputStream("save.txt");
            for (int i = 0; i < world.WIDTH; i++) {
                for (int j = 0; j < world.HEIGHT; j++) {
                    os.write((int)world.get(i, j).getGlyph());
                }
            }
            os.close();
            OutputStream osp = new FileOutputStream("pro.txt");
            osp.write(world.getPlayer().getAtt());
            osp.write(world.getPlayer().getHp());
            osp.write(world.getPlayer().getLv());
            osp.write(world.getPlayer().getRealMov());
            osp.close();
            
        } catch (IOException e) {
            System.out.print("Exception");
        }
    }
    public synchronized void in(World world)
    {
        try {
            InputStream is = new FileInputStream("save.txt");
            int size = is.available();
            InputStream isp = new FileInputStream("pro.txt");
            int tn,h,l,a,m;
            Player p;
            a=isp.read();
            h=isp.read();
            l=isp.read();
            m=isp.read();
            p=new Player(Color.WHITE, world, 0, 0,h);
            for(int i=0;i<world.WIDTH;i++)
            {
                for(int j=0;j<world.HEIGHT;j++)
                {
                    world.show(new Thing(Color.black, (char)32, world), i, j);
                }
            }
            for(int i=0;i<=WorldScreen.LEN;i++)
            {
                for(int j=0;j<=WorldScreen.LEN;j++)
                {
                    if(i==0||i==WorldScreen.LEN||j==0||j==WorldScreen.LEN)
                    {
                        world.put(new Wall(world), i, j);
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                tn=is.read();
                //System.out.println(tn);
                switch (tn)
                {
                    case 161://floor
                    world.put(new Floor(world), i/world.HEIGHT, i%world.HEIGHT);
                    break;
                    case 96://grass
                    world.put(new Grass(world), i/world.HEIGHT, i%world.HEIGHT);
                    break;
                    case 64://monster
                    Monster mon=new Monster(Color.BLUE, world, i/world.HEIGHT, i%world.HEIGHT);
                    world.put(mon, i/world.HEIGHT, i%world.HEIGHT);
                    Thread mono =new Thread(new Mono(world,mon,this));
                    mono.start();
                    break;
                    case 2:
                    case 1:
                    case 154:
                    case 165:
                    case 234://player
                    p=new Player(Color.WHITE, world, i/world.HEIGHT, i%world.HEIGHT,h);
                    world.put(p, i/world.HEIGHT, i%world.HEIGHT);
                    p.setPro(h, l, a, m);
                    p.setShape((char)tn);
                    //System.out.println("\n"+h+' '+l+' '+a+' '+m);
                    break;
                    case 184://shield
                    world.put(new Shield(world), i/world.HEIGHT, i%world.HEIGHT);
                    break;
                    case 47://sword
                    world.put(new Sword(world), i/world.HEIGHT, i%world.HEIGHT);
                    break;
                    case 212://thorn
                    world.put(new Thorn(world), i/world.HEIGHT, i%world.HEIGHT);
                    break;
                    case 177://wall
                    world.put(new Wall(world), i/world.HEIGHT, i%world.HEIGHT);
                    break;
                    default:
                    if(i/world.HEIGHT<=WorldScreen.LEN && i%world.HEIGHT<=WorldScreen.LEN )
                    world.put(new Floor(world), i/world.HEIGHT, i%world.HEIGHT);
                    else
                    world.put(new Thing(Color.BLACK, (char)tn, world), i/world.HEIGHT, i%world.HEIGHT);
                    break;
                }
            }
            int hp=p.getHp();
            //System.out.println("\n"+hp);
            world.put(new Thing(Color.red, (char)181, world), 4, world.WIDTH-7);
            world.put(new Thing(Color.white, (char)88, world), 6, world.WIDTH-7);
            if (hp>=100)
            world.put(new Thing(Color.white, (char)49, world), 8, world.WIDTH-7);
            else
            world.put(new Thing(Color.black, (char)49, world), 8, world.WIDTH-7);
            if (hp<100)
            world.put(new Thing(Color.white, (char)(48+hp/10), world), 9, world.WIDTH-7);
            else
            world.put(new Thing(Color.white, (char)(48), world), 9, world.WIDTH-7);
            world.put(new Thing(Color.white, (char)(48+hp%10), world), 10, world.WIDTH-7);
            is.close();
            isp.close();
        } catch (IOException e) {
            System.out.print("Exception");
        }
    }
}
