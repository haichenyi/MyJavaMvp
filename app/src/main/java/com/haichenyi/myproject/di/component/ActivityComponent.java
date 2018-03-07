package com.haichenyi.myproject.di.component;

import com.haichenyi.myproject.MainActivity;
import com.haichenyi.myproject.di.module.ActivityModule;
import com.haichenyi.myproject.di.scope.ActivityScope;

import dagger.Component;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  void inject(MainActivity mainActivity);
}
