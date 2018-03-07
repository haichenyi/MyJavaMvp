package com.haichenyi.myproject.model.http;

/**
 * Author: 海晨忆.
 * Date: 2017/12/21
 * Desc: 网络请求状态码
 */
public interface HttpCode {
  /**
   * 成功.
   */
  int SUCCESS = 0;
  /**
   * 参数为空.
   */
  int NO_PARAMETER = 1;
  /**
   * 服务器错误.
   */
  int SERVER_ERR = 3;
}
