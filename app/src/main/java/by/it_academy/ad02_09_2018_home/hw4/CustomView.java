package by.it_academy.ad02_09_2018_home.hw4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {


    private Paint paint;
    private int[] degrees;
    private int[] COLORS = {Color.BLUE, Color.GREEN, Color.GRAY, Color.CYAN, Color.RED};
    RectF rectf;
    int temp = 0;

    public CustomView(Context context) {
        super(context);
        init(null);
        this.setWillNotDraw(false);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setValues(int[] values) {
        degrees = new int[values.length];
        calculateDegree(values);
        CustomView.this.invalidate();
        CustomView.this.requestLayout();
    }

    private void calculateDegree(int[] values) {
        int total = 0;
        for (int i = 0; i < values.length; i++) {
            total += values[i];
        }
        double temp;
        for (int i = 0; i < values.length; i++) {
            degrees[i] = (int) ((double) 360 * ((double) values[i]) / ((double) total));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (degrees == null) {
            return;
        } else {
            super.onDraw(canvas);
            rectf = new RectF(0, 0, Math.min(getWidth(), getHeight()),Math.min(getWidth(), getHeight()));
            for (int i = 0; i < degrees.length; i++) {
                if (i == 0) {
                    paint.setColor(COLORS[i]);
                    canvas.drawArc(rectf, temp, degrees[i], true, paint);
                } else {
                    temp += degrees[i - 1];
                    paint.setColor(COLORS[i]);
                    canvas.drawArc(rectf, temp, degrees[i], true, paint);
                }
            }
        }
    }
}

