package com.hsh.dongzi.jinshang.ui.main.classfiy;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.adapter.recyclerview.BaseRecyclerAdapter;
import com.hsh.dongzi.jinshang.adapter.recyclerview.BaseRecyclerViewHolder;
import com.hsh.dongzi.jinshang.model.main.classfiy.OutLineBean;

import java.util.List;

public class ClassFiyAdapter extends BaseRecyclerAdapter<OutLineBean> {
    private OnItemClickListener mOnItemClickListener;

    public ClassFiyAdapter(Context context, List<OutLineBean> list, int resId) {
        super(context, list, resId);
    }

    public void setItemListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void resetList(List<OutLineBean> list){
        this._list = list;
        this.notifyDataSetChanged();
    }
    @Override
    public BaseRecyclerViewHolder initViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void dealView(Context context, int position, BaseRecyclerViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
        OutLineBean bean = getItem(position);
        viewHolder.initData(context,bean,position);
    }

    class ViewHolder extends BaseRecyclerViewHolder<OutLineBean>{
        private LinearLayout ll_item;
        private View view_strip;
        private TextView tv_name;
        protected ViewHolder(View view) {
            super(view);
        }

        @Override
        protected void initView(View view) {
            ll_item = view.findViewById(R.id.ll_item);
            tv_name = view.findViewById(R.id.tv_name);
            view_strip = view.findViewById(R.id.view_strip);
        }

        @Override
        protected void initData(Context context, OutLineBean outLineBean, int position) {
            tv_name.setText(outLineBean.getName());
            ll_item.setOnClickListener(view -> {
                mOnItemClickListener.OnItemClick(position);
            });
            updateUI(outLineBean);
        }

        private void updateUI(OutLineBean outLineBean){
            if (outLineBean.getSelect()){
                view_strip.setBackgroundResource(R.color.red);
                ll_item.setBackgroundResource(R.color.white);
                tv_name.setTextColor(Color.parseColor("#000000"));
            }else {
                view_strip.setBackgroundResource(R.color.gray);
                ll_item.setBackgroundResource(R.color.gray);
                tv_name.setTextColor(Color.parseColor("#666666"));
            }
        }

    }

    public interface OnItemClickListener{
       void OnItemClick(int position);
    }

}
