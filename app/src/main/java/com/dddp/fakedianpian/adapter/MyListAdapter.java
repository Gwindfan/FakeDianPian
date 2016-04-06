package com.dddp.fakedianpian.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dddp.fakedianpian.R;
import com.dddp.fakedianpian.entity.City;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruce J on 2016/4/7.
 */
public class MyListAdapter extends BaseAdapter {
    private List<City> cityList;
    private StringBuffer buffer = new StringBuffer();   //not fixed string buffer
    private List<String> firstList = new ArrayList<>(); //city name

    public MyListAdapter( List<City> list){
        this.cityList = list;
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            holder = new Holder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_city_list_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        // data show processing
        City city = cityList.get(position);
        String name = city.getName();
        String sort = city.getSortKey();
        if (buffer.indexOf(sort) == -1){
            buffer.append(sort);
            firstList.add(name);
        }
        if (firstList.contains(name)){
            holder.keySort.setText(sort);
            holder.keySort.setVisibility(View.VISIBLE);
        } else {
            holder.keySort.setVisibility(View.GONE);
        }
        holder.cityName.setText(name);

        return convertView;
    }

    public class Holder {
        @ViewInject(R.id.city_list_item_sort)
        public TextView keySort;
        @ViewInject(R.id.city_list_item_name)
        public TextView cityName;
    }
}
