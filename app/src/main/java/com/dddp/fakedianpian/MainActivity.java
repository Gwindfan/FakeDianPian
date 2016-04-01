package com.dddp.fakedianpian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.ViewUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.xutils.ViewInjector;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.btn)
    private Button buttonClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = R.id.btn)
    private void click(View view) {
        Toast.makeText(MainActivity.this,"ViewInject works", Toast.LENGTH_SHORT).show();
    }
}

