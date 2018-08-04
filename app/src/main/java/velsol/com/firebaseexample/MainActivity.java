package velsol.com.firebaseexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    ImageView mImageView;
        Button saveBtn;
        TextView mselectImage;
        EditText mNumberEditTexts,areaEdittexts;
    public static final String EMPLOYEE_REF_NAME="Employee_Details";
    FirebaseAuth mAuth;
    public static final int CAM_REQ_CODE=123;
    public static final int GAL_REQ_CODE=321;

    public static final int CAM_PERMISSION_ACCESS_CODE=111;
    public static final String CAM_PERMISSION_NAME[]= {android.Manifest.permission.CAMERA};
    public static final int GAL_PERMISSION_ACCESS_CODE=222;
    public static final String GAL_PERMISSION_NAME[]={android.Manifest.permission.READ_EXTERNAL_STORAGE};
    Bitmap bit=null;
    String choice[]={"CAMERA","GALLERY"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        mImageView=(ImageView)findViewById(R.id.image_display);
        mselectImage=(TextView) findViewById(R.id.select_image_label);
        saveBtn=(Button) findViewById(R.id.saveBtn);
        mNumberEditTexts=(EditText) findViewById(R.id.number);
        areaEdittexts=(EditText) findViewById(R.id.location);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onButtonClicks();
            }
        });
        mselectImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                pickImage();
            }
        });
    }
    public void pickImage()
    {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(" Select One ");
            adb.setItems(choice, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i) {
                        case 0:
                            int res = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA);
                            if (res == PackageManager.PERMISSION_GRANTED) {
                                Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cam, CAM_REQ_CODE);
                            } else {
                                ActivityCompat.requestPermissions(MainActivity.this, CAM_PERMISSION_NAME, CAM_PERMISSION_ACCESS_CODE);
                            }
                            break;
                        case 1:
                            int res1 = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
                            if (res1 == PackageManager.PERMISSION_GRANTED) {
                                Intent gal = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(gal, GAL_REQ_CODE);
                            } else {
                                ActivityCompat.requestPermissions(MainActivity.this, GAL_PERMISSION_NAME, GAL_PERMISSION_ACCESS_CODE);
                            }

                            break;
                    }
                }
            });
            adb.create().show();
    }
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
        {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            switch (requestCode)
            {
                case CAM_PERMISSION_ACCESS_CODE:
                    if(CAM_PERMISSION_NAME.equals(permissions[0]) && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    {
                        Intent cam=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cam,CAM_REQ_CODE);
                    }
                    break;

                case GAL_PERMISSION_ACCESS_CODE:
                    if(GAL_PERMISSION_NAME.equals(permissions[0]) && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    {
                        Intent gal=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(gal,GAL_REQ_CODE);
                    }
                    break;
            }
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case CAM_REQ_CODE:
                if(resultCode == RESULT_OK)
                {
                    Bundle b=data.getExtras();
                    bit=(Bitmap)b.get("data");
                    mImageView.setImageBitmap(bit);
                }
                break;

            case GAL_REQ_CODE:

                if(resultCode == RESULT_OK)
                {
                    Uri img=data.getData();
                    try
                    {
                        bit=MediaStore.Images.Media.getBitmap(this.getContentResolver(),img);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    mImageView.setImageBitmap(bit);
                }
                break;
        }
    }
    public void onButtonClicks()
    {
        //here upload data to servers
        final String number=mNumberEditTexts.getText().toString().trim();
        final String area=areaEdittexts.getText().toString().trim();

        ByteArrayOutputStream bout=new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG,100,bout);
        byte img[]=bout.toByteArray();
        final String image= Base64.encodeToString(img,Base64.DEFAULT);

        mAuth.createUserWithEmailAndPassword("samosa@gmail.com",area)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "authentication had done", Toast.LENGTH_SHORT).show();
                            AddDetails ef=new AddDetails(image,number,area);
                            DatabaseReference dr= FirebaseDatabase.getInstance().getReference(EMPLOYEE_REF_NAME).child(number);
                            dr.setValue(ef);
                            Toast.makeText(MainActivity.this, "datahas been uploaded ", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        //code is to adding data to firebase database
    }
}
