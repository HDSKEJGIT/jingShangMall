package com.hsh.dongzi.jinshang.ui.main.classfiy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hsh.dongzi.jinshang.MyApplication;
import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.model.main.classfiy.ChildListBean;
import com.hsh.dongzi.jinshang.model.main.classfiy.DetailBean;
import com.hsh.dongzi.jinshang.model.main.classfiy.ParentListBean;
import com.hsh.dongzi.jinshang.utils.LogUtil;

import java.util.List;

public class ClassListAdapter extends BaseExpandableListAdapter {
    private List<DetailBean> list;
    private Context context;
    private int parentId ;

    public ClassListAdapter(Context context,List<DetailBean> list,int parentId){
        this.context = context;
        this.list = list;
        this.parentId = parentId;
    }

    public void resetList(List<DetailBean> list,int parentId){
        this.list = list;
        this.parentId = parentId;
        this.notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return (list != null && list.size() > 0) ? list.get(parentId).getList().size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return (list != null && list.size() > 0) ? list.get(parentId).getList().get(groupPosition).getList().size() : 0;
    }

    @Override
    public Object getGroup(int i) {
        return list.get(parentId).getList().get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(parentId).getList().get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        final GroupViewHolder groupViewHolder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_class_list_group,viewGroup,false);
            groupViewHolder = new GroupViewHolder(view);
            view.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder) view.getTag();
        }
            ParentListBean bean = list.get(parentId).getList().get(i);
            groupViewHolder.tv_name.setText(bean.getName());
            view.setOnClickListener(v -> {
                if (((ExpandableListView) viewGroup).isGroupExpanded(i)) {
                    ((ExpandableListView) viewGroup).collapseGroup(i);
                    groupViewHolder.iv_exapend.setBackgroundResource(R.mipmap.ic_back);
                } else {
                    ((ExpandableListView) viewGroup).expandGroup(i);
                    groupViewHolder.iv_exapend.setBackgroundResource(R.mipmap.ic_launcher);
                }

            });
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder;
        if (null == view){
            view =  LayoutInflater.from(context).inflate(R.layout.item_class_list_child,viewGroup,false);
            childViewHolder = new ChildViewHolder(view);
            view.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) view.getTag();
        }
        ChildListBean childListBean = list.get(parentId).getList().get(i).getList().get(i1);
        childViewHolder.tv_name.setText(childListBean.getName());
        //childViewHolder.tv_content.setText(childListBean.get);
        //Glide.with(context).load(MyApplication.getImageURL()+childListBean.getImg()).into(childViewHolder.iv_icon);
        LogUtil.e(MyApplication.getURL()+childListBean.getImg());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    class GroupViewHolder{
        private TextView tv_name;
        private ImageView iv_exapend;
        public GroupViewHolder(View view){
            tv_name = view.findViewById(R.id.tv_name);
            iv_exapend = view.findViewById(R.id.iv_exapend);
        }
    }

    class ChildViewHolder{
        private ImageView iv_icon;
        private TextView tv_name,tv_content;
        public ChildViewHolder(View view){
            iv_icon = view.findViewById(R.id.iv_icon);
            tv_name = view.findViewById(R.id.tv_name);
            tv_content = view.findViewById(R.id.tv_content);
        }
    }
}
