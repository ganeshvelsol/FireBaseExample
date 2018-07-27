package velsol.com.firebaseexample.location;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import velsol.com.firebaseexample.AddDetails;
import velsol.com.firebaseexample.MainActivity;
import velsol.com.firebaseexample.R;
import velsol.com.firebaseexample.RecyclerAdapter;

public class ReadFromFirebase extends AppCompatActivity
{
    RecyclerView recycler;
    LinearLayoutManager llm;
    ArrayList al=new ArrayList();
    ArrayList al_fullDetails=new ArrayList();
    String key[]={"k1","k2","k3","k4","k5"};
    ListView lvDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_from_firebase);
        llm = new LinearLayoutManager(this);
        recycler=(RecyclerView)findViewById(R.id.recycler);
//        RecyclerAdapter adapter=new RecyclerAdapter(ReadFromFirebase.this);
//        recycler.setAdapter(adapter);
//        recycler.setLayoutManager(llm);
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference(MainActivity.EMPLOYEE_REF_NAME);
        dr.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                try
                {
                    for (DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        AddDetails ef=ds.getValue(AddDetails.class);
                        HashMap hm=new HashMap();
                        hm.put(key[0],ef.getImage());//Name
                        hm.put(key[1],ef.getNumber());//Contact
                        hm.put(key[2],ef.getArea());//Email
                        al.add(hm);
                        al_fullDetails.add(hm);
                    }
                   RecyclerAdapter adapter=new RecyclerAdapter(ReadFromFirebase.this);
                    recycler.setAdapter(adapter);
                    recycler.setLayoutManager(llm);
                }catch (Exception e)
                {
                    Toast.makeText(ReadFromFirebase.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(ReadFromFirebase.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }
   /* public class MyBaseAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return al.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View mview=getLayoutInflater().inflate(R.layout.firbase_list,null,false);

            ImageView ivImage=(ImageView)mview.findViewById(R.id.image);
            TextView tvName=(TextView)mview.findViewById(R.id.text1);
            TextView tvCont=(TextView)mview.findViewById(R.id.text2);

//            HashMap hm=(HashMap)al.get(i);
//            String name=(String)hm.get(key[1]);
//            final String cont=(String)hm.get(key[2]);
//            String image=(String)hm.get(key[0]);
//            byte img[]= Base64.decode(image,Base64.DEFAULT);
//            Bitmap bit= BitmapFactory.decodeByteArray(img,0,img.length);
//
//            ivImage.setImageBitmap(bit);
//            tvName.setText(name);
//            tvCont.setText(cont);
            return mview;
        }
    }*/
}
