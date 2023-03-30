package com.example.realm_sql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.channels.DatagramChannel;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    List<DataModal> dataModalList;
    Context context;

    public RecyclerViewAdapter(List<DataModal> dataModalList, Context context) {
        this.dataModalList = dataModalList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_course_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        DataModal modal = dataModalList.get(position);

        holder.vh_CourseTrack.setText(modal.getCourseTracks());
        holder.vh_courseDuration.setText(modal.getCourseDuration());
        holder.vh_CourseDescription.setText(modal.getCourseDescription());
        holder.vh_CourseName.setText(modal.getCourseName());;

    }

    @Override
    public int getItemCount() {
        return dataModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView vh_CourseName;
        private TextView vh_courseDuration;
        private TextView vh_CourseTrack;
        private TextView vh_CourseDescription;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vh_CourseName = itemView.findViewById(R.id.cv_coursename);
            vh_CourseDescription = itemView.findViewById(R.id.cv_coursedesc);
            vh_courseDuration = itemView.findViewById(R.id.cv_courseduration);
            vh_CourseTrack = itemView.findViewById(R.id.cv_corsetrack);
        }
    }
}
