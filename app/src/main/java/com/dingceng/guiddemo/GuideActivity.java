package com.dingceng.guiddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements OnPageChangeListener{
    private static final String TAG = "GuideActivity";
    private List<View> guideList;
    private ViewPager mViewPager;
    private GuidePagerAdapter pagerAdapter;
    private LinearLayout mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        findid();

        initView();
    }

    private void findid() {
        mViewPager= (ViewPager) findViewById(R.id.mViewPager);
        mLinearLayout= (LinearLayout) findViewById(R.id.mLinearLayout);
    }
    private void initView(){
        guideList=new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.guideview1, null);
        View view2 = inflater.inflate(R.layout.guideview2, null);
        View view3 = inflater.inflate(R.layout.guideview3, null);
        View view4 = inflater.inflate(R.layout.guideview4, null);
        guideList.add(view1);
        guideList.add(view2);
        guideList.add(view3);
        guideList.add(view4);

        pagerAdapter=new GuidePagerAdapter();
        pagerAdapter.setGuideList(guideList);
        mViewPager.setAdapter(pagerAdapter);

        for (int i = 0; i < guideList.size(); i++) {
            ImageView imageView=new ImageView(this);
            if (i==0){
                imageView.setImageResource(R.drawable.baidian);
            }else {
                imageView.setImageResource(R.drawable.huidian);
            }
            imageView.setPadding(14,0,14,0);

            mLinearLayout.addView(imageView);
        }

        mViewPager.addOnPageChangeListener(this);
    }

    public void GoMainActivity(View view) {
        SharedPreferences sharedPreferences=getSharedPreferences("test",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("guide",false);
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
private int tag=0;
    @Override
    public void onPageSelected(int position) {
        tag=position;
        int childCount = mLinearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView= (ImageView) mLinearLayout.getChildAt(i);
            if (position==i){
                imageView.setImageResource(R.drawable.baidian);
            }else {
                imageView.setImageResource(R.drawable.huidian);
            }
        }
    }
private boolean aBoolean=false;
    @Override
    public void onPageScrollStateChanged(int state) {
        Log.e(TAG,"state:"+state);

        if (tag==3){
            if (state==2){
                aBoolean=true;
            }
        }else {
            return;
        }

        if (aBoolean){
            Log.e(TAG,"LSQ");
        }
    }
}
