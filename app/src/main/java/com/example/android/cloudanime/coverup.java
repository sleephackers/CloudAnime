package com.example.android.cloudanime;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class coverup extends View {
    BitmapDrawable cloud;
    int width, height;
    int xl = -500, xr;
    double num;
    private Handler h;
    private int xVelocity = 20;
    float percentage;
    private final int FRAME_RATE = 30;
    Paint paint1;
    boolean over = false;

    public coverup(Context context) {
        super(context);
        cloud = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cloud);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        xr = width;
        h = new Handler();
        paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setAlpha(0);

    }

    public coverup(Context context, AttributeSet attr) {
        this(context);
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            if (!over)
                invalidate();
            else
                Toast.makeText(getContext(), "Open New Activity", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onDraw(Canvas canvas) {
        if (height % 300 != 0)
            num = height / 400 + 1;
        if (xl == width) {
            xVelocity = -30;
            xl += xVelocity;
            xr -= xVelocity;
        } else {
            xl += xVelocity;
            xr -= xVelocity;
        }
        Log.e("num", "onDraw: " + num + "  " + height);
        for (int i = 0; i < num; i++) {
            canvas.drawBitmap(cloud.getBitmap(), xl, 500 * (i) - 100, null);
            canvas.drawBitmap(cloud.getBitmap(), xl - 500, 500 * (i) - 100, null);
            canvas.drawBitmap(cloud.getBitmap(), xl - 1000, 500 * (i) - 100, null);
            canvas.drawBitmap(cloud.getBitmap(), xr, 500 * (i) - 100, null);
            canvas.drawBitmap(cloud.getBitmap(), xr + 500, 500 * (i) - 100, null);
            canvas.drawBitmap(cloud.getBitmap(), xr + 1000, 500 * (i) - 100, null);
            if (xl > 0) {


            }
        }
        percentage = ((float) xl / width);
        paint1.setAlpha((int) (percentage * 256));

        Log.e("alpha", "ALPHA:" + percentage * 256);
        canvas.drawRect(0, 0, width, height, paint1);
        if (xVelocity < 0 && xl + 500 <= 0)

        {
            over = true;
            paint1.setColor(Color.RED);
            paint1.setAlpha(255);
            canvas.drawRect(0, 0, width, height, paint1);

        }
        h.postDelayed(r, FRAME_RATE);
    }
}
