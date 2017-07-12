package com.dingceng.guiddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements OnPageChangeListener,OnTouchListener{
    private static final String TAG = "GuideActivity";
    private List<View> guideList;
    private ViewPager mViewPager;
    private GuidePagerAdapter pagerAdapter;
    private LinearLayout mLinearLayout;

    private int currentItem;

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
        mViewPager.setOnTouchListener(this);
    }

    public void GoMainActivity() {
        SharedPreferences sharedPreferences=getSharedPreferences("test",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("guide",false);
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        currentItem=position;
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

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private float startX = 0;
    private float endX;
    private boolean aBoolean=true;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=event.getX();
                break;
            case MotionEvent.ACTION_UP:
                endX=event.getX();
                if (currentItem==(guideList.size()-1)&&(startX-endX)>0&&aBoolean){
                    Log.e(TAG,"LSQ");
                    aBoolean=false;
                    GoMainActivity();
                }
                break;
        }
        return false;
    }
}
