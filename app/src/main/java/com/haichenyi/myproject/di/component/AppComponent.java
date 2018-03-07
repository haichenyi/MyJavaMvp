package com.haichenyi.myproject.di.component;

import com.haichenyi.myproject.di.module.AppModule;
import com.haichenyi.myproject.di.module.HttpModule;
import com.haichenyi.myproject.model.DataHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
  DataHelper getDataHelper();
}
