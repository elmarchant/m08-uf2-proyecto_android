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

import com.local.marchant.app.R;

public class CanvasView extends View {

    public SimonButton[] buttons = {
            new SimonButton(0xFF005E00, Color.GREEN, getContext(), R.raw.b3),
            new SimonButton(0xFF690000, Color.RED, getContext(), R.raw.g3),
            new SimonButton(0xFF6E6E00, Color.YELLOW, getContext(), R.raw.d3),
            new SimonButton(0xFF000061, Color.BLUE, getContext(), R.raw.e2)
    };

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    private boolean playing = false;

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
}
