package com.ghy.meizustorescrolldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghy.meizustorescrolldemo.utils.StatusBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.iv_get_music)
    ImageView mIvGetMusic;
    @Bind(R.id.btn_get_music)
    TextView mBtnGetMusic;

    @Bind(R.id.iv_get_game)
    ImageView mIvGetGame;
    @Bind(R.id.btn_get_game)
    TextView mBtnGetGame;

    @Bind(R.id.view_status_bar)
    View mViewStatusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        StatusBarUtils.statusBarLightMode(this);

        mIvGetMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBottomSheet();
            }
        });

        mBtnGetMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBottomSheet();
            }
        });

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

    private void checkBottomSheet() {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mBottomSheetDialog.getWindow() == null) return;
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.show();
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
