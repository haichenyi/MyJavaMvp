package com.haichenyi.myproject.utils;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.haichenyi.myproject.base.MyApplication;


/**
 * Author: 海晨忆.
 * Date: 2017/12/21
 * Desc: Toast工具类
 */
public final class ToastUtils {
  private static Toast toast;

  private ToastUtils() {
    throw new RuntimeException("工具类不允许创建对象");
  }

  @SuppressWarnings("all")
  private static void init() {
    if (toast == null) {
      toast = Toast.makeText(MyApplication.getInstance(), "", Toast.LENGTH_SHORT);
    }
  }

  public static void showTipMsg(String msg) {
    if (null == toast) {
      init();
    }
    toast.setText(msg);
    toast.show();
  }

  public static void showTipMsg(@StringRes int msg) {
    if (null == toast) {
      init();
    }
    toast.setText(msg);
    toast.show();
  }
}
