package com.local.marchant.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasView extends View {

    public SimonButton[] buttons = {
            new SimonButton(Color.GREEN, Color.BLACK),
            new SimonButton(Color.RED, Color.BLACK),
            new SimonButton(Color.YELLOW, Color.BLACK),
            new SimonButton(Color.BLUE, Color.BLACK)
    };

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth() / 2;
        float height = getHeight() / 2;

        buttons[0].setRect(0, 0, height, width);
        buttons[1].setRect(0, width, height, getWidth());
        buttons[2].setRect(height, 0, getHeight(), width);
        buttons[3].setRect(height, width, getHeight(), getWidth());


        Paint paint = new Paint();

        for(SimonButton button : buttons){
            if(button.isActive()) paint.setColor(button.getActiveColor());
            else paint.setColor(button.getColor());

            canvas.drawRect(button.getLeft(), button.getTop(), button.getRight(), button.getBottom(), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getActionMasked() == MotionEvent.ACTION_UP){

            for(int i = 0; i < 4; i++){
                if(buttons[i].isActive()) buttons[i].setActive(false);
            }

            Log.i("Action", "Touch up");
        }else if(event.getAction() == MotionEvent.ACTION_DOWN){
            float x = event.getX(), y = event.getY();
            int X = (int) x / (getWidth() / 2), Y = (int) y / (getHeight() / 2);
            int index = 0;
            String color = "";

            if(X == 0 && Y == 0){
                color = "GREEN";
                index = 0;
            }else if(X == 1 && Y == 0){
                color = "RED";
                index = 1;
            } else if(X == 0 && Y == 1) {
                color = "YELLOW";
                index = 2;
            } else if(X == 1 && Y == 1) {
                color = "BLUE";
                index = 3;
            }

            buttons[index].setActive(true);

            Log.i("TAG", "Touched color: " + color);
            Log.i("Action", "Touch down");
        }

        invalidate();
        return true;
    }
}
