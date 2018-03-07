package com.haichenyi.myproject.model.http;

import com.haichenyi.myproject.model.http.api.HttpApi;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc: 网络接口Retrofit实现
 */
public class RetrofitHelper implements HttpHelper {
  private HttpApi httpApi;

  @Inject
  RetrofitHelper(HttpApi httpApi) {
    this.httpApi = httpApi;
  }

  @Override
  public Flowable<HttpNoResult> loginCode(String phone) {
    return httpApi.loginCode(phone);
  }
}
