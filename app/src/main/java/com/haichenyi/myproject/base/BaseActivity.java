package com.haichenyi.myproject.base;

import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.haichenyi.myproject.R;
import com.haichenyi.myproject.di.component.ActivityComponent;
import com.haichenyi.myproject.di.component.DaggerActivityComponent;
import com.haichenyi.myproject.di.module.ActivityModule;
import com.haichenyi.myproject.utils.ToastUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author: 海晨忆
 * Date: 2018/2/23
 * Desc:
 */
public abstract class BaseActivity extends SupportActivity implements BaseView, View.OnClickListener {
  private AlertDialog loadingDialog;
  /**
   * 保存当前activity对象，在OnCreate里面添加，记得在OnDestroy里面移除
   * 有什么用呢？
   * 比方说有一个需求，让你在任意位置弹出对话框，弹对话框又需要一个context对象，这个时候，
   * 你就只用传当前list的最上层的activity对象就可以了
   * 当然还有其他需求
   */
  public static List<BaseActivity> activities = new ArrayList<>();
  private Toolbar toolbar;
  private TextView tvToolbarTitle;
  private TextView tvToolbarRight;
  private TextView tvBack;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activities.add(this);
    //强制竖屏(不强制加)
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    int layoutId = getLayoutId(savedInstanceState);
    View inflate = getLayoutInflater().inflate(R.layout.activity_base, toolbar, false);
    LinearLayout rootLinearLayout = inflate.findViewById(R.id.ll_layout_base_activity);
    //没有布局的时候传0
    if (0 == layoutId) {
      setContentView(rootLinearLayout);
    } else {
      View rootView = getLayoutInflater().inflate(layoutId, rootLinearLayout, true);
      setContentView(rootView);
    }
    stateBar();
    initView();
    initData();
    setOnClick(R.id.tv_back_base_activity);
  }

  public BaseActivity setTitles(CharSequence title) {
    tvToolbarTitle.setText(title);
    return this;
  }

  /**
   * 初始化toolbar的内容
   * @param isShowToolbar 是否显示toolbar
   * @param isShowBack 是否显示左边的TextView
   * @param isShowMore 是否显示右边的TextView
   * @return 当前activity对象，可以连点
   */
  protected BaseActivity initToolbar(boolean isShowToolbar, boolean isShowBack,
                                     boolean isShowMore) {
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    if (null != actionBar) {
      if (isShowToolbar) {
        actionBar.show();
        tvBack = findViewById(R.id.tv_back_base_activity);
        TextView textView = findViewById(R.id.tv_right_base_activity);
        if (null != tvBack && null != textView) {
          tvBack.setVisibility(isShowBack ? View.VISIBLE : View.INVISIBLE);
          textView.setVisibility(isShowMore ? View.VISIBLE : View.INVISIBLE);
        }
      } else {
        actionBar.hide();
      }
    }
    return this;
  }

  public BaseActivity setToolbarBack(int colorId) {
    toolbar.setBackgroundColor(getResources().getColor(colorId));
    return this;
  }

  @SuppressWarnings("unused")
  public BaseActivity setMyTitle(String title) {
    tvToolbarTitle.setText(title);
    return this;
  }

  public BaseActivity setMyTitle(@StringRes int stringId) {
    tvToolbarTitle.setText(stringId);
    return this;
  }

  public void setMoreTitle(String moreTitle) {
    tvToolbarRight.setText(moreTitle);
  }

  public BaseActivity setMoreTitle(@StringRes int stringId) {
    tvToolbarRight.setText(stringId);
    return this;
  }

  /**
   * 设置左边内容.
   *
   * @param leftTitle 内容
   * @return {@link BaseActivity}
   */
  public BaseActivity setLeftTitle(String leftTitle) {
    if (tvBack != null) {
      tvBack.setBackground(null);
      tvBack.setText(leftTitle);
    }
    return this;
  }

  /**
   * 设置左边内容.
   *
   * @param leftTitle 内容
   */
  public void setLeftTitle(@StringRes int leftTitle) {
    if (tvBack != null) {
      tvBack.setBackground(null);
      tvBack.setText(leftTitle);
    }
  }

  @SuppressWarnings("unused")
  protected BaseActivity setMoreBackground(int resId) {
    tvToolbarRight.setBackgroundResource(resId);
    return this;
  }

  /**
   * 设置点击事件.
   *
   * @param ids 被点击View的ID
   * @return {@link BaseActivity}
   */
  public BaseActivity setOnClick(@IdRes int... ids) {
    View view;
    for (int id : ids) {
      view = findViewById(id);
      if (null != view) {
        view.setOnClickListener(this);
      }
    }
    return this;
  }

  /**
   * 设置点击事件.
   *
   * @param views 被点击View
   * @return {@link BaseActivity}
   */
  public BaseActivity setOnClick(View... views) {
    for (View view : views) {
      view.setOnClickListener(this);
    }
    return this;
  }

  /**
   * 获取当前布局对象
   *
   * @param savedInstanceState 这个是当前activity保存的数据，最常见的就是横竖屏切换的时候，
   *                           数据丢失问题
   * @return 当前布局的int值
   */
  protected abstract int getLayoutId(Bundle savedInstanceState);

  @Override
  protected void onDestroy() {
    activities.remove(this);
    super.onDestroy();
  }

  protected void initData() {
  }

  protected void initView() {
    toolbar = findViewById(R.id.toolbar_base_activity);
    tvToolbarTitle = findViewById(R.id.tv_title_base_activity);
    tvToolbarRight = findViewById(R.id.tv_right_base_activity);
  }

  /**
   * 设置状态栏背景颜色，不能改变状态栏内容的颜色
   */
  private void stateBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
    SystemBarTintManager tintManager = new SystemBarTintManager(this);
    tintManager.setStatusBarTintEnabled(true);
    tintManager.setNavigationBarTintEnabled(true);
    tintManager.setTintColor(Color.parseColor("#000000"));
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.tv_back_base_activity:
        onBackPressedSupport();
        break;
      default:
        break;
    }
  }

  /**
   * Toast 提示用户
   *
   * @param msg 提示内容String
   */
  @Override
  public void showTipMsg(String msg) {
    ToastUtils.showTipMsg(msg);
  }

  /**
   * Toast 提示用户
   *
   * @param msg 提示内容res目录下面的String的int值
   */
  @Override
  public void showTipMsg(int msg) {
    ToastUtils.showTipMsg(msg);
  }

  /**
   * 网络请求的时候显示正在加载的对话框
   */
  @Override
  public void showLoading() {
    if (null == loadingDialog) {
      loadingDialog = new AlertDialog.Builder(this).setView(new ProgressBar(this)).create();
      loadingDialog.setCanceledOnTouchOutside(false);
      Window window = loadingDialog.getWindow();
      if (null != window) {
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      }
    }
    if (!loadingDialog.isShowing()) {
      loadingDialog.show();
    }
  }

  /**
   * 网络请求完成时隐藏加载对话框
   */
  @Override
  public void hideLoading() {
    if (null != loadingDialog) {
      if (loadingDialog.isShowing()) {
        loadingDialog.dismiss();
      }
      loadingDialog = null;
    }
  }

  @Override
  public void invalidToken() {
    //用于检测你当前用户的token是否有效，无效就返回登录界面，具体的业务逻辑你自己实现
    //如果需要做到实时检测，推荐用socket长连接，每隔10秒发送一个验证当前登录用户token是否过期的请求
  }

  /**
   * Finish当前页面，最好实现onBackPressedSupport()，这个方法会有一个退栈操作，
   * 开源框架实现的，我们不用管
   */
  @Override
  public void myFinish() {
    onBackPressedSupport();
  }

  @Override
  public void onBackPressedSupport() {
    super.onBackPressedSupport();
  }

  protected ActivityComponent getActivityComponent() {
    return DaggerActivityComponent.builder()
        .appComponent(MyApplication.getAppComponent())
        .activityModule(new ActivityModule())
        .build();
  }
}
