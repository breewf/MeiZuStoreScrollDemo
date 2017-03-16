package com.ghy.meizustorescrolldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.iv_get_game)
    ImageView mIvGetGame;
    @Bind(R.id.btn_get_game)
    TextView mBtnGetGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mIvGetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityUpAnim(MainActivity.this, GameDetailActivity.class);
            }
        });

        mBtnGetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityUpAnim(MainActivity.this, GameDetailActivity.class);
            }
        });
    }

    public static void startActivityUpAnim(Activity mActivity, Class clazz) {
        mActivity.startActivity(new Intent(mActivity, clazz));
        mActivity.overridePendingTransition(R.anim.bottom_to_current, R.anim.current_to_top);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
