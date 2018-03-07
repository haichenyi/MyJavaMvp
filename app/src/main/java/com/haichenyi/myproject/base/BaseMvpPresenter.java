package com.haichenyi.myproject.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public class BaseMvpPresenter<T extends BaseView> implements BasePresenter<T> {
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
}
