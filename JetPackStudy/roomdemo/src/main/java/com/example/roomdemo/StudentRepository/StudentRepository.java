package com.example.roomdemo.StudentRepository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomdemo.Student;
import com.example.roomdemo.StudentViewModel.StudentViewModel;
import com.example.roomdemo.dao.StudentDao;
import com.example.roomdemo.database.MyDatabase;

import java.util.List;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Student>> getAllStudents;


    public StudentRepository(Context context) {
        MyDatabase myDatabase = MyDatabase.getInstance(context.getApplicationContext());
        studentDao = myDatabase.getStudentDao();
        getAllStudents = studentDao.getAllStudentsOfLiveData();
    }

    public void insertStudent(Student...students){
        new InsertAsyncTask(studentDao).execute(students);
    }

    public LiveData<List<Student>> getGetAllStudents() {
        return getAllStudents;
    }

    public LiveData<List<Student>> findStudentWithPattern(String pattern){
        return studentDao.findStudentWithPattern("%" + pattern + "%");
    }
    public void updateStudent(Student...students){
        new UpdateAsyncTask(studentDao).execute(students);
    }

    public void delStudent(Student...students){
        new DeleteAsyncTask(studentDao).execute(students);
    }

    public void clearAllStudent(){
        new ClearAsyncTask(studentDao).execute();
    }






    static class  InsertAsyncTask extends AsyncTask<Student,Void,Void> {
        private StudentDao studentDao;

        public InsertAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students);
            return null;
        }
    }

    static class  DeleteAsyncTask extends AsyncTask<Student,Void,Void>{
        private StudentDao studentDao;

        public DeleteAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.deleteStudent(students);
            return null;
        }
    }

    static class  UpdateAsyncTask extends AsyncTask<Student,Void,Void>{
        private StudentDao studentDao;

        public UpdateAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... studens) {
//            Student student5 = new Student(77,"hello5", 23);
            studentDao.updateStudent(studens);
            return null;
        }
    }

    static class  ClearAsyncTask extends AsyncTask<Void,Void,Void> {
        private StudentDao studentDao;

        public ClearAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            Student student5 = new Student(77,"hello5", 23);
            studentDao.clear();
            return null;
        }
    }

}
