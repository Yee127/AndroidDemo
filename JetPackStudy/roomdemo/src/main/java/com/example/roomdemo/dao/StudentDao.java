package com.example.roomdemo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdemo.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student...students);

    @Delete
    void deleteStudent(Student...students);

    @Update
    void  updateStudent(Student...students);


    @Query("SELECT * from student")
//    List<Student> getAllStudents();
    LiveData<List<Student>> getAllStudentsOfLiveData();

    @Query("delete from student")
    void clear();

    @Query("select * from student where id = :id")
    List<Student> getStudentById(int id);
    @Query("select * from student where name like :pattern")
    LiveData<List<Student>> findStudentWithPattern(String pattern);

}
