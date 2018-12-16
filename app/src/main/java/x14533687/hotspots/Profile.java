package x14533687.hotspots;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    //Class Variables
    BottomNavigationView navigation;
    Button logoutBtn;
    FirebaseAuth fAuth;
    FirebaseAuth.AuthStateListener fListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logoutBtn = (Button)findViewById(R.id.logoutBtn);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fAuth = FirebaseAuth.getInstance();

        //Logs out the users
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
            }
        });

        //Checks if users is logged in
        //If not then redirect to login page
        fListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth fireAuth) {
                if (fireAuth.getCurrentUser() == null) {
                    Toast.makeText(Profile.this, "User Logged out ", Toast.LENGTH_LONG).show();
                    Intent i;
                    i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }
            }
        };


    }

    //Navigation Options for bottom navigation bar
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_locate:
                    Intent maps = new Intent(Profile.this, MapsActivity.class);
                    startActivity(maps);
                    return true;
                case R.id.navigation_home:
                    Intent home = new Intent(Profile.this, Home.class);
                    startActivity(home);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(fListener);
    }
}
