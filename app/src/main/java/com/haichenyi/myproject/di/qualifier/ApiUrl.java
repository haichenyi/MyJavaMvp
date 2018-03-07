package com.haichenyi.myproject.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiUrl {
}
