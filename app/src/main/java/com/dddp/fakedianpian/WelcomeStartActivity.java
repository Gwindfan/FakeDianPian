package com.dddp.fakedianpian;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dddp.fakedianpian.utils.SharedPreferencesUtils;

import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_welcome_start)
public class WelcomeStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_start);
        x.view().inject(this);

        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                /*Check whether user opens app first time*/
                Intent intentMain;
                if ( false == SharedPreferencesUtils.getWelcomeBoolean(getBaseContext()) ) {
                    intentMain = new Intent(getApplicationContext(), MainActivity.class);
                } else {
                    intentMain = new Intent(getApplicationContext(), WelcomeGuideActivity.class);
                    //Already opened App
                    SharedPreferencesUtils.putWelcomeBoolean(getBaseContext(), false);
                }
                startActivity(intentMain);

                return false;
            }
        }).sendEmptyMessageDelayed(0, 3000);
    }
}
