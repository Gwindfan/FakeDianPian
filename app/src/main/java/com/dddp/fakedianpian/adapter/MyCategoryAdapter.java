package com.dddp.fakedianpian.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dddp.fakedianpian.R;
import com.dddp.fakedianpian.entity.City;
import com.dddp.fakedianpian.utils.MyUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruce J on 2016/4/13.
 */
public class MyCategoryAdapter extends BaseAdapter {
    private String[] category = MyUtils.allCategory;

 /*   public MyCategoryAdapter (List<View> categoryList) {
        this.categoryList = categoryList;
    }*/

    @Override
    public int getCount() {
        return category.length - 1; // remove last "All" since already in All Category page
    }

    @Override
    public Object getItem(int position) {
        return category[position];
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_index_nav_all_item, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.description.setText(MyUtils.allCategory[position]);
        holder.image.setImageResource(MyUtils.allCategoryImages[position]);

        return convertView;
    }

    class Holder {
        @ViewInject(R.id.home_nav_all_image)
        private ImageView image;
        @ViewInject(R.id.home_nav_all_item_desc)
        private TextView description;
        @ViewInject(R.id.home_nav_all_item_number)
        private TextView number;
    }
}
