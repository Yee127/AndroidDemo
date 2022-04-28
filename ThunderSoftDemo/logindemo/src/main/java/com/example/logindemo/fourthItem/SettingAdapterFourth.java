package com.example.logindemo.fourthItem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logindemo.R;
import com.example.logindemo.bean.SettingItemBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SettingAdapterFourth extends BaseAdapter {
    private List<String> settingList = new ArrayList<>();
    private List<Map<String,Object>> list;
    private Context context;
    private SettingItemBean bean;

    public SettingAdapterFourth(List<Map<String, Object>> setList, Context context) {
        this.list = setList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        settingList.add(context.getResources().getString(R.string.net));
        settingList.add(context.getResources().getString(R.string.wifi));
        settingList.add(context.getResources().getString(R.string.air));
        settingList.add(context.getResources().getString(R.string.more));
        settingList.add(context.getResources().getString(R.string.privacy));
        settingList.add(context.getResources().getString(R.string.light));
        settingList.add(context.getResources().getString(R.string.file));
        settingList.add(context.getResources().getString(R.string.system));
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.setting_items, parent, false);
            viewHolder.textView = convertView.findViewById(R.id.setting_text);
            viewHolder.imageView = convertView.findViewById(R.id.setting_imgView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(settingList.get(position));
        viewHolder.imageView.setImageResource(SettingItemBean.getICONS()[position]);

        Log.e("yee", "getView: " + position);

        return convertView;
    }

    private final class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
