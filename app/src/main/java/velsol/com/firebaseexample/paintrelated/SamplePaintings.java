package velsol.com.firebaseexample.paintrelated;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import velsol.com.firebaseexample.R;

public class SamplePaintings extends AppCompatActivity
{
    //this is the original code...
    PaintView paintView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.sample_paintings);

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
             try
             {
                 paintView.setDrawingCacheEnabled(true);
                 //Bitmap bitmap = paintView.getDrawingCache();

                 //code for reading the mage from the paint view and converting into bitmap
                 Bitmap snapshot = Bitmap.createBitmap(paintView.getDrawingCache());


                 //code for converting the bitmap image into string
                 ByteArrayOutputStream baos=new  ByteArrayOutputStream();
                 snapshot.compress(Bitmap.CompressFormat.PNG,100, baos);
                 byte [] b=baos.toByteArray();
                 String temp= Base64.encodeToString(b, Base64.DEFAULT);

                 Intent ss=new Intent(this,SecondImage.class);
                 ss.putExtra("strings",temp);
                 startActivity(ss);

                 //code for converting the string value to bitmap

//                 byte [] encodeByte=Base64.decode(temp,Base64.DEFAULT);
//                 Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);



//                 File f = null;
//                 if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//                     File file = new File(Environment.getExternalStorageDirectory(),"TTImages_cache");
//                     if(!file.exists()){
//                         file.mkdirs();
//                     }
//                     f = new File(file.getAbsolutePath()+file.separator+ "filename"+".png");
//                 }
//                 FileOutputStream ostream = new FileOutputStream(f);
//                 bitmap.compress(Bitmap.CompressFormat.PNG, 10, ostream);
//                 ostream.close();

             }catch (Exception e)
             {

             }
         }
         else
         {

         }
        return true;

    }
}
