package com.example.logindemo.fifthItem;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logindemo.R;
import com.example.logindemo.fifthItem.DB.MyDBHelper;

import java.io.File;

public class FifthItemActivity extends AppCompatActivity {
    private MyDBHelper myDBHelper;

    String [] column = {
      MediaStore.Downloads._ID,
            MediaStore.Downloads.DISPLAY_NAME
    };

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_item);

        myDBHelper = new MyDBHelper(this,"my_db",null,1);
        SQLiteDatabase database = myDBHelper.getWritableDatabase();



//        ContentResolver resolver = getContentResolver();
//        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
//                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
//
//        while (cursor.moveToNext()){
//            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
//            Log.e("music",title);
//
//            String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
//            Log.e("musicer",artist);
//        }
//
//        String title = MediaStore.Files.FileColumns.TITLE;
//
//        Log.e("ttttttt",title);
//
//        Uri uriA = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        Uri uri = MediaStore.Downloads.EXTERNAL_CONTENT_URI;
//        Log.e("uri",uri.toString());
//        Log.e("uriA",uriA.toString());
//        Cursor query = resolver.query(MediaStore.Downloads.EXTERNAL_CONTENT_URI, null, null, null, null);
//        while (query.moveToNext()){
//
//            String string = query.getString(query.getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE));
//            Log.e("string",string);
//
//        }
//
//        Log.e("getFilesDir",">>>>>>>>>>>>>>>>  getFilesDir <<<<<<<<<<<<<<<<<<<");
//        File filesDir = getFilesDir();
//        Log.e("path",filesDir.toString());
//        getFileName(filesDir.listFiles());

        File file = getCacheDir();
        Log.e("Path",file.toString());
//        getFileName(file.listFiles());


    }

    private String getQueryData(Uri uri,String[] columns) {
        StringBuilder sb = new StringBuilder(); // 保存字符串
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri,
                columns, null, null, null);

        int IDindex = cursor.getColumnIndex(columns[0]);  // 获取ID记录的索引值
        int displayNameIndex = cursor.getColumnIndex(columns[1]); // 获取姓名记录的索引值
       while (cursor.moveToNext()){
            int id = cursor.getInt(IDindex);
            String displayName = cursor.getString(displayNameIndex);
            sb.append(id+":"+displayName+"\n");
            Log.e("name",sb.toString());
        }
        cursor.close();
        return sb.toString();
    }

    private String getFileName(File[] files) {
        String str = "";
        if (files != null){// 先判断目录是否为空，否则会报空指针
            for (File file:files){
                if (file.isDirectory()){
                    String dirName = file.getName();
                    Log.e("=======> dirName",dirName);
                    getFileName(file.listFiles());
                }else if(file.isFile()){
                    String fileName = file.getName();
                    Log.e("==============> fileName",fileName);
                }
            }
        }
        return str;
    }


}