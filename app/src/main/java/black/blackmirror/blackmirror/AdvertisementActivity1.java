package black.blackmirror.blackmirror;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdvertisementActivity1 extends AppCompatActivity {


    private InterstitialAd mInterstittialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);

        mInterstittialAd = new InterstitialAd(this);
        mInterstittialAd.setAdUnitId("взять с сайта адмод");
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstittialAd.loadAd(adRequest);


        mInterstittialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                try {
                    Intent intent  = new Intent(AdvertisementActivity1.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
        });
    }
}