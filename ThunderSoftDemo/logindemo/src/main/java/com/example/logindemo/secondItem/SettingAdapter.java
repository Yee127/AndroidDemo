package com.example.logindemo.secondItem;

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

import java.util.List;
import java.util.Map;

public class SettingAdapter extends BaseAdapter {
    private List<Map<String,Object>> list;
    private Context context;

    public SettingAdapter(List<Map<String, Object>> setList, Context context) {
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
        viewHolder.textView.setText(SettingItemBean.getTITLES()[position]);
        viewHolder.imageView.setImageResource(SettingItemBean.getICONS()[position]);
        return convertView;
    }

    private final class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
