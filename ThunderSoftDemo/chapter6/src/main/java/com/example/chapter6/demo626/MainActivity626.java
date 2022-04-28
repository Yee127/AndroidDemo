package com.example.chapter6.demo626;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import com.example.chapter6.R;

public class MainActivity626 extends AppCompatActivity {
    private String [] columns = {ContactsContract.Contacts._ID,// 希望活的的ID值
            ContactsContract.Contacts.DISPLAY_NAME // 希望获得到的姓名
    };
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main626);

        tv = findViewById(R.id.res);
        tv.setText(getQueryData());  // 获取标签设置数据
    }

    private String getQueryData() {
        StringBuilder sb = new StringBuilder(); // 保存字符串
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                columns, null, null, null);

        int IDindex = cursor.getColumnIndex(columns[0]);  // 获取ID记录的索引值
        int displayNameIndex = cursor.getColumnIndex(columns[1]); // 获取姓名记录的索引值
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            int id = cursor.getInt(IDindex);
            String displayName = cursor.getString(displayNameIndex);
            sb.append(id+":"+displayName+"\n");
        }
        cursor.close();
        return sb.toString();
    }
}