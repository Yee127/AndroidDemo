package com.example.paging;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.temporal.Temporal;

public class MyPageAdapater extends PagedListAdapter<Student,MyPageAdapater.MyViewHolder> {

    public MyPageAdapater() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(Student oldItem, Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(Student oldItem, Student newItem) {
                return oldItem.getStuNum() == newItem.getStuNum();
            }
        });
    }

    @NonNull
    @androidx.annotation.NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @androidx.annotation.NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cell,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @androidx.annotation.NonNull MyViewHolder holder, int position) {
        Student student  = getItem(position);
        if (student == null){
            holder.textView.setText("Loading...");
        }else {
            holder.textView.setText(String.valueOf(student.getStuNum()));
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
       TextView textView;
       public MyViewHolder(View itemView) {
           super(itemView);
           textView = itemView.findViewById(R.id.textView_cell);
       }
   }
}
