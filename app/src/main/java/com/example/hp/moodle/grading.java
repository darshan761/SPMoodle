package com.example.hp.moodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class grading extends AppCompatActivity {
    private SeekBar seekBar,seekBar1,seekBar2,seekBar3;
    private Button submit;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    float p1,p2,p3,p4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grading);
        seekBar = findViewById(R.id.p1);
        seekBar1 = findViewById(R.id.p2);
        seekBar2 = findViewById(R.id.p3);
        seekBar3 = findViewById(R.id.p4);
        submit = findViewById(R.id.submit);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p1 = progress/10;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p2 = progress/10;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p2 = progress/5;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p3 = progress/10;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final float total = p1 + p2 + p3 + p4;
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                ref.child("Exp").child(getIntent().getStringExtra("MEME")).orderByChild("student_name").equalTo(auth.getCurrentUser().getEmail()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot x : dataSnapshot.getChildren()){
                            x.child("p1").getRef().setValue(p1);
                            x.child("p1").getRef().setValue(p1);
                            x.child("p1").getRef().setValue(p1);
                            x.child("p1").getRef().setValue(p1);
                            x.child("Total").getRef().setValue(total);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Toast.makeText(v.getContext(),"Graded ",Toast.LENGTH_LONG).show();
            }
        });

    }
}
