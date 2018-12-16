package x14533687.hotspots;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {


    //Class variables
    EditText emailtf, passwordtf;
    Button loginBtn, registerBtn;
    FirebaseAuth fAuth;
    String email, password;
    FirebaseAuth.AuthStateListener fListener;
    TextView forgotTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        fAuth = FirebaseAuth.getInstance();
        emailtf = findViewById(R.id.emailTf);
        passwordtf = findViewById(R.id.passwordTf);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        forgotTV = findViewById(R.id.forgotTV);

        //method checks if user is already logged in or not
        fListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if(currentUser != null){
                    startActivity(new Intent(Login.this, MapsActivity.class));
                }
                else{
                    Toast.makeText(Login.this, "Please login or Register " , Toast.LENGTH_LONG).show();
                }
            }
        };

        //Method that checks if fields are empty
        //If not then call Login Method
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailtf.getText().toString();
                password = passwordtf.getText().toString();

                if(TextUtils.isEmpty(email)){
                    emailtf.setError("Email field is empty");
                }
                if(TextUtils.isEmpty(password)){
                    emailtf.setError("Password field is empty");
                }
                else {
                    userLogin();
                }
            }
        });

        //Redirects the user to register activity
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });
    }

    //Method that checks if user has an acount
    //If they do, log them in and redirect them to locations activity
    private void userLogin(){
        final ProgressDialog pd = new ProgressDialog(Login.this);
        pd.setMessage("Logging in...");
        pd.show();

        fAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //If user has an account and enters correct credentials
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in Successfully",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, MapsActivity.class));
                            pd.dismiss();

                        }
                        else{
                            Toast.makeText(Login.this, "There was a problem logging in...",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(fListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        fAuth.addAuthStateListener(fListener);
    }




}
