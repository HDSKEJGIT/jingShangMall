package com.hsh.dongzi.jinshang.ui.main.classfiy.search;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseActivity;
import com.hsh.dongzi.jinshang.model.main.classfiy.search.ResultSearchBean;
import com.hsh.dongzi.jinshang.model.main.classfiy.search.SearchKeyWordBean;
import com.hsh.dongzi.jinshang.presenter.main.classfiy.search.SearchResultPresenter;
import com.hsh.dongzi.jinshang.utils.LogUtil;
import com.hsh.dongzi.jinshang.utils.StringUtils;
import com.hsh.dongzi.jinshang.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchResultActivity extends BaseActivity<SearchResultPresenter> implements SearchResultView {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rl_clean)
    RelativeLayout rlClean;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rv_select_spec)
    RecyclerView rvSelectSpec;
    @BindView(R.id.rv_result_list)
    RecyclerView rvResultList;
    @BindView(R.id.iv_beck)
    ImageView ivBeck;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.iv_shop_name)
    ImageView ivShopName;
    @BindView(R.id.ll_shop_name)
    LinearLayout llShopName;
    @BindView(R.id.iv_shop_type)
    ImageView ivShopType;
    @BindView(R.id.iv_shop_picture)
    ImageView ivShopPicture;
    @BindView(R.id.tv_shop_standard)
    TextView tvShopStandard;
    @BindView(R.id.tv_shop_describe)
    TextView tvShopDescribe;
    @BindView(R.id.cl_shop_type)
    ConstraintLayout clShopType;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.ll_detail)
    LinearLayout llDetail;

    private String type;

    private String search;
    private List<ResultSearchBean> list = new ArrayList<>();
    private ResultSearchAdapter adapter;

    private String name;
    private int id;

    @Override
    protected void beforeView() {
        type = getIntent().getExtras().getString("type");
        LogUtil.i(type);
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initView() {
        //初始化 recycler（列表）
        initResultRecyc();

        if (!StringUtils.isEmpty(type)){
            if (type.equals("search")){
                //搜索的逻辑
                llSearch.setVisibility(View.VISIBLE);
                search = getIntent().getExtras().getString("search");
                if (!StringUtils.isEmpty(search)) {
                    etSearch.setText(search);
                    //检查输入框 true则请求接口
                    mPresenter.checkEdit();
                }
            }else {
                //详情的逻辑
                llDetail.setVisibility(View.VISIBLE);
                name = getIntent().getExtras().getString("name");
                if (!StringUtils.isEmpty(name)){
                    tvShopName.setText(name);
                }
                id = getIntent().getExtras().getInt("id");
            }
        }

    }

    @Override
    protected SearchResultPresenter createPresenter() {
        return new SearchResultPresenter(this);
    }

    @OnClick({R.id.rl_clean, R.id.tv_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_clean:
                mPresenter.checkEdit();
                break;
            case R.id.tv_search:
                if (!StringUtils.isEmpty(etSearch.getText().toString().trim())) {
                    etSearch.setText("");
                }
                break;
        }
    }

    @Override
    public String getSearch() {
        return etSearch.getText().toString().trim();
    }

    @Override
    public void searchSucceed(SearchKeyWordBean data) {
        list.clear();
        list.addAll(data.getPageInfo().getList());
        adapter.resetList(list);
    }

    private void initResultRecyc() {
        rvResultList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ResultSearchAdapter(this, list, R.layout.item_result_search);
        rvResultList.setAdapter(adapter);
        adapter.setListener(new ResultSearchAdapter.OnItemClickListener() {
            @Override
            public void numAdd(int position, String num) {
            }

            @Override
            public void numDel(int position, String num) {

            }

            @Override
            public void numClick(int position, String num) {

            }

            @Override
            public void addCartClick(int position, String num) {
                initDialog();
            }
        });
    }

    private void initDialog() {
        Dialog cartNumDialog = new Dialog(this, R.style.custom_window_dialog);
        LinearLayout layout = (LinearLayout) ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.shopcart_goodnum_edit, null);
        TextView shop_goods_cancel = layout.findViewById(R.id.shop_goods_cancel);
        TextView shop_goods_ok = layout.findViewById(R.id.shop_goods_ok);
        final EditText shop_goods_edit = layout.findViewById(R.id.shop_goods_edit);
        shop_goods_edit.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        shop_goods_cancel.setOnClickListener(v -> cartNumDialog.dismiss());
        shop_goods_ok.setOnClickListener(view -> {
            if (!StringUtils.isEmpty(shop_goods_edit.getText().toString())) {//判空

            } else {
                ToastUtil.showToastSafe("请输入您要购买的数量！");
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
