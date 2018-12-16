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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    //Class Variables
    EditText emailtf, passwordtf;
    Button registerBtn;
    FirebaseAuth fAuth;
    String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fAuth = FirebaseAuth.getInstance();
        emailtf = findViewById(R.id.emailTf);
        passwordtf = findViewById(R.id.passwordTf);
        registerBtn = findViewById(R.id.registerBtn);


        //Button which calls register method
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpUser();
            }
        });

    }

    private void setUpUser(){


        email = emailtf.getText().toString();
        password = passwordtf.getText().toString();

        if(TextUtils.isEmpty(email)){
            emailtf.setError("Email field is empty");
        }
        if(TextUtils.isEmpty(password)){
            emailtf.setError("Password field is empty");
        }
        else {
            final ProgressDialog progressD;
            progressD = new ProgressDialog(this);
            progressD.setMessage("Setting up new user...");
            progressD.show();

            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        Toast.makeText(Register.this, "User created! ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Register.this, MapsActivity.class));

                        progressD.dismiss();


                    }
                }
            });
        }
    }



}
