package com.example.hp.moodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Exp extends AppCompatActivity {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int noo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        final String c_nme = getIntent().getStringExtra("Course_name");
        Log.d("boiboi",c_nme);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("course").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                  for(DataSnapshot x : dataSnapshot.getChildren()){

                      if(x.child("name").getValue().toString().equals(c_nme)){

                          noo = Integer.parseInt(x.child("exp_no").getValue().toString());
                          Log.d("boibo",Integer.toString(noo));
                          ArrayList<String>  mdataset = new ArrayList<String>();
                          for(int i = 1;i<=noo;i++){
                              mdataset.add("Experiment No:"+i);

                          }

                          mAdapter = new MyAdapter2(mdataset);
                          mRecyclerView.setAdapter(mAdapter);

                      }
                  }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
