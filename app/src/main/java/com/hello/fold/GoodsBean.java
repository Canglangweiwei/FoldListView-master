package com.hello.fold;

import java.io.Serializable;

/**
 * 促销商品实体
 *
 * @author daren
 */
@SuppressWarnings("ALL")
public class GoodsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String ProductName;

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public GoodsBean() {
        super();
    }

    public GoodsBean(String productName) {
        ProductName = productName;
    }
}
