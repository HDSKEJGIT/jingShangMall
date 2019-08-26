package com.hsh.dongzi.jinshang.ui.main.classfiy.search;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsh.dongzi.jinshang.R;
import com.hsh.dongzi.jinshang.adapter.recyclerview.BaseRecyclerAdapter;
import com.hsh.dongzi.jinshang.adapter.recyclerview.BaseRecyclerViewHolder;
import com.hsh.dongzi.jinshang.model.main.classfiy.search.ResultSearchBean;
import com.hsh.dongzi.jinshang.utils.StringUtils;

import java.util.List;

public class ResultSearchAdapter extends BaseRecyclerAdapter<ResultSearchBean> {

    private OnItemClickListener  mOnItemClickListener;
    public ResultSearchAdapter(Context context, List<ResultSearchBean> list, int resId) {
        super(context, list, resId);
    }

    @Override
    public BaseRecyclerViewHolder initViewHolder(View view) {
        return new ViewHolder(view);
    }

    public void setListener( OnItemClickListener  mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void resetList(List<ResultSearchBean> _list){
        this._list = _list;
        this.notifyDataSetChanged();
    }

    @Override
    public void dealView(Context context, int position, BaseRecyclerViewHolder holder) {
       ViewHolder viewHolder = (ViewHolder) holder;
        ResultSearchBean searchBean = getItem(position);
        viewHolder.initData(context,searchBean,position);
    }

    class ViewHolder extends BaseRecyclerViewHolder<ResultSearchBean>{
        private TextView tv_titile,tv_space,tv_repertory,tv_loose,tv_address,tv_num,tv_price,tv_space_num;
        private TextView tv_standard,tv_stand,tv_materia,tv_cardnum,tv_unit;
        private ImageView iv_add,iv_delete,iv_add_cart;

        protected ViewHolder(View view) {
            super(view);
        }

        @Override
        protected void initView(View view) {
            tv_titile = view.findViewById(R.id.tv_titile);
            tv_space = view.findViewById(R.id.tv_space);
            tv_repertory = view.findViewById(R.id.tv_repertory);
            tv_loose = view.findViewById(R.id.tv_loose);
            tv_address = view.findViewById(R.id.tv_address);
            tv_num = view.findViewById(R.id.tv_num);
            tv_price = view.findViewById(R.id.tv_price);
            tv_space_num = view.findViewById(R.id.tv_space_num);
            tv_standard = view.findViewById(R.id.tv_standard);
            tv_stand = view.findViewById(R.id.tv_stand);
            tv_materia = view.findViewById(R.id.tv_materia);
            tv_cardnum = view.findViewById(R.id.tv_cardnum);
            iv_add = view.findViewById(R.id.iv_add);
            iv_delete = view.findViewById(R.id.iv_delete);
            iv_add_cart = view.findViewById(R.id.iv_add_cart);
            tv_unit = view.findViewById(R.id.tv_unit);
        }

        @Override
        protected void initData(Context context, ResultSearchBean resultSearchBean, int position) {
            if (null != resultSearchBean){
                //判空 商品名称
                if (StringUtils.isEmpty(resultSearchBean.getProductname())){
                    tv_titile.setVisibility(View.GONE);
                }else {
                    tv_titile.setVisibility(View.VISIBLE);
                    tv_titile.setText(resultSearchBean.getProductname());
                }
                //判空 商品标准
                if (StringUtils.isEmpty(resultSearchBean.getLevel3())){
                    tv_standard.setVisibility(View.GONE);
                }else {
                    tv_standard.setVisibility(View.VISIBLE);
                    tv_standard.setText(" /"+resultSearchBean.getLevel3());
                }
                //判空 商品规格
                if (StringUtils.isEmpty(resultSearchBean.getStand())){
                    tv_stand.setVisibility(View.GONE);
                }else {
                    tv_stand.setVisibility(View.VISIBLE);
                    tv_stand.setText("/"+resultSearchBean.getStand());
                }
                //判空 商品材质
                if (StringUtils.isEmpty(resultSearchBean.getMaterial())){
                    tv_materia.setVisibility(View.GONE);
                }else {
                    tv_materia.setVisibility(View.VISIBLE);
                    tv_materia.setText("/"+resultSearchBean.getMaterial());
                }
                //判空 商品牌号
                if (StringUtils.isEmpty(resultSearchBean.getCardnum())){
                    tv_cardnum.setVisibility(View.GONE);
                }else {
                    tv_cardnum.setVisibility(View.VISIBLE);
                    tv_cardnum.setText("/"+resultSearchBean.getCardnum());
                }
                //判空 商品包装方式
                if (StringUtils.isEmpty(resultSearchBean.getPackagetype())){
                    tv_space.setVisibility(View.GONE);
                }else {
                    tv_space.setVisibility(View.VISIBLE);
                    tv_space.setText(resultSearchBean.getPackagetype());
                }
                //判空 商品库存
                tv_repertory.setText("库存:"+ resultSearchBean.getPdstorenum()+"千支");
                //判空 商品拆零非

                //判空 商品仓库
                if (StringUtils.isEmpty(resultSearchBean.getStorename())){
                    tv_address.setVisibility(View.GONE);
                }else {
                    tv_address.setVisibility(View.VISIBLE);
                    tv_address.setText(resultSearchBean.getStorename());
                }
                //判空单位
                if (StringUtils.isEmpty(resultSearchBean.getStoreunit())){
                    tv_unit.setVisibility(View.GONE);
                }else {
                    tv_unit.setVisibility(View.VISIBLE);
                    tv_unit.setText(resultSearchBean.getStoreunit());
                }
                //单价
                if (resultSearchBean.getProdprice() != 0){
                    tv_price.setVisibility(View.VISIBLE);
                    tv_price.setText("¥"+resultSearchBean.getProdprice()+"/"+resultSearchBean.getStoreunit());
                }else {
                    tv_price.setVisibility(View.GONE);
                }
                //...

            }else {
                tv_titile.setVisibility(View.GONE);
                tv_standard.setVisibility(View.GONE);
                tv_stand.setVisibility(View.GONE);
                tv_materia.setVisibility(View.GONE);
                tv_cardnum.setVisibility(View.GONE);
                tv_space.setVisibility(View.GONE);
                tv_repertory.setVisibility(View.GONE);
                tv_loose.setVisibility(View.GONE);
                tv_address.setVisibility(View.GONE);
                tv_num.setVisibility(View.GONE);
                tv_price.setVisibility(View.GONE);
                tv_space_num.setVisibility(View.GONE);
                iv_add.setVisibility(View.GONE);
                iv_delete.setVisibility(View.GONE);
                iv_add_cart.setVisibility(View.GONE);
            }
            //加号点击事件
            iv_add.setOnClickListener(view -> mOnItemClickListener.numAdd(position,tv_num.getText().toString()));
            //减号点击事件
            iv_delete.setOnClickListener(view -> {
                mOnItemClickListener.numDel(position,tv_num.getText().toString());
            });
            //数量点击事件
            tv_num.setOnClickListener(view -> mOnItemClickListener.numClick(position,tv_num.getText().toString()));
            //购物车按钮点击事件
            iv_add_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.addCartClick(position,tv_num.getText().toString());
                }
            });
        }
    }

    public interface OnItemClickListener{
        void numAdd(int position,String num);
        void numDel(int position,String num);
        void numClick(int position,String num);
        void addCartClick(int position,String num);
    }
}
