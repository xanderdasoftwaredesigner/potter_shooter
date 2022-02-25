package com.xander.pottershooter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;


import java.io.ObjectInputStream;
import java.util.ArrayList;


public class GameView extends View {

    Bitmap background, launcher;
    Rect rect;
    static int dWidth, dHeight;
    ArrayList<Broom> brooms;
    ArrayList<Bludger> bludgers;
    Handler handler;
    Runnable runnable;
    final long UPDATE_MILLIS = 30;
    static int launcherWidth, launcherHeight, launcherX, launcherY;
    Context context;
    int count =0;
    Paint paint = new Paint();
    float CurrentAimingX, CurrentAimingY;


    public GameView(Context context) {
        super(context);
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(),R.drawable.harry_potter_background);
        launcher = BitmapFactory.decodeResource(getResources(), R.drawable.fred_finished);
        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        rect = new Rect(0,0,dWidth,dHeight);
        handler = new Handler();
        runnable = (Runnable) () -> {invalidate();};
        brooms = new ArrayList<>();
        bludgers = new ArrayList<>();
        for(int i=0;i<2;i++){
            Broom broom = new Broom(context);
            brooms.add(broom);
        }
        for(int i=0;i<3;i++){
            Broom2 broom2 = new Broom2(context);
            brooms.add(broom2);
        }
        launcherWidth = launcher.getWidth();
        launcherHeight = launcher.getHeight();
        launcherX = dWidth/2 - launcherWidth/2;
        launcherY = dHeight-launcherHeight;
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawBitmap(background, null,rect,null);
        canvas.drawLine(launcherX, launcherY, CurrentAimingX, CurrentAimingY, paint);
        Broom currentBroom;
        for(int i=0; i<brooms.size(); i++){
            currentBroom = brooms.get(i);
            canvas.drawBitmap(currentBroom.getBroom(),currentBroom.broomX,currentBroom.broomY,null);
            currentBroom.broomFrame++;
            if(currentBroom.broomFrame > currentBroom.getFrameCount()){
                currentBroom.broomFrame = 0;
            }
            if(currentBroom.getMovingDirection().equals("left") ){
                currentBroom.broomX -= currentBroom.velocity;
                if (currentBroom.broomX < -currentBroom.getWidth()){
                    currentBroom.resetPosition();
                }
            }else if(currentBroom.getMovingDirection().equals("right")){
                currentBroom.broomX += currentBroom.velocity;
                if (currentBroom.broomX > (dWidth + currentBroom.getWidth())){
                    currentBroom.resetPosition();
                }
            }
        }
        Bludger currentBludger;
        for (int i=0; i<bludgers.size(); i++){
            currentBludger = bludgers.get(i);
            if (currentBludger.y > -currentBludger.getBludgerHeight()) {
                currentBludger.bludgerFrame++;
                if (currentBludger.bludgerFrame > currentBludger.getFrameCount()) {
                    currentBludger.bludgerFrame = 0;
                }
                int currentVelocityX, currentVelocityY;
                float currentRise,currentRun,totalTarget;
                currentRise = (int)currentBludger.targetY - launcherY;
                currentRun = (int)currentBludger.targetX - launcherX;
                totalTarget = Math.abs(currentRise) + Math.abs(currentRun);
                float PercentToRise, PercentToRun;
                PercentToRise = (float) Math.abs(currentRise)/totalTarget;
                PercentToRun =(float)currentRun/totalTarget;

                currentBludger.y -= (int)(currentBludger.bVelocity * (Math.abs(currentRise)/totalTarget));
                currentBludger.x += (int)(currentBludger.bVelocity * (currentRun/totalTarget));
                canvas.drawBitmap(currentBludger.getBludger(), currentBludger.x, currentBludger.y, null);

                Broom curentBroomDetection;
                for (int b = 0; b < brooms.size(); b++) {
                    curentBroomDetection = brooms.get(b);

                    if (currentBludger.x >= curentBroomDetection.broomX
                            && (currentBludger.x + currentBludger.getBludgerWidth()) <= (curentBroomDetection.broomX + curentBroomDetection.getWidth())
                            && currentBludger.y >= curentBroomDetection.broomY
                            && currentBludger.y <= (curentBroomDetection.broomY + curentBroomDetection.getHeight())) {
                        curentBroomDetection.resetPosition();
                        count++;
                        bludgers.remove(i);
                    }
                }
            }
            else{
                bludgers.remove(i);
            }
        }
        canvas.drawBitmap(launcher,launcherX,launcherY,null);
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        int action = event.getAction();
        CurrentAimingX = touchX;
        CurrentAimingY = touchY;


        if(action == MotionEvent.ACTION_UP){
                Log.i("launcher","is tapped");
                if (bludgers.size() < 4){
                   Bludger b  = new Bludger(context,touchX,touchY,launcherX,launcherY);
                   bludgers.add(b);
                }
        }
        return  true;
    }


}
