package com.example.hp.moodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class create extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText cname,no,desp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        auth = FirebaseAuth.getInstance();
        cname = findViewById(R.id.c_name);
        no = findViewById(R.id.no);
        desp = findViewById(R.id.desp);
        final Course c = new Course();
        c.setName(cname.getText().toString());
        c.setExp_no(Integer.parseInt(no.getText().toString()));
        c.setDesp(desp.getText().toString());
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("course").push().setValue(c);

        ref.child("professor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot x: dataSnapshot.getChildren()){
                    if(x.child("email").equals(auth.getCurrentUser().getEmail().toString())){
                        c.setProf(x.child("name").getValue().toString());
                        ref.child("course").push().setValue(c);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
