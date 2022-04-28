package com.example.paging;

import android.arch.paging.DataSource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StuDao {
    @Insert()
    void insertStu(Student...students);


    @Query("delete from student")
    void clear();

    @Query("select * from student")
//    LiveData<List<Student>> getAllStu();
    DataSource.Factory<Integer,Student> getAllStu();
}
