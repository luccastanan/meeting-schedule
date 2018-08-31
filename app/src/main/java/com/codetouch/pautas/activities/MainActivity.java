package com.codetouch.pautas.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.codetouch.pautas.Constants;
import com.codetouch.pautas.R;
import com.codetouch.pautas.adapters.ScheduleAdapter;
import com.codetouch.pautas.database.DatabaseHelper;
import com.codetouch.pautas.fragments.SchedulesFragment;
import com.codetouch.pautas.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ScheduleAdapter.ISchedules {

    private ConstraintLayout layout;
    private BottomNavigationView bottomNavigation;
    private FrameLayout container;
    private SchedulesFragment fragmentOpen, fragmentFinished;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        db = new DatabaseHelper(this);

        container = findViewById(R.id.container);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddActivity.class), Constants.RequestCode.NEW_SCHEDULE);
            }
        });
        fragmentOpen = SchedulesFragment.newInstance((ArrayList<Schedule>) db.listSchedule(true), this);
        fragmentFinished = SchedulesFragment.newInstance((ArrayList<Schedule>) db.listSchedule(false), this);

        openFragment(fragmentOpen);
        openFragment(fragmentFinished);
        openFragment(fragmentOpen);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.RequestCode.NEW_SCHEDULE) {
            if (resultCode == Activity.RESULT_OK) {
                Schedule schedule = data.getParcelableExtra("schedule");
                fragmentOpen.addSchedule(schedule);
                //Snackbar.make(layout, R.string.schedule_saved, Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(layout, R.string.schedule_error, Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_open:
                openFragment(fragmentOpen);
                break;
            case R.id.nav_finish:
                openFragment(fragmentFinished);
                break;
        }
        return true;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().getFragments() == null) {
            transaction.add(container.getId(), fragment);
        } else {
            transaction.replace(container.getId(), fragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onAction(Schedule schedule) {
        db.updateSchedule(schedule);
        if (schedule.isStatus())
            fragmentOpen.addSchedule(schedule);
        else
            fragmentFinished.addSchedule(schedule);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
