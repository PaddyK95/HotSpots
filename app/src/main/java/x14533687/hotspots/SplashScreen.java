package x14533687.hotspots;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView LMlogo;
    private static final int DELAY_MILLISECONDS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        LMlogo = findViewById(R.id.animLogo);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,Login.class);
                startActivity(i);
                finish();
            }
        }, DELAY_MILLISECONDS);

        LMlogo.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade));



    }



}
