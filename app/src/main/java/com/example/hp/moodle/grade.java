package com.example.hp.moodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hp.moodle.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class grade extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DatabaseReference Ref  = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        final ArrayList<String> mDataset = new ArrayList<String>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        String u = getIntent().getStringExtra("MEME");
        Log.d("frydrice",u);
        Ref.child("enrolled").orderByChild("enrolled_course").equalTo(u).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot x: dataSnapshot.getChildren()){
                   Query e = Ref.child("Users").child("Students").orderByChild("email").equalTo(x.child("student_email").getValue().toString());
                   e.addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           for(DataSnapshot y : dataSnapshot.getChildren()){
                               mDataset.add(y.child("name").getValue().toString());
                               mAdapter = new myAdapter3(mDataset);
                               mRecyclerView.setAdapter(mAdapter);
                           }
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
