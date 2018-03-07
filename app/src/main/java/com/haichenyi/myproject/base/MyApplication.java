package com.haichenyi.myproject.base;

import android.app.Application;

import com.haichenyi.myproject.di.component.AppComponent;
import com.haichenyi.myproject.di.component.DaggerAppComponent;
import com.haichenyi.myproject.di.module.AppModule;
import com.haichenyi.myproject.di.module.HttpModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public class MyApplication extends Application {
  private static MyApplication instance;
  private static AppComponent appComponent;

  public static MyApplication getInstance() {
    return instance;
  }

  private void setInstance(MyApplication instance) {
    MyApplication.instance = instance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    setInstance(this);
    initLeakCanary();
  }

  /**
   * 初始化内存检测工具
   */
  private void initLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return;
    }
    LeakCanary.install(this);
  }
  /**
   * 获取AppComponent.
   *
   * @return AppComponent
   */
  public static synchronized AppComponent getAppComponent() {
    if (null == appComponent) {
      appComponent = DaggerAppComponent.builder()
          .appModule(new AppModule(getInstance()))
          .httpModule(new HttpModule())
          .build();
    }
    return appComponent;
  }
}
