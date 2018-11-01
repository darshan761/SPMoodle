package com.example.hp.moodle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
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
        mRecyclerView = findViewById(R.id.my_recycler_view);
        final ArrayList<Course> mDataset = new ArrayList<Course>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);


        ref.child("course").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for(DataSnapshot x: dataSnapshot.getChildren()) {
                    Course c = x.getValue(Course.class);
                    mDataset.add(c);
                    mAdapter = new MyAdapter(mDataset);
                    mRecyclerView.setAdapter(mAdapter);
                    Log.d("nme", c.getName());
                }

                // specify an adapter (see also next example)

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

    }
}
