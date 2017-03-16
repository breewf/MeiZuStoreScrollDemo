package com.ghy.meizustorescrolldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ghy.meizustorescrolldemo.utils.StatusBarUtils;

public class GameDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        StatusBarUtils.statusBarLightMode(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.current_to_bottom, R.anim.top_to_current);
    }

}
