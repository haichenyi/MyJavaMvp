package com.haichenyi.myproject;

import android.os.Bundle;

import com.haichenyi.myproject.base.BaseMvpActivity;
import com.haichenyi.myproject.contract.MainContract;
import com.haichenyi.myproject.presenter.MainPresenter;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.IView {

  @Override
  protected int getLayoutId(Bundle savedInstanceState) {
    return R.layout.activity_main;
  }

  @Override
  protected void initData() {
    super.initData();
    initToolbar(true, false, true).setMyTitle("主页").setMoreTitle("更多");
    basePresenter.loadData();
  }

  @Override
  protected void initInject() {
    getActivityComponent().inject(this);
  }
}
