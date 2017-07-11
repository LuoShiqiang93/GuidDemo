package com.dingceng.guiddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WoComeActivity extends AppCompatActivity {

    private static final int GO_GUIDE = 0;
    private static final int GO_HOME = 1;
    private static final int LOGIN=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo_come);

        initLoad();
    }

    private void initLoad() {
        SharedPreferences sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
        boolean guide = sharedPreferences.getBoolean("guide", true);
        boolean login = sharedPreferences.getBoolean("login", false);
        if (!login) {
            if (!guide) {
                handler.sendEmptyMessageDelayed(GO_HOME, 2000);
            } else {
                handler.sendEmptyMessageDelayed(GO_GUIDE, 2000);

            }
        }else {
            handler.sendEmptyMessageDelayed(LOGIN,2000);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_GUIDE:
                    goGuide();
                    break;
                case GO_HOME:
                    goHome();
                    break;
                case LOGIN:
                    goMain();
                    break;
            }
        }
    };

    private void goMain() {
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goHome() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goGuide() {
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
        finish();
    }
}
