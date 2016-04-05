package com.dddp.fakedianpian;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dddp.fakedianpian.fragment.FragmentDiscount;
import com.dddp.fakedianpian.fragment.FragmentDiscovery;
import com.dddp.fakedianpian.fragment.FragmentHome;
import com.dddp.fakedianpian.fragment.FragmentMy;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    @ViewInject(R.id.rg_main_bottom_tabs)
    private RadioGroup mainBottomTabs;
    @ViewInject(R.id.rb_home)
    private RadioButton home;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        mainBottomTabs.setOnCheckedChangeListener(this);
        home.setChecked(true);
        changeFragment(new FragmentHome());
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch ( checkedId ){
            case R.id.rb_home:
                changeFragment(new FragmentHome());
                break;
            case R.id.rb_discount:
                changeFragment(new FragmentDiscount());
                break;
            case R.id.rb_discovery:
                changeFragment(new FragmentDiscovery());
                break;
            case R.id.rb_my:
                changeFragment(new FragmentMy());
                break;

        }
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();
    }
}

