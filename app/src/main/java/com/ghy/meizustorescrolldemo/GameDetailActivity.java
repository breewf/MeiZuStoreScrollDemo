package com.ghy.meizustorescrolldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ghy.meizustorescrolldemo.fragment.GameFragment1;
import com.ghy.meizustorescrolldemo.fragment.GameFragment2;
import com.ghy.meizustorescrolldemo.fragment.GameFragment3;
import com.ghy.meizustorescrolldemo.fragment.GameFragment4;
import com.ghy.meizustorescrolldemo.utils.StatusBarUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameDetailActivity extends AppCompatActivity implements OnTabSelectListener {


    @Bind(R.id.btn_install)
    TextView btnInstall;

    private String[] mTitles = {"详情", "评论", "资讯", "论坛"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private GamePagerAdapter mPagerAdapter;

    @Bind(R.id.iv_arrow_down)
    ImageView mIvArrowDown;
    @Bind(R.id.game_tab_layout)
    SlidingTabLayout mGameTabLayout;
    @Bind(R.id.game_viewpager)
    ViewPager mGameViewPager;

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

        btnInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GameDetailActivity.this, "然而并没有安装，哈哈哈", Toast.LENGTH_SHORT).show();
            }
        });

        GameFragment1 gameFragment1 = new GameFragment1();
        GameFragment2 gameFragment2 = new GameFragment2();
        GameFragment3 gameFragment3 = new GameFragment3();
        GameFragment4 gameFragment4 = new GameFragment4();
        mFragments.add(gameFragment1);
        mFragments.add(gameFragment2);
        mFragments.add(gameFragment3);
        mFragments.add(gameFragment4);

        //设置adapter
        mPagerAdapter = new GamePagerAdapter(getSupportFragmentManager());
        mGameViewPager.setAdapter(mPagerAdapter);
        //设置关联
        mGameTabLayout.setViewPager(mGameViewPager);
        mGameTabLayout.setOnTabSelectListener(GameDetailActivity.this);
        mGameViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changePage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onTabSelect(int position) {
        changePage(position);
    }

    @Override
    public void onTabReselect(int position) {

    }

    private void changePage(int position) {
        switch (position){
            case 0:
                btnInstall.setText("安装");
                break;
            case 1:
                btnInstall.setText("安装后评论");
                break;
            case 2:
                btnInstall.setText("发文章");
                break;
            case 3:
                btnInstall.setText("发帖子");
                break;
        }
    }

    /**
     * ViewPager适配器
     */
    private class GamePagerAdapter extends FragmentPagerAdapter {

        public GamePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
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
