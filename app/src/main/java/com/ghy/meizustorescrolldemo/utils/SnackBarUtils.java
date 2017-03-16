package com.ghy.meizustorescrolldemo.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghy.meizustorescrolldemo.R;


/**
 * Created by GHY on 2016/12/20.
 * Desc: Snackbar工具类
 */

public class SnackBarUtils {

    public static final int Info = 1;
    public static final int Confirm = 2;
    public static final int Warning = 3;
    public static final int Alert = 4;


    public static int red = 0xfff44336;
    public static int green = 0xff4caf50;
    public static int blue = 0xff2195f3;
    public static int orange = 0xffffc107;

    public interface OnSnackBarClickListener {
        void onClick();
    }

    /**
     * 显示一个SnackBar
     *
     * @param view
     * @param content
     */
    public static void showSnackBar(View view, String content) {
        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 显示一个SnackBar
     *
     * @param view
     * @param content
     */
    public static void showSnackBarLong(View view, String content) {
        Snackbar.make(view, content, Snackbar.LENGTH_LONG).show();
    }

    /**
     * 显示一个SnackBar
     *
     * @param view
     * @param content
     * @param actionString
     * @param listener
     */
    public static void showSnackBar(View view, String content, String actionString, final OnSnackBarClickListener listener) {

        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).setAction(actionString, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onClick();
            }
        }).show();
    }

    /**
     * 显示一个SnackBar
     *
     * @param view
     * @param content
     * @param actionString
     * @param listener
     */
    public static void showSnackBarLong(View view, String content, String actionString, final OnSnackBarClickListener listener) {

        Snackbar.make(view, content, Snackbar.LENGTH_LONG).setAction(actionString, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onClick();
            }
        }).show();
    }

    /**
     * 显示一个SnackBar
     *
     * @param view
     * @param content
     * @param actionString
     * @param color
     * @param listener
     */
    public static void showSnackBarCustom(View view, String content, String actionString, int color, final OnSnackBarClickListener listener) {

        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).setActionTextColor(color)
                .setAction(actionString, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) listener.onClick();
                    }
                }).show();
    }

    /**
     * 显示一个SnackBar
     *
     * @param view
     * @param content
     * @param actionString
     * @param duration
     * @param color
     * @param listener
     */
    public static void showSnackBarCustom(View view, String content, String actionString, int duration, int color, final OnSnackBarClickListener listener) {

        Snackbar.make(view, content, Snackbar.LENGTH_INDEFINITE).setDuration(duration).setActionTextColor(color)
                .setAction(actionString, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) listener.onClick();
                    }
                }).show();
    }

    /**
     * 默认短显示Snackbar
     *
     * @param view
     * @param message
     * @return
     */
    public static Snackbar showSnackbarDefault(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        setSnackbarColor(snackbar, Color.parseColor("#ffffff"), Color.parseColor("#fc8825"));
        return snackbar;
    }

    /**
     * 默认长显示Snackbar
     *
     * @param view
     * @param message
     * @return
     */
    public static Snackbar showSnackbarLongDefault(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        setSnackbarColor(snackbar, Color.parseColor("#ffffff"), Color.parseColor("#fc8825"));
        return snackbar;
    }

    /**
     * 短显示Snackbar，自定义颜色
     *
     * @param view
     * @param message
     * @param messageColor
     * @param backgroundColor
     * @return
     */
    public static Snackbar showSnackbar(View view, String message, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        setSnackbarColor(snackbar, messageColor, backgroundColor);
        return snackbar;
    }

    /**
     * 长显示Snackbar，自定义颜色
     *
     * @param view
     * @param message
     * @param messageColor
     * @param backgroundColor
     * @return
     */
    public static Snackbar showSnackbarLong(View view, String message, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        setSnackbarColor(snackbar, messageColor, backgroundColor);
        return snackbar;
    }

    /**
     * 自定义时常显示Snackbar，自定义颜色
     *
     * @param view
     * @param message
     * @param messageColor
     * @param backgroundColor
     * @return
     */
    public static Snackbar indefiniteSnackbar(View view, String message, int duration, int messageColor, int backgroundColor) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        setSnackbarColor(snackbar, messageColor, backgroundColor);
        return snackbar;
    }

    /**
     * 短显示Snackbar，可选预设类型
     *
     * @param view
     * @param message
     * @param type
     * @return
     */
    public static Snackbar showSnackbar(View view, String message, int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        switchType(snackbar, type);
        return snackbar;
    }

    /**
     * 长显示Snackbar，可选预设类型
     *
     * @param view
     * @param message
     * @param type
     * @return
     */
    public static Snackbar showSnackbarLong(View view, String message, int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        switchType(snackbar, type);
        return snackbar;
    }

    /**
     * 自定义时常显示Snackbar，可选预设类型
     *
     * @param view
     * @param message
     * @param type
     * @return
     */
    public static Snackbar indefiniteSnackbar(View view, String message, int duration, int type) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setDuration(duration);
        switchType(snackbar, type);
        return snackbar;
    }

    //选择预设类型
    private static void switchType(Snackbar snackbar, int type) {
        switch (type) {
            case Info:
                setSnackbarColor(snackbar, blue);
                break;
            case Confirm:
                setSnackbarColor(snackbar, green);
                break;
            case Warning:
                setSnackbarColor(snackbar, orange);
                break;
            case Alert:
                setSnackbarColor(snackbar, Color.YELLOW, red);
                break;
        }
    }

    /**
     * 设置Snackbar背景颜色
     *
     * @param snackbar
     * @param backgroundColor
     */
    public static void setSnackbarColor(Snackbar snackbar, int backgroundColor) {
        View view = snackbar.getView();
        if (view != null) {
            view.setBackgroundColor(backgroundColor);
        }
    }

    /**
     * 设置Snackbar文字和背景颜色
     *
     * @param snackbar
     * @param messageColor
     * @param backgroundColor
     */
    public static void setSnackbarColor(Snackbar snackbar, int messageColor, int backgroundColor) {
        View view = snackbar.getView();
        view.setBackgroundColor(backgroundColor);
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(messageColor);
    }

    /**
     * 向Snackbar中添加view
     *
     * @param snackbar
     * @param layoutId
     * @param index    新加布局在Snackbar中的位置
     */
    public static void snackbarAddView(Snackbar snackbar, int layoutId, int index) {
        View snackbarview = snackbar.getView();
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarview;

        View add_view = LayoutInflater.from(snackbarview.getContext()).inflate(layoutId, null);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.gravity = Gravity.CENTER_VERTICAL;

        snackbarLayout.addView(add_view, index, p);
    }

}
