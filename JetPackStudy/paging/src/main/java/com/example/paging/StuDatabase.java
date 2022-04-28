package com.example.paging;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Student.class},version = 1,exportSchema = false)
public abstract class StuDatabase extends RoomDatabase {
    private static StuDatabase instance;

    static synchronized StuDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context,StuDatabase.class,"student")
                    .build();
        }
        return instance;
    }
    abstract StuDao getStudentDao();
}
