package com.example.firsttest.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.firsttest.R;

public class CustomViewFreeDots extends View {

    Paint paint = new Paint();

    public CustomViewFreeDots(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int radius = 20;
        paint.setColor(Color.BLACK);
        canvas.drawCircle(x - 50, y, radius, paint);
        canvas.drawCircle(x, y, radius, paint);
        canvas.drawCircle(x + 50, y, radius, paint);

        super.onDraw(canvas);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = 150;
        int height = 150;

        this.setMeasuredDimension(width, height);

    }

}