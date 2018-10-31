package com.example.hp.moodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class enrolled extends AppCompatActivity {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private int no;
    private RecyclerView.LayoutManager mLayoutManager;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled);
        final ArrayList<String>mdataset = new ArrayList<String>();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

         ref.child("enrolled").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot x:dataSnapshot.getChildren()){
                    if(x.child("student_email").getValue().toString().equals(auth.getCurrentUser().getEmail())) {
                        mdataset.add(x.child("enrolled_course").getValue().toString());
                        mAdapter = new MyAdapter1(mdataset);
                        mRecyclerView.setAdapter(mAdapter);
                    }}}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
