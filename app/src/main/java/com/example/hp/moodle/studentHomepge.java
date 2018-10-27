package com.example.hp.moodle;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class studentHomepge extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homepge);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        Intent i = new Intent(studentHomepge.this, course_display.class);
        startActivity(i);
        finish();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                       if (menuItem.getItemId()==R.id.nav_home){
                           Intent i = new Intent(studentHomepge.this, create.class);
                           startActivity(i);
                           finish();
                       }
                        if (menuItem.getItemId()==R.id.create_course){
                            Intent i = new Intent(studentHomepge.this, create.class);
                            startActivity(i);
                            finish();
                        }
                        if (menuItem.getItemId()==R.id.nav_settings){
                            Intent i = new Intent(studentHomepge.this, Personal.class);
                            startActivity(i);
                            finish();
                        }
                        if (menuItem.getItemId()==R.id.logout){

                        }
                        if (menuItem.getItemId()==R.id.about){
                            Intent i = new Intent(studentHomepge.this, about.class);
                            startActivity(i);
                        }
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
