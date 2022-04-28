package com.example.chapter6.demo642;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.AutoCompleteTextView;

import com.example.chapter6.R;

public class MainActivity642 extends AppCompatActivity {
    private String [] columns = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
    };
    private ContactListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main642);
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                columns, null, null, null);
        adapter = new ContactListAdapter(this, cursor,true);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.edit);
        autoCompleteTextView.setAdapter(adapter);
    }
}