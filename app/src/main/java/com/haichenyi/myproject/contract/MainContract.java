package com.haichenyi.myproject.contract;

import com.haichenyi.myproject.base.BasePresenter;
import com.haichenyi.myproject.base.BaseView;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public interface MainContract {
  interface IView extends BaseView{

  }
  interface Presenter extends BasePresenter<IView>{
    void loadData();
  }
}
