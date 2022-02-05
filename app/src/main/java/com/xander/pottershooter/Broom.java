package com.xander.pottershooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Broom {
    Bitmap broom[] = new Bitmap[6];
    int broomX, broomY, velocity,broomFrame;
    Random rand;
    int increasingVelocity;
    int loopAround;
    String movingDirection;

    public Broom(Context context){
        broom[0] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hp_0);
        broom[1] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hp_1);
        broom[2] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hp_2);
        broom[3] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hp_3);
        broom[4] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hp_4);
        broom[5] = BitmapFactory.decodeResource(context.getResources(),R.drawable.hp_5);
        rand = new Random();
        broomX = GameView.dWidth + rand.nextInt(1200);
        broomY = rand.nextInt(300);
        velocity = increasingVelocity + rand.nextInt(13);
        broomFrame = 0;
        increasingVelocity = 10;
        loopAround = 0;
        resetPosition();
        movingDirection = "left";

    }

    public Bitmap getBroom() { return broom[broomFrame]; }
    public int getWidth(){
        return broom[0].getWidth();
    }
    public int getHeight(){ return broom[0].getHeight(); }
    public String getMovingDirection(){return movingDirection;}
    public int getFrameCount(){return broom.length-1;}
    public void resetPosition(){
        loopAround++;
        if(loopAround == 7 && increasingVelocity <200){
            loopAround = 0;
            increasingVelocity += rand.nextInt(20);
        }
        broomX = GameView.dWidth + rand.nextInt(1200);
        broomY = rand.nextInt(100);
        velocity = increasingVelocity + rand.nextInt(13);
        broomFrame = 0;

    }
}