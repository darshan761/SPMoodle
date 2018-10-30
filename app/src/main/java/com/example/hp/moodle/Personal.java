package com.example.hp.moodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.moodle.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Personal extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText UID;
    private EditText Class;
    private Button sve;
    private Button edit;
    private FirebaseAuth mauth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        name = findViewById(R.id.user_name);
        email = findViewById(R.id.user_email);
        UID = findViewById(R.id.uid);
        Class = findViewById(R.id.user_clss);
        sve = (Button) findViewById(R.id.save);
        edit = (Button)findViewById(R.id.edit);
        DatabaseReference Ref = FirebaseDatabase.getInstance().getReference();
        Ref.child("Users").child("Students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot x: dataSnapshot.getChildren()){
                   if( x.child("email").getValue().toString().equals(mauth.getCurrentUser().getEmail())){
                        name.setText(x.child("name").getValue().toString());
                        email.setText(mauth.getCurrentUser().getEmail());
                        UID.setText(x.child("uid").getValue().toString());
                        Class.setText(x.child("dept").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        sve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                ref.child("Users").child("Students").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot x : dataSnapshot.getChildren()){
                            if(x.child("email").getValue().toString().equals(mauth.getCurrentUser().getEmail().toString())){
                                x.child("name").getRef().setValue(name.getText());
                                x.child("dept").getRef().setValue(Class.getText());
                                name.setEnabled(false);
                                Class.setEnabled(false);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setEnabled(true);
                Class.setEnabled(true);
            }
        });

    }
}
