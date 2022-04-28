package com.example.chapter6.demo641;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import com.example.chapter6.R;

public class MainActivity641 extends AppCompatActivity {
    private String [] columns = {
            ContactsContract.Contacts._ID, // get ID
            ContactsContract.Contacts.DISPLAY_NAME, // get name
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID
    };

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main641);
        tv = findViewById(R.id.result);
        tv.setText(getQueryData());
    }

    @SuppressLint("Recycle")
    private String getQueryData() {
        StringBuilder builder = new StringBuilder();
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()){
            int idIndex = cursor.getColumnIndex(columns[0]);
            int disPlayNameIndex = cursor.getColumnIndex(columns[1]);
            int id = cursor.getInt(idIndex); // 获取id
            String disPlayName = cursor.getString(disPlayNameIndex);
            Cursor phone = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    columns[3] + "=" + id,
                    null, null);
            while (phone.moveToNext()){
                int phoneColumnIndex = phone.getColumnIndex(columns[2]);
                String phoneString = phone.getString(phoneColumnIndex);
                builder.append(disPlayName+":"+phoneString+"\n");
            }
        }
        cursor.close();
        return builder.toString();
    }
}