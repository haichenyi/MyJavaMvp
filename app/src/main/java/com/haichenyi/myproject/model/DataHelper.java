package com.haichenyi.myproject.model;

import com.haichenyi.myproject.model.http.HttpHelper;
import com.haichenyi.myproject.model.http.HttpNoResult;

import io.reactivex.Flowable;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public class DataHelper implements HttpHelper {
  private HttpHelper http;

  public DataHelper(HttpHelper http) {
    this.http = http;
  }

  @Override
  public Flowable<HttpNoResult> loginCode(String phone) {
    return http.loginCode(phone);
  }
}
