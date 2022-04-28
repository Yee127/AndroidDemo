package com.example.eg3_7_fragment;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class ListFragment extends android.app.ListFragment {
    boolean dualPane; // 是否在一个屏幕上同时显示列表和详细内容
    int curCheckPosition = 0;  // 当前索引位置

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_checked,
                Data.TITLES));   // 为列表设置适配器
//      获取布局文件中添加的FrameLayout帧布局管理器
        View detailFrame = getActivity().findViewById(R.id.detail);
//        判断是否在同一屏幕上同时显示列表和详细内容
        dualPane = detailFrame != null && detailFrame.getVisibility() == View.VISIBLE;
        if(savedInstanceState != null){
//            更新当前选择的索引位置
            curCheckPosition = savedInstanceState.getInt("curChoice",0);
        }

        if (dualPane){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE); // 设置列表为单选模式
            showDetails(curCheckPosition);
        }
    }

    @SuppressLint("WrongConstant")
    private void showDetails(int index) {
        curCheckPosition = index;
        Intent intent;
        if (dualPane){
            getListView().setItemChecked(index,true); // 设置选中列表项为选中状态
            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detail);

            if (detailFragment == null || detailFragment.getShowIndex() != index){
                detailFragment = DetailFragment.newInstance(index);
//              要在activity中管理fragment需要使用FragmentManager
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.detail,detailFragment); // 替换原来显示的内容
//                显示转换效果
                fragmentTransaction.setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commit();
            }else {
                intent = new Intent(getActivity(), MainActivity.DetailActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
            }
        }

    }

    /**
     * 保存当前选中的列表项的索引值
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice",curCheckPosition);
    }

    /**
     *
     * @param l
     * @param v
     * @param position
     * @param id
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        showDetails(position);
    }
}
