package com.ghy.meizustorescrolldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.ghy.meizustorescrolldemo.utils.StatusBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameDetailActivity extends AppCompatActivity {

    @Bind(R.id.iv_arrow_down)
    ImageView mIvArrowDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        ButterKnife.bind(this);
        StatusBarUtils.statusBarLightMode(this);

        mIvArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.current_to_bottom, R.anim.top_to_current);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}
