package velsol.com.firebaseexample.paintrelated;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import velsol.com.firebaseexample.R;

/**
 * Created by Velsol 170016 on 9/3/2018.
 */

public class PaintView extends View implements View.OnTouchListener
{
    private static final String TAG = "PaintView";
    List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();
    public PaintView(Context context)
    {
        super(context);

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        paint.setUnderlineText(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        for (Point point : points) {
            canvas.drawCircle(point.x, point.y, 8, paint);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        Point point = new Point();
        point.x = motionEvent.getX();
        point.y = motionEvent.getY();
        points.add(point);
        invalidate();
        return true;
    }
    class Point {
        float x, y;

        @Override
        public String toString()
        {
            return x + ", " + y;
        }
    }
}
