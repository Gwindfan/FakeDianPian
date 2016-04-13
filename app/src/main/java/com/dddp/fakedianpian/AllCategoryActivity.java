package com.dddp.fakedianpian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.ViewUtils;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.dddp.fakedianpian.adapter.MyCategoryAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;


public class AllCategoryActivity extends AppCompatActivity {
//    @ViewInject(R.id.home_nav_all_category)
    private GridView categoryList;
    private ImageView homeNavBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_index_nav_all);

        categoryList = (GridView) findViewById(R.id.home_nav_all_category);
        homeNavBack = (ImageView) findViewById(R.id.home_nav_all_back);

        homeNavBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        MyCategoryAdapter adapter = new MyCategoryAdapter();
        categoryList.setAdapter(adapter);
    }
}
