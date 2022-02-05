package com.xander.pottershooter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Bludger{
    public int getBludgerWidth;
    int x, y;
    int bVelocity;
    int bludgerFrame;
    Bitmap bludger[] = new Bitmap[13];
    public Bludger(Context context) {
        bludger[0] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_0);
        bludger[1] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_1);
        bludger[2] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_2);
        bludger[3] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_3);
        bludger[4] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_4);
        bludger[5] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_5);
        bludger[6] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_6);
        bludger[7] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_7);
        bludger[8] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_8);
        bludger[9] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_9);
        bludger[10] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_10);
        bludger[11] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_11);
        bludger[12] = BitmapFactory.decodeResource(context.getResources(),R.drawable.bludger_12);
        x = GameView.dWidth/2 - getBludgerWidth()/2;
        y = GameView.dHeight - GameView.launcherHeight - getBludgerHeight()/2;
        bVelocity = 50;
        bludgerFrame = 0;
    }
    public Bitmap getBludger() { return bludger[bludgerFrame]; }

    public int getBludgerWidth(){
        return bludger[0].getWidth();
    }
    public int getBludgerHeight(){
        return bludger[0].getHeight();
    }
    public int getFrameCount(){
        return bludger.length-1;
    }
}
