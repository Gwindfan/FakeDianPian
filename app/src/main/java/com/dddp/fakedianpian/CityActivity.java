package com.dddp.fakedianpian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dddp.fakedianpian.adapter.MyListAdapter;
import com.dddp.fakedianpian.entity.City;
import com.dddp.fakedianpian.entity.ResponseObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.home_city_list)
public class CityActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    @ViewInject(R.id.city_list)
    private ListView lvCity;
    @ViewInject(R.id.index_city_back)
    private TextView tvCityBack;
    private List<City> cityList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //Add search header view
        View view = LayoutInflater.from(this).inflate(R.layout.home_city_search, null);
        lvCity.addHeaderView(view);

        //get city list from server
        String jsonString = "{\"state\":1,\"datas\":[{\"id\":\"820\",\"name\":\"An Yang\",\"sortKey\":\"A\"},{\"id\":\"68\",\"name\":\"An Qing\",\"sortKey\":\"A\"},{\"id\":\"100\",\"name\":\"Shang Hai\",\"sortKey\":\"S\"}]}";
        //asyn task, no server side for this time
        cityList = parseCityDataJson(jsonString);
        MyListAdapter adapter = new MyListAdapter(cityList);
        lvCity.setAdapter(adapter);

        // set listView listener
        lvCity.setOnItemClickListener(this);
        // Select city page, back
        tvCityBack.setOnClickListener(this);
    }

    public void onClick( View view){
        switch (view.getId()) {
            case R.id.index_city_back:
                finish();
                break;
        }
    }

    private List<City> parseCityDataJson(String jsonString){
        Gson gson = new Gson();
        ResponseObject<List<City>> object = gson.fromJson(jsonString, new TypeToken<ResponseObject<List<City>>>(){}.getType());

        return object.getDatas();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        TextView textView = (TextView) view.findViewById(R.id.city_list_item_name);
//        Log.i("TAG", "cityName: " + textView.getText());
        intent.putExtra("cityName", textView.getText());
        setResult(RESULT_OK, intent);
        finish();
    }

}
