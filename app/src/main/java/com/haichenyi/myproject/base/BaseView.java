package com.haichenyi.myproject.base;

import android.support.annotation.StringRes;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public interface BaseView {
  void showTipMsg(String msg);

  void showTipMsg(@StringRes int msg);

  void showLoading();

  void hideLoading();

  void invalidToken();

  void myFinish();
}
