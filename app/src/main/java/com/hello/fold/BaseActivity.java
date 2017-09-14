package com.hello.fold;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * <p>
 * 基类
 * </p>
 * Created by Administrator on 2017/6/13 0013.
 */
@SuppressWarnings("ALL")
public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        ButterKnife.bind(this);
        initUi();
        initDatas();
        initListener();
    }

    /**
     * 页面绑定
     */
    protected abstract int initContentView();

    /**
     * 初始化UI
     */
    protected abstract void initUi();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    /**
     * 初始化监听事件
     */
    protected abstract void initListener();

    /**
     * 配置toolbar
     *
     * @param allowBack                 是否需要返回
     * @param title                      标题
     * @param allowOperate              是否需要操作
     * @param listener                  点击事件
     */
    protected void configToolbar(boolean allowBack, String title, boolean allowOperate, View.OnClickListener listener) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        // 标题
        TextView txtTitle = (TextView) toolbar.findViewById(R.id.common_toolbar_txtTitle);
        txtTitle.setText(title);
        if (allowBack) {
            toolbar.setNavigationIcon(R.drawable.common_btn_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        if (allowOperate) {
            TextView txtOperate = (TextView) toolbar.findViewById(R.id.common_toolbar_txtOperate);
            txtOperate.setVisibility(View.VISIBLE);
            txtOperate.setOnClickListener(listener);
        }
    }

    /**
     * 配置toolbar
     *
     * @param allowBack    是否需要返回
     * @param title        标题
     * @param allowOperate 是否需要操作
     * @param listener     点击事件
     */
    protected void configToolbar(boolean allowBack, int titleId, boolean allowOperate, View.OnClickListener listener) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        // 标题
        TextView txtTitle = (TextView) toolbar.findViewById(R.id.common_toolbar_txtTitle);
        txtTitle.setText(titleId);
        if (allowBack) {
            toolbar.setNavigationIcon(R.drawable.common_btn_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        if (allowOperate) {
            TextView txtOperate = (TextView) toolbar.findViewById(R.id.common_toolbar_txtOperate);
            txtOperate.setVisibility(View.VISIBLE);
            txtOperate.setOnClickListener(listener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
