package com.codetouch.pautas.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codetouch.pautas.Constants;
import com.codetouch.pautas.R;
import com.codetouch.pautas.adapters.ScheduleAdapter;
import com.codetouch.pautas.database.DatabaseHelper;
import com.codetouch.pautas.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;

    private RecyclerView rcvSchedules;
    private List<Schedule> scheduleList;
    private ScheduleAdapter scheduleAdapter;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        layout = findViewById(R.id.layout);
        rcvSchedules = findViewById(R.id.rcv_schedules);
        scheduleList = db.list();

        scheduleAdapter = new ScheduleAdapter(scheduleList, new ScheduleAdapter.ISchedules() {
            @Override
            public void onAction(Schedule schedule) {

            }
        });

        rcvSchedules.setAdapter(scheduleAdapter);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddActivity.class), Constants.RequestCode.NEW_SCHEDULE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.RequestCode.NEW_SCHEDULE) {
            if (resultCode == Activity.RESULT_OK) {
                Schedule schedule = data.getParcelableExtra("schedule");
                scheduleList.add(schedule);
                scheduleAdapter.notifyItemInserted(scheduleList.size() - 1);
                Snackbar.make(layout, R.string.schedule_saved, Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(layout, R.string.schedule_error, Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
