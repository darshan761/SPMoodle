package com.example.hp.moodle;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class create extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText cname,no,desp;
    private Button crete;
    Course c = new Course();
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        auth = FirebaseAuth.getInstance();
        cname = findViewById(R.id.c_name);
        no = findViewById(R.id.no);
        desp = findViewById(R.id.desp);
        crete = findViewById(R.id.crete);

        crete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.child("Users").child("Professor").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                      //  Log.d("course",Long.toString(dataSnapshot.getChildrenCount()));
                        for(DataSnapshot x: dataSnapshot.getChildren()){
                            Log.d("course",x.child("email").getValue().toString());
                            if(x.child("email").getValue().toString().equals(auth.getCurrentUser().getEmail().toString())){

                                c.setName(cname.getText().toString());
                                c.setExp_no(Integer.parseInt(no.getText().toString()));
                                c.setDesp(desp.getText().toString());
                                c.setProf(x.child("name").getValue().toString());
                                ref.child("course").push().setValue(c);
                                Toast.makeText(create.this,"Course added Successfully",Toast.LENGTH_LONG).show();
                                cname.setText("");
                                no.setText("");
                                desp.setText("");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });



    }
}
