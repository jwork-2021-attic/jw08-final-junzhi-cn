package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import com.anish.calabashbros.*;

import my.runo.*;

import asciiPanel.AsciiPanel;

import java.util.Random;

public class WorldScreen implements Screen {

    private World world;
    protected Player player;
    private int[][] map;
    public static final int LEN = 20;

    public WorldScreen() {
        world = new World();
        map=new int[LEN+1][LEN+1];
        // for(int i=0;i<256;i++)
        // {    
        // Thing thing;
        // thing=new Thing(Color.WHITE, (char) i, world);
        // world.put(thing, i%16, i/16);
        // }
        Random r = new Random();
        for(int i=0;i<=LEN;i++)
        {
            for(int j=0;j<=LEN;j++)
            {
                if(i==0||i==LEN||j==0||j==LEN)
                {
                    map[i][j]=5;
                    world.put(new Wall(world), i, j);
                }
                else
                map[i][j]=0;
            }
        }
        for(int i=0;i<this.world.WIDTH;i++)
        {
            for(int j=LEN+1;j<this.world.HEIGHT;j++)
            {
                world.put(new Thing(Color.black, (char)32, this.world), i, j);
            }
        }
        for(int i=LEN+1;i<this.world.WIDTH;i++)
        {
            for(int j=0;j<this.world.HEIGHT;j++)
            {
                world.put(new Thing(Color.black, (char)32, this.world), i, j);
            }
        }
        for(int i=0;i<20;i++)
        {
            int wx=r.nextInt(LEN-1)+1;
            int wy=r.nextInt(LEN-1)+1;
            map[wx][wy]=5;
            world.put(new Wall(world), wx, wy);
        }
        for(int i=0;i<10;i++)
        {
            int wx=r.nextInt(LEN-1)+1;
            int wy=r.nextInt(LEN-1)+1;
            map[wx][wy]=4;
            world.put(new Thorn(world), wx, wy);
        }
        for(int i=0;i<10;i++)
        {
            int wx=r.nextInt(LEN-1)+1;
            int wy=r.nextInt(LEN-1)+1;
            map[wx][wy]=3;
            world.put(new Grass(world), wx, wy);
        }
        int wx=r.nextInt(LEN-1)+1;
        int wy=r.nextInt(LEN-1)+1;
        map[wx][wy]=2;
        world.put(new Sword(world), wx, wy);
        do{
        wx=r.nextInt(LEN-1)+1;
        wy=r.nextInt(LEN-1)+1;
        }
        while(map[wx][wy]==2);
        map[wx][wy]=1;
        world.put(new Shield(world), wx, wy);
        int px,py;
        do{
        px=r.nextInt(LEN-1)+1;
        py=r.nextInt(LEN-1)+1;}
        while(map[px][py]==2||map[px][py]==1);
        player = new Player(Color.WHITE, world, px, py);
        world.put(player, px, py);
        

        Thread autoput =new Thread(new AutoPut(world));
        autoput.start();

    }

    
    private boolean isOK(int xPos, int yPos){
        if(xPos >= 0 && xPos < LEN && yPos >= 0 && yPos < LEN && world.get(xPos,yPos).getGlyph()!=(char) 177 &&!world.get(xPos,yPos).isPlayer() && !world.get(xPos,yPos).isMon() ){
            return true;
        }
        return false;
    }
    public int getMap(int xPos, int yPos){
        if(xPos<=LEN && xPos>=0 && yPos<=LEN && yPos>=0)
        return map[xPos][yPos];
        else return -1;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {
                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public synchronized Screen respondToUserInput(KeyEvent key) {
        int xPos = player.getxPos();
        int yPos = player.getyPos();
        if(world.isAlive())
        switch(key.getKeyCode()){
            case 69://E
                Thread att =new Thread(new PlayerAtt(player));
                att.start();
                break;
            case 81://Q
                player.changes();
                break;
            case 0x20://space
                if(player.getDir()==1)
                {
                    if(isOK(xPos, yPos - player.getMov())){
                        player.moveTo(xPos, yPos - player.getMov());
                        player.setxPos(xPos);
                        player.setyPos(yPos - player.getMov());
                    }
                }
                else if(player.getDir()==2)
                {
                    if(isOK(xPos, yPos + player.getMov())){
                        player.moveTo(xPos, yPos + player.getMov());
                        player.setxPos(xPos);
                        player.setyPos(yPos + player.getMov());
                    }
                }
                else if(player.getDir()==3)
                {
                    if(isOK(xPos - player.getMov(), yPos)){
                        player.moveTo(xPos - player.getMov(), yPos);
                        player.setxPos(xPos - player.getMov());
                        player.setyPos(yPos);
                    }
                }
                else if(player.getDir()==4)
                {
                    if(isOK(xPos + player.getMov(), yPos)){
                        player.moveTo(xPos + player.getMov(), yPos);
                        player.setxPos(xPos + player.getMov());
                        player.setyPos(yPos);
                    }
                }
                break;
            case 0x25://←
                    player.setlr(-1);
                if(isOK(xPos - 1, yPos)){
                    player.moveTo(xPos - 1, yPos);
                    player.setxPos(xPos - 1);
                    player.setyPos(yPos);
                }
                break;
            case 0x26://↑
                    player.setud(1);
                if(isOK(xPos, yPos - 1)){
                    player.moveTo(xPos, yPos - 1);
                    player.setxPos(xPos);
                    player.setyPos(yPos - 1);
                }
                break;
            case 0x27://→
                    player.setlr(1);
                if(isOK(xPos + 1, yPos)){
                    player.moveTo(xPos + 1, yPos);
                    player.setxPos(xPos + 1);
                    player.setyPos(yPos);
                }
                break;
            case 0x28://↓
                    player.setud(-1);
                if(isOK(xPos, yPos + 1)){
                    player.moveTo(xPos, yPos + 1);
                    player.setxPos(xPos);
                    player.setyPos(yPos + 1);
                }
                break;
            default:
                break;
                
        }
        try { 
            Thread.sleep(100);
        } catch (Exception e) { 
            System.out.println("err"); 
        }
        return this;
    }

}
