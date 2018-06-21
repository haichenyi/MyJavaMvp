package com.haichenyi.myproject.model.http;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Author: 海晨忆.
 * Date: 2017/12/21
 * Desc: 网络拦截器
 */
public class MyHttpInterceptor implements Interceptor {
  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();//获取request对象
    RequestBody body = request.body();//获取body
    if (null == body) {
      return chain.proceed(request);
    }
    if (body instanceof FormBody) {
      Response proceed = chain.proceed(formatRequest(request));
      ResponseBody responseBody = proceed.body();
      if (null != responseBody) {
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        String responseStr = buffer.clone().readString(Charset.forName("UTF-8"));
//        LogUtils.d(Constants.HTTP_LOG, responseStr);
      }
      return proceed;
    } else {
      return chain.proceed(request);
    }
  }

  private Request formatRequest(Request request) {
    FormBody formBody = (FormBody) request.body();//获取body
    if (null == formBody) {
      return request;
    }
    int size = formBody.size();
    ArrayMap<String, String> arrayMap = new ArrayMap<>();
    for (int i = 0; i < size; i++) {
      arrayMap.put(formBody.name(i), formBody.value(i));
    }
//    arrayMap.put("padId", Cache.padId);//到时候传衣柜id，现在随便传
    String param = new Gson().toJson(arrayMap);
    param = param.replace("\"[", "[").replace("]\"", "]")
        .replace("\"{", "{").replace("}\"", "}")
        .replace("\\", "");
    Log.e("wz", request.url().toString() + "\n" + param);
    return new Request.Builder()
        .url(request.url())
        .headers(request.headers())
        .post(new FormBody.Builder()
            .add("json", param)
            .build())
        .build();
  }
}
