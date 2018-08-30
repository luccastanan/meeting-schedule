package com.codetouch.pautas.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codetouch.pautas.R;
import com.codetouch.pautas.models.Schedule;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    private List<Schedule> scheduleList;
    private int expandedPosition = -1;
    private int pExpandedPosition = -1;

    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_schedule, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Schedule schedule = scheduleList.get(position);
        holder.txvTitle.setText(schedule.getTitle());
        holder.txvDescription.setText(schedule.getDescription());
        holder.txvDetails.setText(schedule.getDetails());

        final boolean isExpanded = position == expandedPosition;
        holder.lytDetails.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);

        if (isExpanded)
            pExpandedPosition = position;

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandedPosition = isExpanded ? -1 : position;

                notifyItemChanged(pExpandedPosition);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CardView layout;
        LinearLayout lytDetails;
        TextView txvTitle, txvDescription, txvDetails;

        MyViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            txvTitle = itemView.findViewById(R.id.txv_title);
            txvDescription = itemView.findViewById(R.id.txv_description);
            lytDetails = itemView.findViewById(R.id.lyt_details);
            txvDetails = itemView.findViewById(R.id.txv_details);
        }
    }
}
