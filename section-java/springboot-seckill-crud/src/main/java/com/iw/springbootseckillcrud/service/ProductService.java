package com.iw.springbootseckillcrud.service;

import com.github.pagehelper.PageInfo;
import com.iw.springbootseckillcrud.entity.Product;

public interface ProductService {

    /**
     * 根据页号查询对应的 Product 数据
     * @param pageNo
     * @return
     */
    PageInfo<Product> selectByPageNo(Integer pageNo);

    /**
     * 将一条Product 记录保存到DB中
     * @param product
     */
    void insertProduct(Product product);
}
