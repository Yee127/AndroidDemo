package com.example.chapter5.optionMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter5.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class OptionMenuMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu_main);

        XmlResourceParser xml = getResources().getXml(R.xml.customerxml);
        StringBuilder sb = new StringBuilder("");
        try {
            while (xml.getEventType() != XmlResourceParser.END_DOCUMENT){
                if (xml.getEventType() == XmlResourceParser.START_TAG){
                    String name = xml.getName();
                    if (name.equals("customer")){
                        sb.append("name："+xml.getAttributeValue(0)+"");
                        sb.append("tel："+xml.getAttributeValue(1)+"");
                        sb.append("\n");
                    }
                }
                xml.next();
            }
            TextView tv = findViewById(R.id.show);
            tv.setText(sb.toString());
            registerForContextMenu(tv);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu,menu);
        menu.setHeaderIcon(R.drawable.ic_launcher_foreground);
        menu.setHeaderTitle("请选择");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu,menu);  // 解析菜单文件
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}