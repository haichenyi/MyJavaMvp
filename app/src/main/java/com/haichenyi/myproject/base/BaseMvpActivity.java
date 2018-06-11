package com.haichenyi.myproject.base;

import android.arch.lifecycle.LifecycleObserver;

import javax.inject.Inject;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity {
    @Inject
    protected T basePresenter;

    @Override
    @SuppressWarnings("unchecked")
    protected void initView() {
        super.initView();
        initInject();
        if (basePresenter instanceof BaseMvpPresenter) {
            getLifecycle().addObserver((LifecycleObserver) basePresenter);
        }
        if (null != basePresenter) {
            basePresenter.attachView(this);
        }
    }

    protected abstract void initInject();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basePresenter = null;
    }
}
