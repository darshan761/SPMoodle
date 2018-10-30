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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private EditText editText,editText1,editText2,editText3;
    private Spinner spinner, spinner1;
    private Button button,button1;
    private final String TAG = SignUp.class.getSimpleName();
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        editText = (EditText)findViewById(R.id.email);
        editText1 = (EditText)findViewById(R.id.Password);
        editText2 = (EditText)findViewById(R.id.name);
        editText3 = (EditText)findViewById(R.id.id);
        spinner = (Spinner)findViewById(R.id.category);
        spinner1 = (Spinner)findViewById(R.id.dept);
        button = (Button)findViewById(R.id.register);

        progressBar = (ProgressBar)findViewById(R.id.loading);
        progressBar.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.dept, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = editText.getText().toString().trim();
                String password = editText1.getText().toString().trim();
                final String name = editText2.getText().toString().trim();
                String id = editText3.getText().toString().trim();
                String cat = spinner.getSelectedItem().toString();
                final String dept = spinner1.getSelectedItem().toString();
                if( !( TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name) || TextUtils.isEmpty(id) || TextUtils.isEmpty(cat) || TextUtils.isEmpty(dept) )) {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (spinner.getSelectedItem().toString().equals("Professor")) {
                                            Professor p = new Professor();
                                            p.setName(name);
                                            p.setProfID(Integer.parseInt(editText3.getText().toString()));
                                            p.setDept(dept);
                                            p.setEmail(email);
                                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                            DatabaseReference myRef = ref.child("Users");
                                            DatabaseReference my = myRef.child("Professor").child(name);
                                            my.setValue(p);
                                            Toast.makeText(SignUp.this, "You selected Professor", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(SignUp.this, Home.class);
                                            startActivity(i);

                                        }
                                        if(spinner.getSelectedItem().toString().equals("Student")) {
                                            Student s = new Student();
                                            s.setName(name);
                                            s.setUid(Integer.parseInt(editText3.getText().toString()));
                                            s.setDept(spinner1.getSelectedItem().toString());
                                            s.setEmail(email);
                                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                            DatabaseReference myRef = ref.child("Users");
                                            DatabaseReference my = myRef.child("Students").child(name);
                                            my.setValue(s);
                                            Toast.makeText(SignUp.this,"You selected Student", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(SignUp.this,studentHomepge.class);
                                            startActivity(i);
                                        }

                                        // ...
                                    }

                                else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());

                                }
                            }});
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please Enter all Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }



}
