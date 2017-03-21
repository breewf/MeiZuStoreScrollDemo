package com.ghy.meizustorescrolldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NestedScrollView.OnScrollChangeListener {

    @Bind(R.id.iv_arrow_down)
    ImageView mIvArrowDown;

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
    @Bind(R.id.app_scroll_layout)
    NestedScrollView mAppScrollLayout;

    private BottomSheetDialog mBottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        mAppScrollLayout.setOnScrollChangeListener(this);//滚动监听

        mIvArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomSheetDialog != null && mBottomSheetDialog.isShowing()) {
                    mBottomSheetDialog.dismiss();
                }
            }
        });

        mIvGetMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheet();
            }
        });

        mBtnGetMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheet();
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

    private void showBottomSheet() {
        if (mBottomSheetDialog == null) mBottomSheetDialog = new BottomSheetDialog(this);
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

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Log.i("scroll", "scrollY-->>" + scrollY);
    }

}
