package com.ghy.meizustorescrolldemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ghy.meizustorescrolldemo.utils.AppUtils;

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

    private int screenHeight;
    private int marTopHeight = 300;//bottomSheet弹出距离屏幕顶部距离
    private int statusBarHeight;//statusBarHeight
    private float statusWeight;//状态栏高度占marTopHeight的权重

    private ImageView mIvArrowDown;
    private TextView mTvAppIntro;
    private TextView mTvAppIntroCopy;
    private TextView mInstallApp;
    private String tvAppIntroString;
    private float introLayoutHeight;//应用介绍所有文本高度
    private float introLayoutThreeHeight;//应用介绍三行文本高度

    private BottomSheetDialog mBottomSheetDialog;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        WindowManager wm = this.getWindowManager();
        screenHeight = wm.getDefaultDisplay().getHeight();
        statusBarHeight = AppUtils.dip2px(this, 25);
        //从正常弹出位置向上滑动到顶部 slideOffset从0--1.0
        //300-0 (0-1.0)每1.0高度为300
        statusWeight = (float) statusBarHeight / 300;
        Log.i("BottomSheet", "screenHeight-->>" + screenHeight);
        Log.i("BottomSheet", "statusBarHeight-->>" + statusBarHeight);
        Log.i("BottomSheet", "statusWeight-->>" + statusWeight);

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
        View contentView = getLayoutInflater().inflate(R.layout.bottom_sheet_layout, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mBottomSheetDialog.getWindow() == null) return;
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//去除状态栏黑色背景
        }
        mIvArrowDown = (ImageView) contentView.findViewById(R.id.iv_arrow_down);
        mTvAppIntro = (TextView) contentView.findViewById(R.id.tv_app_intro);
        mTvAppIntroCopy = (TextView) contentView.findViewById(R.id.tv_app_intro_copy);
        mInstallApp = (TextView) contentView.findViewById(R.id.tv_install_app);
        mBottomSheetDialog.setContentView(contentView);
        View view = mBottomSheetDialog.getWindow().findViewById(android.support.design.R.id.design_bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(view);
        mBottomSheetBehavior.setPeekHeight(screenHeight - marTopHeight);//设置BottomSheetDialog高度
        setBottomSheetCallback();//设置监听
        initClickListener();
        mBottomSheetDialog.show();
    }

    /**
     * 设置mBottomSheetBehavior监听
     */
    private void setBottomSheetCallback() {
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.i("BottomSheet", "newState-->>" + newState);
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {//折叠到底部隐藏
                    //以下两行解决使用向下拖拽的方法虽然使弹框消失了，但是残留遮罩效果
                    mBottomSheetDialog.dismiss();
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {//展开到顶部
                    mIvArrowDown.setImageResource(R.mipmap.arrow_down);
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {//正常弹出展开
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {//拖动过程中
                    mIvArrowDown.setImageResource(R.mipmap.icon_close);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("BottomSheet", "slideOffset-->>" + slideOffset);
                if (slideOffset >= (1 - statusWeight)) {
                    Log.i("BottomSheet", "滑到状态栏啦。。。。");
                } else {

                }
            }
        });
    }

    /**
     * 点击事件监听
     */
    private void initClickListener() {
        mIvArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomSheetDialog != null && mBottomSheetDialog.isShowing()) {
                    mBottomSheetDialog.dismiss();
                }
            }
        });
        tvAppIntroString = mTvAppIntro.getText().toString();
        mTvAppIntro.post(new Runnable() {
            @Override
            public void run() {
                introLayoutHeight = mTvAppIntro.getHeight();
                introLayoutThreeHeight = mTvAppIntroCopy.getHeight();
                setThreeLine();
            }
        });
        mTvAppIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTvAppIntro.getMaxLines() == 3) {
                    mTvAppIntro.setText(tvAppIntroString);
                    mTvAppIntro.setMaxLines(Integer.MAX_VALUE);
                } else {
                    setThreeLine();
                }
            }
        });
        mInstallApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "假的，假的，都是假的", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void startActivityUpAnim(Activity mActivity, Class clazz) {
        mActivity.startActivity(new Intent(mActivity, clazz));
        mActivity.overridePendingTransition(R.anim.bottom_to_current, R.anim.current_to_top);
    }

    /**
     * 设置应用介绍为三行内容数据
     *
     * @return
     */
    private void setThreeLine() {
        mTvAppIntro.setMaxLines(3);
        String threeLineText = getThreeLineString();
        SpannableString spannableString = new SpannableString(threeLineText + "更多");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.color_blue));
        StyleSpan styleSpan_B = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(styleSpan_B, threeLineText.length(), spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, threeLineText.length(), spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvAppIntro.setText(spannableString);
    }

    /**
     * 获取应用介绍前三行内容数据
     *
     * @return
     */
    private String getThreeLineString() {
        String stringText = "";
        //得到TextView的布局
        Layout layout = mTvAppIntro.getLayout();
        //得到TextView显示有多少行
        int lines = mTvAppIntro.getLineCount();
        //为了转换String 到 StringBuilder
        StringBuilder SrcStr = new StringBuilder(mTvAppIntro.getText().toString());
        for (int i = 0; i < 3; i++) {
            //使用getLineStart 和 getLineEnd 得到指定行的开始和结束的坐标，坐标范围是SrcStr整个字符串范围内。
            String lineStr = SrcStr.subSequence(layout.getLineStart(i), layout.getLineEnd(i)).toString();
            stringText += lineStr;
        }
        return stringText.substring(0, stringText.length() - 3) + "..";//去掉最后三个字，用来填充 ..和更多 二字
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
