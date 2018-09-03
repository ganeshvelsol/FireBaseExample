package velsol.com.firebaseexample.paintrelated;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import velsol.com.firebaseexample.R;

public class SamplePaintings extends AppCompatActivity
{
    PaintView paintView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.sample_paintings);

        paintView = new PaintView(this);
        setContentView(paintView);
        paintView.requestFocus();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.for_paints,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         super.onOptionsItemSelected(item);
         int id=item.getItemId();
         if (id==R.id.clear)
         {

         }
         else
         {

         }
        return true;

    }
}
