package com.dddp.fakedianpian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class CityActivity extends AppCompatActivity implements View.OnClickListener{
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
        String jsonString = "{\"state\":1,\"datas\":[{\"id\":\"820\",\"name\":\"An Yang\",\"sortKey\":\"A\"},{\"id\":\"68\",\"name\":\"An Qing\",\"sortKey\":\"A\"}]}";
        //asyn task, no server side for this time
        cityList = parseCityDataJson(jsonString);
        MyListAdapter adapter = new MyListAdapter(cityList);
        lvCity.setAdapter(adapter);

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

//    private StringBuffer buffer = new StringBuffer();   //not fixed string buffer
//    private List<String> firstList = new ArrayList<>(); //city name

  /*  public class MyListAdapter extends BaseAdapter {
        private List<City> cityList;

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
            // data show process

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
    }
*/


}
