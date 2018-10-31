package com.example.hp.moodle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by HP on 24-10-2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Course> mDataset ;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    int count = 0;
    int no;
    String i;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public MyViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
 /*       public enroll(){
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference Ref = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference r = Ref.child("enrolled").push();
                    r.child("student_email").setValue(auth.getCurrentUser().getEmail());
                    r.child("enrolled_course").setValue(i);
                    Toast.makeText(v.getContext(),"Enrolled successfully",Toast.LENGTH_SHORT).show();
                }
            });
        }  */
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Course> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
         i = mDataset.get(position).getName();
         Log.d("posi",i);
        holder.mTextView.setText(i);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child("Professor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot x:dataSnapshot.getChildren()){
              //      Log.d("RRRRR",x.child("email").getValue().toString());
                    if((x.child("email").getValue().toString()).equals(auth.getCurrentUser().getEmail())){
                        Log.d("BEFORE",Integer.toString(count));
                        count = 2;
                        Log.d("BEFTER",Integer.toString(count));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (count == 0) {
                        DatabaseReference Ref = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference r = Ref.child("enrolled").push();
                        r.child("student_email").setValue(auth.getCurrentUser().getEmail());
                        r.child("enrolled_course").setValue(mDataset.get(position).getName());
                        final DatabaseReference href = Ref.child("Exp").child(holder.mTextView.getText().toString()).push();
                        Ref.child("course").orderByChild("name").equalTo(holder.mTextView.getText().toString()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot x : dataSnapshot.getChildren()) {
                                    no = Integer.parseInt(x.child("exp_no").getValue().toString());
                                    for (int i = 1; i <= no; i++) {
                                        href.child("EXP:" + i).child("student_name").setValue(auth.getCurrentUser().getEmail().toString());
                                    }
                                }}

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        Toast.makeText(v.getContext(), "Enrolled successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(v.getContext(), Exp.class);
                        i.putExtra("Course_name", mDataset.get(position).getName());
                        v.getContext().startActivity(i);
                    }
                }
            });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        Log.d("LENGTH",Integer.toString(mDataset.size()));
        return mDataset.size();
    }
}
