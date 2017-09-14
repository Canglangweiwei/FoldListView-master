package com.hello.fold;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 促销活动类型Bean
 *
 * @author daren
 */
@SuppressWarnings("ALL")
public class GoodsInfoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 促销名称
     */
    private String PromName;

    /**
     * 促销商品集合
     */
    private List<GoodsBean> PromProducts;

    public GoodsInfoBean() {
        super();
        PromProducts = new ArrayList<GoodsBean>();
    }

    public String getPromName() {
        return PromName;
    }

    public void setPromName(String promName) {
        PromName = promName;
    }

    public List<GoodsBean> getPromProducts() {
        return PromProducts;
    }

    public void setPromProducts(List<GoodsBean> promProducts) {
        PromProducts = promProducts;
    }
}
