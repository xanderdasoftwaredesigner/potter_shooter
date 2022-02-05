package com.xander.pottershooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Broom2 extends Broom{

    Bitmap[] broom = new Bitmap[6];
    String movingDirection;
    public Broom2(Context context) {
        super(context);
        broom[0] = BitmapFactory.decodeResource(context.getResources(),R.drawable.malfoy_0);
        broom[1] = BitmapFactory.decodeResource(context.getResources(),R.drawable.malfoy_1);
        broom[2] = BitmapFactory.decodeResource(context.getResources(),R.drawable.malfoy_2);
        broom[3] = BitmapFactory.decodeResource(context.getResources(),R.drawable.malfoy_3);
        broom[4] = BitmapFactory.decodeResource(context.getResources(),R.drawable.malfoy_4);
        broom[5] = BitmapFactory.decodeResource(context.getResources(),R.drawable.malfoy_5);
        rand = new Random();
        broomX =  -(200+ rand.nextInt(1500));
        broomY = rand.nextInt(400);
        velocity = increasingVelocity + rand.nextInt(21);
        broomFrame = 0;
        increasingVelocity = 10;
        loopAround = 0;
        resetPosition();
        movingDirection = "right";

    }

    @Override
    public Bitmap getBroom() { return broom[broomFrame]; }

    @Override
    public int getWidth() { return broom[0].getWidth(); }

    @Override
    public int getHeight() { return broom[0].getHeight(); }

    @Override
    public String getMovingDirection(){return movingDirection;}

    @Override
    public int getFrameCount(){return broom.length-1;}

    @Override
    public void resetPosition() {
        loopAround++;
        if(loopAround == 7 && increasingVelocity <200){
            loopAround = 0;
            increasingVelocity += rand.nextInt(20);
        }
        broomX = -(200+ rand.nextInt(1500));
        broomY = rand.nextInt(100);
        velocity = increasingVelocity + rand.nextInt(21);
        broomFrame = 0;
    }


}
