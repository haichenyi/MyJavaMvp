package com.haichenyi.myproject.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haichenyi.myproject.utils.ToastUtils;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public abstract class BaseFragment extends SupportFragment implements BaseView,
    View.OnClickListener {
  protected boolean isInit;
  private View rootView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    int layoutRes = layoutRes();
    if (0 != layoutRes) {
      return inflater.inflate(layoutRes, null);
    } else {
      return super.onCreateView(inflater, container, savedInstanceState);
    }
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    rootView = view;
  }

  @Override
  public void onLazyInitView(@Nullable Bundle savedInstanceState) {
    super.onLazyInitView(savedInstanceState);
    isInit = true;
    init();
  }

  protected <T extends View> T findViewById(@IdRes int id) {
    return rootView.findViewById(id);
  }

  /**
   * 设置点击事件.
   *
   * @param ids 被点击View的ID
   * @return {@link BaseFragment}
   */
  public BaseFragment setOnClick(@IdRes int... ids) {
    for (int id : ids) {
      rootView.findViewById(id).setOnClickListener(this);
    }
    return this;
  }

  /**
   * 设置点击事件.
   *
   * @param views 被点击View的ID
   * @return {@link BaseFragment}
   */
  public BaseFragment setOnClick(View... views) {
    for (View view : views) {
      view.setOnClickListener(this);
    }
    return this;
  }

  protected abstract void init();

  @Override
  public void onDestroy() {
    rootView = null;
    super.onDestroy();
  }

  protected abstract int layoutRes();

  @Override
  public void showTipMsg(String msg) {
    ToastUtils.showTipMsg(msg);
  }

  @Override
  public void showTipMsg(int msg) {
    ToastUtils.showTipMsg(msg);
  }

  @Override
  public void showLoading() {
    BaseActivity activity = (BaseActivity) getActivity();
    /*if (activity instanceof BaseMvpActivity) {
      activity.showLoading();
    }*/
  }

  @Override
  public void hideLoading() {
    BaseActivity activity = (BaseActivity) getActivity();
    /*if (activity instanceof BaseMvpActivity) {
      activity.hideLoading();
    }*/
  }

  @Override
  public void invalidToken() {
    BaseActivity activity = (BaseActivity) getActivity();
    /*if (activity instanceof BaseMvpActivity) {
      activity.invalidToken();
    }*/
  }

  @Override
  public void onClick(View v) {
  }

  @Override
  public void myFinish() {
    onBackPressedSupport();
  }
}

