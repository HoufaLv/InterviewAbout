package com.iw.springbootseckillcrud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iw.springbootseckillcrud.entity.Product;
import com.iw.springbootseckillcrud.entity.ProductExample;
import com.iw.springbootseckillcrud.mapper.ProductMapper;
import com.iw.springbootseckillcrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 根据页号查询对应的 Product 数据
     *
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<Product> selectByPageNo(Integer pageNo) {
        // TODO: 2018/5/14 0014 使用PageHelper + ProductExample 来做查询

        PageHelper.startPage(pageNo,10);
        ProductExample productExample = new ProductExample();
        List<Product> products = productMapper.selectByExample(productExample);

        return new PageInfo<>(products);
    }

    /**
     * 将一条Product 记录保存到DB中
     *
     * @param product
     */
    @Override
    public void insertProduct(Product product) {
        productMapper.insertSelective(product);
    }
}
