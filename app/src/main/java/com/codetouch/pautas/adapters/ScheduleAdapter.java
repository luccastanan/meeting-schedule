package com.codetouch.pautas.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codetouch.pautas.R;
import com.codetouch.pautas.database.DatabaseHelper;
import com.codetouch.pautas.models.Schedule;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    private DatabaseHelper db;
    private List<Schedule> scheduleList;
    private int ePosition = -1;
    private int pPosition = -1;

    private ISchedules onSchedules;

    public ScheduleAdapter(DatabaseHelper db, List<Schedule> scheduleList, ISchedules onSchedules) {
        this.db = db;
        this.scheduleList = scheduleList;
        this.onSchedules = onSchedules;
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
        final Schedule schedule = scheduleList.get(position);
        holder.txvTitle.setText(schedule.getTitle());
        holder.txvDescription.setText(schedule.getDescription());
        holder.txvDetails.setText(schedule.getDetails());
        holder.txvAuthor.setText(db.selectUserName(schedule.getAuthorId()));
        holder.btnAction.setText(schedule.isStatus() ? R.string.finalize : R.string.reopen);

        final boolean isExpanded = position == ePosition;
        holder.lytDetails.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.itemView.setActivated(isExpanded);

        if (isExpanded)
            pPosition = position;

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ePosition = isExpanded ? -1 : position;

                notifyItemChanged(pPosition);
                notifyItemChanged(position);
            }
        });

        holder.btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                schedule.setStatus(!schedule.isStatus());
                onSchedules.onAction(schedule);
                scheduleList.remove(schedule);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        CardView layout;
        ConstraintLayout lytDetails;
        TextView txvTitle, txvDescription, txvDetails, txvAuthor;
        Button btnAction;

        MyViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            txvTitle = itemView.findViewById(R.id.txv_title);
            txvDescription = itemView.findViewById(R.id.txv_description);
            lytDetails = itemView.findViewById(R.id.lyt_details);
            txvDetails = itemView.findViewById(R.id.txv_details);
            txvAuthor = itemView.findViewById(R.id.txv_author);
            btnAction = itemView.findViewById(R.id.btn_action);
        }


    }

    public interface ISchedules extends Parcelable {
        void onAction(Schedule schedule);
    }
}
