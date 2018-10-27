package com.example.hp.moodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class course_display extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_display);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        final ArrayList<Course> mDataset = new ArrayList<Course>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        ref.child("course").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 for(DataSnapshot x:  dataSnapshot.getChildren()) {
                     mDataset.add(x.getValue(Course.class));
                 }
                Course[] myDataset = new Course[10];
                myDataset = mDataset.toArray(myDataset);
                // specify an adapter (see also next example)
                mAdapter = new MyAdapter(myDataset);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

    }
}
