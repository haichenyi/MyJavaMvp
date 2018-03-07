package com.haichenyi.myproject.model.http.api;

import com.haichenyi.myproject.model.http.HttpNoResult;
import com.haichenyi.myproject.model.http.ProtocolHttp;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:网络请求接口api
 */
public interface HttpApi {
  /**
   * 登录时获取验证码.
   *
   * @param phone 手机号
   * @return {"code":0}
   */
  @FormUrlEncoded
  @POST(ProtocolHttp.METHOD_LOGIN_CODE)
  Flowable<HttpNoResult> loginCode(@Field("phone") String phone);
}
