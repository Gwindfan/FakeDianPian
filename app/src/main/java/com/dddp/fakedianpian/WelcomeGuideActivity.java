package com.dddp.fakedianpian;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dddp.fakedianpian.adapter.MyPagerAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_welcome_guide)
public class WelcomeGuideActivity extends AppCompatActivity {
    @ViewInject(R.id.vp_welcome_guide)
    private ViewPager viewPagerWelcomeGuide;

    @ViewInject(R.id.btn_enter_app)
    private Button buttonEnterApp;
//    private List<View> guideList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        intViewPager();
    }

    private void intViewPager() {
        List<View> guideList = new ArrayList<>();
/*        int[] guideRes = {R.drawable.welcome_guilde_1, R.drawable.welcome_guilde_2,
                R.drawable.welcome_guilde_3};
        for (int i = 0; i < guideRes.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(guideRes[i]);
            guideList.add(imageView);
            System.out.println("No. " + i);

        }*/
        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.welcome_guilde_1);
        guideList.add(imageView1);
        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.welcome_guilde_2);
        guideList.add(imageView2);
        ImageView imageView3 = new ImageView(this);
        imageView3.setImageResource(R.drawable.welcome_guilde_3);
        guideList.add(imageView3);


        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(guideList);
        viewPagerWelcomeGuide.setAdapter(myPagerAdapter);

    }

    @Event(R.id.btn_enter_app)
    public void click(){

    }
}
