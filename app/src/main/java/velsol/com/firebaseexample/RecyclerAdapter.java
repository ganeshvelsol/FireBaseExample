package velsol.com.firebaseexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Velsol 170016 on 7/12/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    Context context;
    ArrayList al=new ArrayList();
    ArrayList al_fullDetails=new ArrayList();
    String key[]={"k1","k2","k3","k4","k5"};

    public RecyclerAdapter(Context context)
    {
        this.context = context;
    }
    int del_pos;
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.firbase_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position)
    {
        DatabaseReference dr= FirebaseDatabase.getInstance().getReference(MainActivity.EMPLOYEE_REF_NAME);
        dr.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
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
            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {
            }
        });
        HashMap hm=(HashMap)al.get(position);
        String name=(String)hm.get(key[1]);
        final String cont=(String)hm.get(key[2]);
        String image=(String)hm.get(key[0]);
        byte img[]= Base64.decode(image,Base64.DEFAULT);
        Bitmap bit= BitmapFactory.decodeByteArray(img,0,img.length);

        holder.images.setImageBitmap(bit);
        holder.text1.setText(name);
        holder.text2.setText(cont);

    }
    @Override
    public int getItemCount()
    {
        return al.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView text1,text2;
        ImageView images;
        public ViewHolder(View itemView)
        {
            super(itemView);
            text1=(TextView)itemView.findViewById(R.id.text1);
            text2=(TextView)itemView.findViewById(R.id.text2);
            images=(ImageView) itemView.findViewById(R.id.image);
        }
    }
}
