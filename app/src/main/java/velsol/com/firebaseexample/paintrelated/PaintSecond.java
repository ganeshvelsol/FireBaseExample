package velsol.com.firebaseexample.paintrelated;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import velsol.com.firebaseexample.R;

public class PaintSecond extends AppCompatActivity
{
    Bitmap originalBitmap,image;
    ImageView iv_ttx;
    EditText et_sample;

    Paint paint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paint_second);

        iv_ttx = (ImageView) findViewById(R.id.iv_ttx);
//to get screen width and hight
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//dimentions x,y of device to create a scaled bitmap having similar dimentions to screen size
        int height1 = displaymetrics.heightPixels;
        int width1 = displaymetrics.widthPixels;
//paint object to define paint properties
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setTextSize(25);
//loading bitmap from drawable
        originalBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//scaling of bitmap
        originalBitmap =Bitmap.createScaledBitmap(originalBitmap, width1, height1, false);
//creating anoter copy of bitmap to be used for editing
        image = originalBitmap.copy(Bitmap.Config.RGB_565, true);

        et_sample =(EditText) findViewById(R.id.et_txt);

        Button btn_save_img = (Button) findViewById(R.id.btn_save_image);
        Button btn_clr_all = (Button) findViewById(R.id.btn_clr_all);

        btn_save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage(image);
            }
        });
        btn_clr_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image = originalBitmap.copy(Bitmap.Config.RGB_565, true);
                iv_ttx.setImageBitmap(image);
            }
        });
        iv_ttx.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                    String user_text= "i";
                            //et_sample.getText().toString();
    //gettin x,y cordinates on screen touch
                float scr_x=motionEvent.getRawX();
                float scr_y=motionEvent.getRawY();
    //funtion called to perform drawing
                createImage(scr_x,scr_y,user_text);
                return true;
            }
        });
    }

    void saveImage(Bitmap img)
    {
        String RootDir = Environment.getExternalStorageDirectory()
                + File.separator + "txt_imgs";
        File myDir=new File(RootDir);
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);

            img.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        Toast.makeText(PaintSecond.this, "Image saved to 'txt_imgs' folder",Toast.LENGTH_LONG).show();
    }
    public Bitmap createImage(float scr_x,float scr_y,String user_text){
        //canvas object with bitmap image as constructor
        Canvas canvas = new Canvas(image);
        int viewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
//removing title bar hight
        scr_y=scr_y- viewTop;
//fuction to draw text on image. you can try more drawing funtions like oval,point,rect,etc...
        canvas.drawText(""+user_text, scr_x, scr_y, paint);
        iv_ttx.setImageBitmap(image);
        return image;
    }
}
