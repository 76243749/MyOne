package com.myandroid.myone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class DefaultActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            );

            // 标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效,需要去掉标题
            //requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        handler.sendEmptyMessageDelayed(0,3000);

    }

    public void getHome(){
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        String uid = pref.getString("uid","");
        if (uid.equals("")){
            Intent intent = new Intent(DefaultActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(DefaultActivity.this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
