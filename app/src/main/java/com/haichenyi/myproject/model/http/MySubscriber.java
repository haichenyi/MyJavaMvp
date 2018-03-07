package com.haichenyi.myproject.model.http;


import com.haichenyi.myproject.base.BaseView;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Author: 海晨忆.
 * Date: 2017/12/21
 * Desc:
 */
public abstract class MySubscriber<T> extends ResourceSubscriber<T> {
  private BaseView baseView;
  private boolean showLoading;

  public MySubscriber(BaseView baseView) {
    this.baseView = baseView;
  }

  public MySubscriber(BaseView baseView, boolean showLoading) {
    this.baseView = baseView;
    this.showLoading = showLoading;
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (null != baseView && showLoading) {
      baseView.showLoading();
    }
  }

  @Override
  public void onError(Throwable t) {
    if (null == baseView) {
      return;
    }
    baseView.hideLoading();
    if (t instanceof ApiException) {
      ApiException apiException = (ApiException) t;
      switch (apiException.getCode()) {
        case HttpCode.NO_PARAMETER:
          baseView.showTipMsg("参数为空");
          break;
        case HttpCode.SERVER_ERR:
          baseView.showTipMsg("服务器错误");
          break;
        default:
          break;
      }
    }
  }

  @Override
  public void onComplete() {
    if (null != baseView) {
      baseView.hideLoading();
    }
  }
}
