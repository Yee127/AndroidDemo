package com.example.roomdemo.StudentViewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdemo.Student;
import com.example.roomdemo.StudentRepository.StudentRepository;
import com.example.roomdemo.dao.StudentDao;
import com.example.roomdemo.database.MyDatabase;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
//    private StudentDao studentDao;
//    private LiveData<List<Student>> getAllStudents;
    private StudentRepository repository;
    public StudentViewModel(@NonNull Application application) {
        super(application);
        MyDatabase myDatabase = MyDatabase.getInstance(application);
        repository = new StudentRepository(application);
//        studentDao = myDatabase.getStudentDao();
//        getAllStudents = studentDao.getAllStudentsOfLiveData();

    }


    public void insertStudent(Student...students){
        repository.insertStudent(students);
    }

    public LiveData<List<Student>> getGetAllStudents() {
        return  repository.getGetAllStudents();
    }
    public LiveData<List<Student>> findStuWithPattern(String pattern){
        return repository.findStudentWithPattern(pattern);
    }

    public void updateStudent(Student...students){
        repository.updateStudent(students);
    }

    public void delStudent(Student...students){
        repository.delStudent(students);
    }

    public void clearAllStudent(){
       repository.clearAllStudent();
    }





//    public static class  InsertAsyncTask extends AsyncTask<Student,Void,Void> {
//        private StudentDao studentDao;
//
//        public InsertAsyncTask(StudentDao studentDao) {
//            this.studentDao = studentDao;
//        }
//
//        @Override
//        protected Void doInBackground(Student... students) {
//            studentDao.insertStudent(students);
//            return null;
//        }
//    }
//
//    public static class  DeleteAsyncTask extends AsyncTask<Student,Void,Void>{
//        private StudentDao studentDao;
//
//        public DeleteAsyncTask(StudentDao studentDao) {
//            this.studentDao = studentDao;
//        }
//
//        @Override
//        protected Void doInBackground(Student... students) {
//            studentDao.deleteStudent(students);
//            return null;
//        }
//    }
//
//    public static class  UpdateAsyncTask extends AsyncTask<Student,Void,Void>{
//        private StudentDao studentDao;
//
//        public UpdateAsyncTask(StudentDao studentDao) {
//            this.studentDao = studentDao;
//        }
//
//        @Override
//        protected Void doInBackground(Student... studens) {
////            Student student5 = new Student(77,"hello5", 23);
//            studentDao.updateStudent(studens);
//            return null;
//        }
//    }
//
//    public static class  ClearAsyncTask extends AsyncTask<Void,Void,Void>{
//        private StudentDao studentDao;
//
//        public ClearAsyncTask(StudentDao studentDao) {
//            this.studentDao = studentDao;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
////            Student student5 = new Student(77,"hello5", 23);
//            studentDao.clear();
//            return null;
//        }
//    }

}
