package velsol.com.firebaseexample.intenttransitions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import velsol.com.firebaseexample.R;
import velsol.com.firebaseexample.RoomDatabase.RoomDataBaseExmpl;

public class IntentsTransitions extends AppCompatActivity
{
    Button first_one;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_transitions);
        first_one=(Button)findViewById(R.id.first_one);
        first_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //writing code for calling the second intent
                Intent ss=new Intent(IntentsTransitions.this, RoomDataBaseExmpl.class);
                startActivity(ss);
                overridePendingTransition(R.anim.entry_anim,R.anim.exit_anim);
            }
        });
    }
}
