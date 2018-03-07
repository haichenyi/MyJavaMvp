package com.haichenyi.myproject.base;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public interface BasePresenter<T extends BaseView> {
  void attachView(T baseView);

  void detachView();
}
