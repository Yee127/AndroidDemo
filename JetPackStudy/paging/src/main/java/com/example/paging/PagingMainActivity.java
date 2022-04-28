package com.example.paging;


import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PagingMainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    StuDao stuDao;
    StuDatabase stuDatabase;
    MyPageAdapater adapter;
    LiveData<PagedList<Student>> allStuLivePaged;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging_main);
        recyclerView = findViewById(R.id.rView);
        adapter = new MyPageAdapater();

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        recyclerView.setAdapter(adapter);

        stuDatabase = StuDatabase.getInstance(this);
        stuDao = stuDatabase.getStudentDao();

        allStuLivePaged = new LivePagedListBuilder<>(stuDao.getAllStu(),30).build();
//        allStuLivePaged.observe(this, new Observer<PagedList<Student>>() {
//            @Override
//            public void onChanged(@android.support.annotation.Nullable @Nullable PagedList<Student> students) {
//                adapter.submitList(students);
//            }
//        });
    }

    public void generate(View view) {
        Student [] stus = new Student[1000];
        for (int i = 0; i<1000; i++){
            Student student = new Student();
            student.setStuNum(i);
        }
        new InsertAsyncTask(stuDao).execute(stus);
    }

    public void clear(View view) {
        new ClearAsyncTask(stuDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Student,Void,Void> {
        StuDao stuDao;

        public InsertAsyncTask(StuDao stuDao) {
            this.stuDao = stuDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            stuDao.insertStu(students);
            return null;
        }
    }


    static class ClearAsyncTask extends AsyncTask<Void,Void,Void>{
        StuDao stuDao;

        public ClearAsyncTask(StuDao stuDao) {
            this.stuDao = stuDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            stuDao.clear();
            return null;
        }
    }
}