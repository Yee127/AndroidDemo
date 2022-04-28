package com.example.chapter5.demo593;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.chapter5.R;

public class ContextMainActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_main);

        tv = findViewById(R.id.show);
        registerForContextMenu(tv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.contextmenus,menu);
        menu.setHeaderIcon(R.drawable.ic_launcher_foreground);
        menu.setHeaderTitle("Select TextColor you want");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.color1:
                tv.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.color2:
                tv.setTextColor(Color.rgb(0,255,0));
                break;
            case R.id.color3:
                tv.setTextColor(Color.rgb(0,0,255));
                break;
            case R.id.color4:
                tv.setTextColor(Color.rgb(255,180,0));
                break;
            default:
                tv.setTextColor(Color.rgb(255,255,255));
        }
        return true;
    }
}