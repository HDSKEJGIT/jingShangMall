package com.hsh.dongzi.jinshang;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hsh.dongzi.jinshang.base.mvp.BaseActivity;
import com.hsh.dongzi.jinshang.base.mvp.BasePresenter;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;


public class TestActivity extends BaseActivity {
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected int layoutRes() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTransparent(this);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick(R.id.btn)
   public void onClick(View view){
        switch (view.getId()){
            case R.id.btn:
                Toast.makeText(this,"当前点击了按钮",Toast.LENGTH_LONG).show();
                forward(HomeActivity.class);
                break;
        }
    }
}
