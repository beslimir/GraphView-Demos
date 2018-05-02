package ehealth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.beslimir.R;
import com.facebook.stetho.Stetho;

import butterknife.BindView;
import butterknife.ButterKnife;

import static ehealth.Constants.SPLASH_DISPLAY_LENGTH;

/**
 * Created by beslimir on 16.01.18..
 */

public class SplashScreen extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash_screen);

        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);

        progressBar.setVisibility(View.VISIBLE);
        openMainActivity();
    }

    private void openMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, DeviceList.class);
//                Intent intent = new Intent(SplashScreen.this, MainActivity.class); //for testing purpose
                startActivity(intent);
                progressBar.setVisibility(View.GONE);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
