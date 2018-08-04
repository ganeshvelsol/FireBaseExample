package velsol.com.firebaseexample.admobs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import velsol.com.firebaseexample.R;

public class AddingAdmobs extends AppCompatActivity
{
    AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_admobs);
        adView=(AdView)findViewById(R.id.adView);
        //adView.setAdSize(AdSize.BANNER);
        MobileAds.initialize(this,"ca-app-pub-5908983824098948/1426271089");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener()
        {
            @Override
            public void onAdLoaded()
            {
                super.onAdLoaded();
            }
            @Override
            public void onAdFailedToLoad(int i)
            {
                super.onAdFailedToLoad(i);
                Toast.makeText(AddingAdmobs.this, ""+i, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
