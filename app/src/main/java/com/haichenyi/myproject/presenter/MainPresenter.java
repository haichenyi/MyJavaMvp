package com.haichenyi.myproject.presenter;

import com.haichenyi.myproject.base.BaseMvpPresenter;
import com.haichenyi.myproject.base.MyApplication;
import com.haichenyi.myproject.contract.MainContract;
import com.haichenyi.myproject.model.DataHelper;
import com.haichenyi.myproject.model.http.HttpNoResult;
import com.haichenyi.myproject.model.http.MyRxUtils;
import com.haichenyi.myproject.model.http.MySubscriber;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public class MainPresenter extends BaseMvpPresenter<MainContract.IView>
    implements MainContract.Presenter {
  private DataHelper dataHelper;

  @Inject
  MainPresenter() {
    dataHelper = MyApplication.getAppComponent().getDataHelper();
  }

  @Override
  public void loadData() {
    addSubscribe(dataHelper.loginCode("134xxxxxxxx")
        .compose(MyRxUtils.toMain(Schedulers.io()))
        .subscribeWith(new MySubscriber<HttpNoResult>(baseView, true) {
          @Override
          public void onNext(HttpNoResult httpNoResult) {

          }
        }));
//    baseView.showTipMsg("加载数据");
  }
}
