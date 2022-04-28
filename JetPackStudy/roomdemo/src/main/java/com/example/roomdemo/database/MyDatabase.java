package com.example.roomdemo.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomdemo.Student;
import com.example.roomdemo.dao.StudentDao;
// 版本迁移  Version 1 --> 2
@Database(entities = {Student.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase mInstance;

    private static final String DATABASE_NAME = "my_db.db";
    public static synchronized MyDatabase getInstance(Context context){
        if (mInstance == null){
            mInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MyDatabase.class,
                    DATABASE_NAME
                    )
//                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_2_0)
                    .build();
        }
        return mInstance;
    }

    static final Migration MIGRATION_2_0 = new Migration(2,0) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table student add column is_show INTEGER not null default 0");
        }
    };
    public abstract StudentDao getStudentDao();

}
