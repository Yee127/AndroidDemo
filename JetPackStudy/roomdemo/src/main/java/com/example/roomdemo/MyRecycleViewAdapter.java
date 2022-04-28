package com.example.roomdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdemo.StudentViewModel.StudentViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {
    List<Student> allStudents = new ArrayList<>();

    private StudentViewModel studentViewModel;

    public MyRecycleViewAdapter(StudentViewModel studentViewModel) {
        this.studentViewModel = studentViewModel;
    }

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.cell_card, parent, false);
        final MyViewHolder holder = new MyViewHolder(itemView);
        holder.mSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            // 接收 student
            Student student = (Student) holder.itemView.getTag(R.id.key_for_view_holder);
            if(b){
                holder.textSubTitle.setVisibility(View.VISIBLE);
                student.setShow(true);
                studentViewModel.updateStudent(student);
            }else {
                holder.textSubTitle.setVisibility(View.GONE);
                student.setShow(false);
                studentViewModel.updateStudent(student);
            }
        });
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = allStudents.get(position);
        // 将student传递给 onCreateViewHolder 函数中
        holder.itemView.setTag(R.id.key_for_view_holder,student);
        holder.textId.setText(String.valueOf(position+1));
        holder.textTitle.setText(String.valueOf(student.name));
        holder.textSubTitle.setText(String.valueOf(student.age));
//        holder.mSwitch.setOnCheckedChangeListener(null);
        if (student.isShow()){
            holder.textSubTitle.setVisibility(View.VISIBLE);
            holder.mSwitch.setChecked(true);
        }else {
            holder.textSubTitle.setVisibility(View.GONE);
            holder.mSwitch.setChecked(false);
        }

// 性能优化 ==> 将该部分移到 onCreateViewHolder
//        holder.mSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
//            if(b){
//                holder.textSubTitle.setVisibility(View.VISIBLE);
//                student.setShow(true);
//                studentViewModel.updateStudent(student);
//            }else {
//                holder.textSubTitle.setVisibility(View.GONE);
//                student.setShow(false);
//                studentViewModel.updateStudent(student);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return allStudents.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textId,textTitle,textSubTitle;
        Switch mSwitch;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textId = itemView.findViewById(R.id.text_id);
            textTitle = itemView.findViewById(R.id.text_title);
            textSubTitle = itemView.findViewById(R.id.text_subTitle);
            mSwitch = itemView.findViewById(R.id.switch1);
        }
    }
}
