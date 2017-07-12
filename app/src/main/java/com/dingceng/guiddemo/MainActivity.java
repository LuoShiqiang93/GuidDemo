package com.dingceng.guiddemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void exit(View view) {
        SharedPreferences sharedPreferences=getSharedPreferences("test",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login",false);
        editor.apply();
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
