package com.example.eg3_7_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class DetailFragment extends Fragment {
    public static DetailFragment newInstance(int index){
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index",index);
        fragment.setArguments(bundle);
        return fragment;
    }

    public int getShowIndex(){
        return getArguments().getInt("index",0);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container == null){
            return null;
        }

        ScrollView scrollView = new ScrollView(getActivity());
        TextView textView = new TextView(getActivity());
        textView.setPadding(10,10,10,10);
        scrollView.addView(textView);
        textView.setText(Data.DETAIL[getShowIndex()]);
        return scrollView;
    }
}
