package com.dddp.fakedianpian.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dddp.fakedianpian.CityActivity;
import com.dddp.fakedianpian.R;
import com.dddp.fakedianpian.utils.MyUtils;
import com.dddp.fakedianpian.utils.SharedPreferencesUtils;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.util.List;
import android.os.Handler;

/**
 * Created by Bruce J on 2016/4/5.
 */
public class FragmentHome extends Fragment implements LocationListener {
    @ViewInject(R.id.tv_city)
    private TextView topCity;
    private String cityName;
    private LocationManager locationManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_index, null );
        x.view().inject(this, view);
        //Get city information
        topCity.setText(SharedPreferencesUtils.getCityName(getActivity()));
        topCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), CityActivity.class), MyUtils.REQUEST_CITY_CODE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == MyUtils.REQUEST_CITY_CODE && resultCode == Activity.RESULT_OK){
            cityName = data.getStringExtra("cityName");
            topCity.setText(cityName);
        }
    }

    // check gps settings
    @Override
    public void onStart() {
        super.onStart();
        checkGpsIsOpened();
    }

    private void checkGpsIsOpened() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean isOpened = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if ( !isOpened ) {
            // enter gps settings page
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(intent, MyUtils.REQUEST_GPS_SETTINGS_CODE);
        }

        // Start locating
        startLocating();
    }

    private void startLocating() {
        Log.i("TAG", "Start to get location");
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                topCity.setText(cityName);
            }
            return false;
        }
    });


    @Override
    public void onLocationChanged(Location location) {
        Log.i("TAG", "Location changed");
        updateWithNewLocation(location);
    }

    private void updateWithNewLocation(Location location) {
        double latitude = 0.0;
        double longitude = 0.0;

        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Log.i("TAG", "Latitude: " +latitude +  ", Longitude: "+longitude);
        } else {
            cityName = "No location info.";
        }
        List<Address> list = null;
        Geocoder geocoder = new Geocoder(getActivity());
        try {
            list = geocoder.getFromLocation(latitude, longitude, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size() ; i++) {
                Address addr = list.get(i);
                cityName = addr.getLocality();  //get city
            }
        }
        // send message to update top city name
        handler.sendEmptyMessage(1);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //save city name into local storage
        SharedPreferencesUtils.putCityName(getActivity(), cityName);
//        stopLocating();
    }

    private void stopLocating() {
        locationManager.removeUpdates(this);
    }
}
