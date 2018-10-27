package com.example.hp.moodle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {

    private EditText editText,editText1;
    private Button button;
    private Spinner spinner,spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            editText = (EditText)findViewById(R.id.name);
            editText1 = (EditText)findViewById(R.id.id);
            button = (Button)findViewById(R.id.Continue);
            spinner = (Spinner)findViewById(R.id.category);
            spinner1 = (Spinner) findViewById(R.id.dept);
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(editText.getText().toString()).build();
            user.updateProfile(profileUpdates);
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
                    if(spinner.getSelectedItem().toString().equals("Professor")) {
                        Professor p = new Professor();
                        p.setName(editText.getText().toString());
                        p.setProfID(Integer.parseInt(editText1.getText().toString()));
                        p.setDept(spinner1.getSelectedItem().toString());
                        p.setEmail(user.getEmail());
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference myRef = ref.child("Users");
                        DatabaseReference my = myRef.child("Professor").push();
                        my.setValue(p);
                        Toast.makeText(Home.this,"You selected Professor", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Home.this,Homepge.class);
                        startActivity(i);
                    }
                    if(spinner.getSelectedItem().toString().equals("Student")) {
                        Student s = new Student();
                        s.setName(editText.getText().toString());
                        s.setUid(Integer.parseInt(editText1.getText().toString()));
                        s.setDept(spinner1.getSelectedItem().toString());
                        s.setEmail(user.getEmail());
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference myRef = ref.child("Users");
                        DatabaseReference my = myRef.child("Students").push();
                        my.setValue(s);
                        Toast.makeText(Home.this,"You selected Student", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Home.this,studentHomepge.class);
                        startActivity(i);
                    }


                }
            });

        }
    }
}
