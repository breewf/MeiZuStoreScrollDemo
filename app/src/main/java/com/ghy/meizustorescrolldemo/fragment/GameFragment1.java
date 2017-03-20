package com.ghy.meizustorescrolldemo.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghy.meizustorescrolldemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameFragment1 extends Fragment {

    @Bind(R.id.tv_game_intro)
    TextView tvGameIntro;
    @Bind(R.id.tv_game_intro_copy)
    TextView tvGameIntroCopy;//计算三行的高度
    String tvGameIntroString;
    @Bind(R.id.up_down_layout)
    LinearLayout upDownLayout;

    private float introLayoutHeight;//应用介绍所有文本高度
    private float introLayoutThreeHeight;//应用介绍三行文本高度
    private float animHeight;//要平移的高度

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_fragment1, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvGameIntroString = tvGameIntro.getText().toString();
        tvGameIntro.post(new Runnable() {
            @Override
            public void run() {
                introLayoutHeight = tvGameIntro.getHeight();
                introLayoutThreeHeight = tvGameIntroCopy.getHeight();
                animHeight = introLayoutHeight - introLayoutThreeHeight;
                setThreeLine();
            }
        });
        tvGameIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvGameIntro.getMaxLines() == 3) {
                    tvGameIntro.setText(tvGameIntroString);
                    tvGameIntro.setMaxLines(Integer.MAX_VALUE);

//                    ObjectAnimator animator = ObjectAnimator.ofFloat(upDownLayout, "translationY", animHeight);
//                    animator.setDuration(600);
//                    animator.start();

                } else {
                    setThreeLine();

//                    ObjectAnimator animator = ObjectAnimator.ofFloat(upDownLayout, "translationY", -animHeight);
//                    animator.setDuration(600);
//                    animator.start();
                }
            }
        });


    }

    /**
     * 设置应用介绍为三行内容数据
     *
     * @return
     */
    private void setThreeLine() {
        tvGameIntro.setMaxLines(3);
        String threeLineText = getThreeLineString();
        SpannableString spannableString = new SpannableString(threeLineText + "更多");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(getActivity(), R.color.theme_color));
        StyleSpan styleSpan_B = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(styleSpan_B, threeLineText.length(), spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, threeLineText.length(), spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvGameIntro.setText(spannableString);
    }


    /**
     * 获取应用介绍前三行内容数据
     *
     * @return
     */
    private String getThreeLineString() {
        String stringText = "";
        //得到TextView的布局
        Layout layout = tvGameIntro.getLayout();
        //得到TextView显示有多少行
        int lines = tvGameIntro.getLineCount();
        //为了转换String 到 StringBuilder
        StringBuilder SrcStr = new StringBuilder(tvGameIntro.getText().toString());
        for (int i = 0; i < 3; i++) {
            //使用getLineStart 和 getLineEnd 得到指定行的开始和结束的坐标，坐标范围是SrcStr整个字符串范围内。
            String lineStr = SrcStr.subSequence(layout.getLineStart(i), layout.getLineEnd(i)).toString();
            stringText += lineStr;
        }
        return stringText.substring(0, stringText.length() - 3) + "..";//去掉最后三个字，用来填充 ..和更多 二字
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
