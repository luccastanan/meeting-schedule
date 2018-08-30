package com.codetouch.pautas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codetouch.pautas.adapters.ScheduleAdapter;
import com.codetouch.pautas.models.Schedule;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvSchedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvSchedules = findViewById(R.id.rcv_schedules);

        List<Schedule> scheduleList = new ArrayList<>();
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        scheduleList.add(new Schedule(scheduleList.size(), "Coxinha", "Coisa de comer muito boa", "Coisa de comer muito boa, com recheio de frango", 0, true));
        rcvSchedules.setAdapter(new ScheduleAdapter(scheduleList));
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
    }
}
