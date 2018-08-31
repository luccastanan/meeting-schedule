package com.codetouch.pautas.fragments;


import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codetouch.pautas.R;
import com.codetouch.pautas.adapters.ScheduleAdapter;
import com.codetouch.pautas.database.DatabaseHelper;
import com.codetouch.pautas.models.Schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchedulesFragment extends Fragment {

    private static String ARG_SCHEDULE_LIST = "arg_schedule_list";
    private static String ARG_ON_SCHEDULES = "arg_on_schedules";

    private List<Schedule> scheduleList;
    private RecyclerView rcvSchedules;
    private TextView txvEmpty;
    private ScheduleAdapter scheduleAdapter;

    private ScheduleAdapter.ISchedules onSchedules;

    public static SchedulesFragment newInstance(ArrayList<Schedule> scheduleList, ScheduleAdapter.ISchedules onSchedules) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_SCHEDULE_LIST, scheduleList);
        args.putParcelable(ARG_ON_SCHEDULES, onSchedules);
        SchedulesFragment fragment = new SchedulesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SchedulesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            scheduleList = getArguments().getParcelableArrayList(ARG_SCHEDULE_LIST);
            onSchedules = getArguments().getParcelable(ARG_ON_SCHEDULES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedules, container, false);
        rcvSchedules = view.findViewById(R.id.rcv_schedules);
        txvEmpty = view.findViewById(R.id.txv_empty);
        scheduleAdapter = new ScheduleAdapter(new DatabaseHelper(getContext()), scheduleList, new ScheduleAdapter.ISchedules() {
            @Override
            public void onAction(Schedule schedule) {
                onSchedules.onAction(schedule);
                chechIfEmpty();
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {

            }
        });
        rcvSchedules.setAdapter(scheduleAdapter);
        chechIfEmpty();
        return view;
    }

    private void chechIfEmpty() {
        txvEmpty.setVisibility(scheduleList.size() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void addSchedule(Schedule schedule) {
        scheduleList.add(schedule);
        scheduleAdapter.notifyItemInserted(scheduleList.size() - 1);
        chechIfEmpty();
    }
}
