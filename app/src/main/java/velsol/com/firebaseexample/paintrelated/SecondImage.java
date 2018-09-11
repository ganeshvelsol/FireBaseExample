package velsol.com.firebaseexample.paintrelated;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import velsol.com.firebaseexample.R;

public class SecondImage extends AppCompatActivity {

    ImageView imagesss;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_image);
        imagesss=(ImageView)findViewById(R.id.imagesss);

        b=getIntent().getExtras();
        String ss=b.getString("strings");
        byte [] encodeByte=Base64.decode(ss, Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        imagesss.setImageBitmap(bitmap);
    }
}
