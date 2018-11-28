package velsol.com.firebaseexample.bottomNavigation;

import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import velsol.com.firebaseexample.R;
import velsol.com.firebaseexample.fragments.FirstFrag;
import velsol.com.firebaseexample.fragments.SecondFrag;

public class BottomSheets extends AppCompatActivity
{

    ActionBar act;
    BottomNavigationView bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheets);
        bottom=findViewById(R.id.bottom);
        act=getSupportActionBar();

        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.single:
                    {
                        loadFragment(new FirstFrag());
                        act.setTitle("hello");
                        return true;
                    }case R.id.two:
                    {
                        loadFragment(new SecondFrag());
                        act.setTitle("second");
                        return true;
                    }case R.id.three:
                    {
                        loadFragment(new FirstFrag());
                        act.setTitle("first");
                        return true;
                    }case R.id.four:
                    {
                        loadFragment(new SecondFrag());
                        act.setTitle("second");
                        return true;
                    }

                }
                return false;
            }
        });
    }
    public void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
