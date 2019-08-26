package com.hsh.dongzi.jinshang.ui.main.classfiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.base.mvp.BaseFragment;
import com.hsh.dongzi.jinshang.model.main.classfiy.ClassFiyBean;
import com.hsh.dongzi.jinshang.model.main.classfiy.DetailBean;
import com.hsh.dongzi.jinshang.model.main.classfiy.OutLineBean;
import com.hsh.dongzi.jinshang.presenter.main.classfiy.ClassFiyPresenter;
import com.hsh.dongzi.jinshang.ui.main.classfiy.search.SearchResultActivity;
import com.hsh.dongzi.jinshang.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClassFiyFragment extends BaseFragment<ClassFiyPresenter> implements ClassFiyView {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.rv_class)
    RecyclerView rvClass;
    @BindView(R.id.elv_list)
    ExpandableListView elvList;

    private List<OutLineBean> outLineBeanList = new ArrayList<>();
    private ClassFiyAdapter classFiyAdapter;
    private int parentID;

    private List<DetailBean> detailBeanList = new ArrayList<>();
    private ClassListAdapter classListAdapter;

    @Override
    protected int layoutRes() {
        return R.layout.fragment_class;
    }

    @Override
    protected ClassFiyPresenter createPresenter() {
        return new ClassFiyPresenter(this);
    }

    @Override
    protected void initView() {
        initRecycler();
        initExpandable();
        mPresenter.getClassData(this);
    }

    @OnClick({R.id.tv_search})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tv_search:
                mPresenter.checkAndGoSearch();
                break;
        }
    }

    @Override
    public String getSearch() {
        return etSearch.getText().toString().trim();
    }

    @Override
    public void goSearch(String content) {
        //跳转到分类搜索的页面 -- 进入SearchResultActivity  根据 type来区分是搜索 还是 详情
        Intent intent = new Intent(mActivity, SearchResultActivity.class);
        intent.putExtra("type","search");
        intent.putExtra("search",content);
        mActivity.forward(intent);
    }

    @Override
    public void reuqestSucceed(ClassFiyBean data) {
        LogUtil.i("分类数据请求成功");
        outLineBeanList.clear();
        outLineBeanList.addAll(data.getOutline());
        if (outLineBeanList.size()>0){
            outLineBeanList.get(0).setSelect(true);
        }
        classFiyAdapter.resetList(outLineBeanList);

        detailBeanList.clear();
        detailBeanList.addAll(data.getDetail());
        classListAdapter.resetList(detailBeanList,parentID);
    }

    @Override
    public void requestErroy() {
        LogUtil.i("请求失败");
    }

    private void initRecycler(){
        classFiyAdapter = new ClassFiyAdapter(mActivity,outLineBeanList,R.layout.item_class_fiy_type);
        rvClass.setLayoutManager(new LinearLayoutManager(mActivity));
        rvClass.setAdapter(classFiyAdapter);
        classFiyAdapter.setItemListener(position -> {
            parentID = position;
            for (int i = 0; i < outLineBeanList.size(); i++) {
                outLineBeanList.get(i).setSelect(false);
            }
            outLineBeanList.get(position).setSelect(true);
            classFiyAdapter.resetList(outLineBeanList);
            updateExpandable();
        });

    }

    private void initExpandable(){
        classListAdapter = new ClassListAdapter(mActivity,detailBeanList,parentID);
        elvList.setGroupIndicator(null);
        elvList.setAdapter(classListAdapter);
        elvList.setOnChildClickListener((expandableListView, view, groupPosition, childPosition, id) -> {
            //点击子商品触发
            //跳转到分类详情页面 -- 进入SearchResultActivity  根据 type来区分是搜索 还是 详情
            Intent intent = new Intent(mActivity, SearchResultActivity.class);
            intent.putExtra("type","detail");
            intent.putExtra("name",detailBeanList.get(parentID).getList().get(groupPosition).getList().get(childPosition).getName());
            intent.putExtra("id",detailBeanList.get(parentID).getList().get(groupPosition).getList().get(childPosition).getId());
            mActivity.forward(intent);
            return true;
        });
    }

    private void updateExpandable(){
        classListAdapter.resetList(detailBeanList,parentID);
    }
}
