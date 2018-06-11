package com.haichenyi.myproject.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public class BaseMvpPresenter<T extends BaseView> implements BasePresenter<T>, LifecycleObserver {
    protected T baseView;
    private CompositeDisposable disposables;

    @Override
    public void attachView(T baseView) {
        this.baseView = baseView;
    }

    protected void addSubscribe(Disposable disposable) {
        if (null == disposables) {
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);
    }

    @Override
    public void detachView() {
        this.baseView = null;
        unSubscribe();
    }

    private void unSubscribe() {
        if (null != disposables) {
            disposables.clear();
            disposables = null;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.e("wz", "P--onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.e("wz", "P--onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.e("wz", "P--onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.e("wz", "P--onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.e("wz", "P--onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        detachView();
        Log.e("wz", "P--onDestroy");
    }

}
