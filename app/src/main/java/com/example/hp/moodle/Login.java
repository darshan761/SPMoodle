package com.example.hp.moodle;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText editText,editText1;
    private Button button,button1;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private Spinner spinner;
    private final static String TAG = Login.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editText = (EditText)findViewById(R.id.email);
        editText1 = (EditText)findViewById(R.id.Password);
        button  = (Button)findViewById(R.id.login);
        button1 = (Button)findViewById(R.id.reg);
        spinner = findViewById(R.id.logcategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        progressBar = (ProgressBar) findViewById(R.id.loginloading);
        progressBar.setVisibility(View.GONE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = editText.getText().toString().trim();
                String password = editText1.getText().toString().trim();
                if(!(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(Login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }
                else Toast.makeText(getApplicationContext(),"Please Enter all fields",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if(user != null) {
            Toast.makeText(getApplicationContext(),"Login SuccessFull",Toast.LENGTH_SHORT).show();
            if(spinner.getSelectedItem().equals("Student")) {
                Intent i = new Intent(this, Home.class);
                startActivity(i);
                progressBar.setVisibility(View.GONE);
            }
            if(spinner.getSelectedItem().toString().equals("Professor")){
                Intent i = new Intent(this, studentHomepge.class);
                startActivity(i);
                progressBar.setVisibility(View.GONE);
            }
        }
        else Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
    }


}
