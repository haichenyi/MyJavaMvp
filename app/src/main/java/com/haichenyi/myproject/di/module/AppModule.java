package com.haichenyi.myproject.di.module;

import com.haichenyi.myproject.base.MyApplication;
import com.haichenyi.myproject.model.DataHelper;
import com.haichenyi.myproject.model.http.HttpHelper;
import com.haichenyi.myproject.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
@Module
public class AppModule {
  private MyApplication application;

  public AppModule(MyApplication application) {
    this.application = application;
  }

  @Provides
  @Singleton
  DataHelper provideDataHelper(HttpHelper httpHelper) {
    return new DataHelper(httpHelper);
  }

  @Provides
  @Singleton
  HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
    return retrofitHelper;
  }
}
