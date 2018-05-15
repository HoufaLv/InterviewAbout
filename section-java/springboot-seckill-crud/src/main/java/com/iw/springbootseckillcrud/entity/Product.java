package com.iw.springbootseckillcrud.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class Product implements Serializable {

    private Integer id;

    private String productName;

    private String productTitle;

    private String productInventory;

    private BigDecimal productPrice;

    private BigDecimal productMarkeyPrice;

    private Date startTime;

    private Date endTime;

    private String productDesc;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(String productInventory) {
        this.productInventory = productInventory;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductMarkeyPrice() {
        return productMarkeyPrice;
    }

    public void setProductMarkeyPrice(BigDecimal productMarkeyPrice) {
        this.productMarkeyPrice = productMarkeyPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}