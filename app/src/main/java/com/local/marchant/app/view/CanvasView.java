package com.local.marchant.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CanvasView extends View {

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //prueba de git

        float width = getWidth() / 2;
        float height = getHeight() / 2;

        Paint paint = new Paint();

        paint.setColor(Color.GREEN);
        canvas.drawRect(0, 0, width, height, paint);

        paint.setColor(Color.RED);
        canvas.drawRect(width, 0, getWidth(), height, paint);

        paint.setColor(Color.YELLOW);
        canvas.drawRect(0, height, width, getHeight(), paint);

        paint.setColor(Color.BLUE);
        canvas.drawRect(width, height, getWidth(), getHeight(), paint);
    }

    public void clickArea(){

    }
}
