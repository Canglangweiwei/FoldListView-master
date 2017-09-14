package com.hello.fold;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private List<GoodsInfoBean> promList = new ArrayList<>();
    private List<CollapsableLinearLayout> collapsablelist = new ArrayList<>();
    private LayoutInflater inflater;
    private static final int index = 0;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUi() {
        inflater = LayoutInflater.from(this);
        configToolbar(false, "商品列表", true, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击了操作按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initDatas() {
        // 数据1
        GoodsInfoBean mGoodsInfoBean1 = new GoodsInfoBean();
        mGoodsInfoBean1.setPromName("奶粉");

        GoodsBean mGoodsBean1 = new GoodsBean("可瑞康");
        GoodsBean mGoodsBean2 = new GoodsBean("启赋");
        GoodsBean mGoodsBean3 = new GoodsBean("贝因美");

        List<GoodsBean> list1 = new ArrayList<>();
        list1.add(mGoodsBean1);
        list1.add(mGoodsBean2);
        list1.add(mGoodsBean3);
        list1.add(mGoodsBean1);
        list1.add(mGoodsBean2);
        list1.add(mGoodsBean3);
        mGoodsInfoBean1.setPromProducts(list1);

        // 数据2
        GoodsInfoBean mGoodsInfoBean2 = new GoodsInfoBean();
        mGoodsInfoBean2.setPromName("宝宝服饰");

        GoodsBean mGoodsBean4 = new GoodsBean("裤子");
        GoodsBean mGoodsBean5 = new GoodsBean("上衣");
        GoodsBean mGoodsBean6 = new GoodsBean("长跑");

        ArrayList<GoodsBean> list2 = new ArrayList<>();
        list2.add(mGoodsBean4);
        list2.add(mGoodsBean5);
        list2.add(mGoodsBean6);
        list2.add(mGoodsBean4);
        list2.add(mGoodsBean5);
        list2.add(mGoodsBean6);
        mGoodsInfoBean2.setPromProducts(list2);

        // 数据3
        GoodsInfoBean mGoodsInfoBean3 = new GoodsInfoBean();
        mGoodsInfoBean3.setPromName("男士上衣");

        GoodsBean mGoodsBean7 = new GoodsBean("长衫");
        GoodsBean mGoodsBean8 = new GoodsBean("短袖");
        GoodsBean mGoodsBean9 = new GoodsBean("西服");

        ArrayList<GoodsBean> list3 = new ArrayList<>();
        list3.add(mGoodsBean7);
        list3.add(mGoodsBean8);
        list3.add(mGoodsBean9);
        list3.add(mGoodsBean7);
        list3.add(mGoodsBean8);
        list3.add(mGoodsBean9);
        mGoodsInfoBean3.setPromProducts(list3);

        promList.add(mGoodsInfoBean1);
        promList.add(mGoodsInfoBean2);
        promList.add(mGoodsInfoBean3);

        showPromList();
    }

    @Override
    protected void initListener() {

    }

    private void showPromList() {
        List<View> comboViews = createContentViews(promList);
        LinearLayout comboInfoContainer = (LinearLayout) findViewById(R.id.combo_info_container_linear);
        for (View comboView : comboViews) {
            if (comboInfoContainer != null) {
                comboInfoContainer.addView(comboView);
                View dividerView = new View(this);
                dividerView.setBackgroundColor(Color.parseColor("#f8f8f8"));
                comboInfoContainer.addView(dividerView, new LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT));// Util.dp2px(this, 30)
            }
        }
    }

    private List<View> createContentViews(List<GoodsInfoBean> promInfo) {
        List<View> contentViews = new ArrayList<>();
        int realIndex = 0;
        if (promInfo != null && promInfo.size() != 0) {
            for (int i = 0; i < promInfo.size(); i++) {
                GoodsInfoBean prom = promInfo.get(i);
                if (prom.getPromProducts() != null) {
                    if (prom.getPromProducts().size() != 0) { // 不展示status为4
                        View promInfoItem = createPromInfoItem(prom, realIndex);
                        contentViews.add(promInfoItem);
                        realIndex++;
                    }
                }
            }
        }
        return contentViews;
    }

    private View createPromInfoItem(GoodsInfoBean promInfo, int indexs) {
        // @formatter:off
        final View comboItem = inflater.inflate(R.layout.item_goods, null);
        RelativeLayout combo_title_container = (RelativeLayout) comboItem.findViewById(R.id.combo_title_container);
        TextView nickNameView = (TextView) comboItem.findViewById(R.id.combo_nick_name_text);
        TextView comboNumText = (TextView) comboItem.findViewById(R.id.combo_num_text_01);
        final ImageView arrowImage = (ImageView) comboItem.findViewById(R.id.combo_arrow_image);
        final CollapsableLinearLayout childContainer =
                (CollapsableLinearLayout) comboItem.findViewById(R.id.combo_products_container);
        childContainer.setToggleView(arrowImage);
        // create child product views
        if (promInfo.getPromProducts() != null && promInfo.getPromProducts().size() > 0) {
            for (GoodsBean childBean : promInfo.getPromProducts()) {
                View childView = createComboChildItem(childBean);
                childContainer.addView(childView);
            }
        }
        collapsablelist.add(childContainer);
        // 如果是第一条数据就展开
        if (index == indexs) {
            childContainer.expand();
        } else {
            childContainer.collapse();
        }
        nickNameView.setText(promInfo.getPromName());
        comboNumText.setText(String.valueOf(indexs + 1));
        combo_title_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childContainer.toggle();
                // 收起其他的view
                for (int i = 0; i < collapsablelist.size(); i++) {
                    if (!childContainer.equals(collapsablelist.get(i))) {
                        if (collapsablelist.get(i).isExpanded()) {
                            collapsablelist.get(i).collapseOther();
                        }
                    }
                }
            }
        });
        // @formatter:on
        return comboItem;
    }

    private View createComboChildItem(final GoodsBean childBean) {
        // @formatter:off
        View comboChildItem = inflater.inflate(R.layout.item_goods_child, null);
        LinearLayout product_linear = (LinearLayout) comboChildItem.findViewById(R.id.product_linear);
        TextView childNameView = (TextView) comboChildItem.findViewById(R.id.combo_product_name);

        childNameView.setText(childBean.getProductName());
        product_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(MainActivity.class.getName(), "点击商品");
                Toast.makeText(MainActivity.this, "点击商品：【" + childBean.getProductName() + "】",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return comboChildItem;
    }
}
