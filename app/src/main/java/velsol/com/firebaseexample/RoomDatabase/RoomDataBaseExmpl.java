package velsol.com.firebaseexample.RoomDatabase;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import velsol.com.firebaseexample.R;

public class RoomDataBaseExmpl extends AppCompatActivity
{
    EditText user_name,user_email,user_mobile;
    Button save,read;
    RecyclerView recycler_view;
    LinearLayoutManager llm;
    List<User> users;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_data_base_exmpl);
        initParams();
    }
    public void initParams()
    {
        user_name=(EditText)findViewById(R.id.user_name);
        user_email=(EditText)findViewById(R.id.user_email);
        user_mobile=(EditText)findViewById(R.id.user_mobile);
        save=(Button) findViewById(R.id.save);
        read=(Button) findViewById(R.id.read);
        llm=new LinearLayoutManager(this);
        recycler_view=(RecyclerView)findViewById(R.id.recycler_view);
       final MyUserDatabase rr=Room.databaseBuilder(getApplicationContext(),MyUserDatabase.class,"simple")
                .allowMainThreadQueries().build();

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    rr.userDao().addUser(new User(user_name.getText().toString().trim(),user_email.getText().toString().trim(),user_mobile.getText().toString().trim()));

                }catch (Exception e)
                {
                    Log.e("db-error",""+e);
                }

            }
        });

        read.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                users=rr.userDao().getData();
                RoomDatabaseRecyclerView rm=new RoomDatabaseRecyclerView(RoomDataBaseExmpl.this,users);
                recycler_view.setAdapter(rm);
                recycler_view.setLayoutManager(llm);
                //here reading the data from the database
//                List<User> ss=rr.userDao().getData();
//
//                for (User ur : ss)
//                {
//
//                }
            }
        });
    }
}
