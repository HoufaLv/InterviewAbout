package com.iw.springbootseckillcrud.controller;

import com.github.pagehelper.PageInfo;
import com.iw.springbootseckillcrud.entity.Product;
import com.iw.springbootseckillcrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Product 相关的业务控制器
 * @author Lvhoufa
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    // TODO: 2018/5/14 0014 在ProductController中处理Product相关的控制器逻辑代码,将业务代码交由Service的实现类去做

    @Autowired
    private ProductService productService;

    /**
     * goto Product home page
     * @param pageNo
     * @param model
     * @return page
     */
    @GetMapping
    public String home(@RequestParam(required = false, name = "p",defaultValue = "1")Integer pageNo,
                       Model model){

        PageInfo<Product> productPageInfo = productService.selectByPageNo(pageNo);

        model.addAttribute("pageInfo",productPageInfo);
        return "product/home";
    }


    /**
     * goto templates/product/new.html
     * @return
     */
    @GetMapping("/new")
    public String newProduct(){
        return "product/new";
    }

    /**
     * save product Object to DB
     * @param product
     * @return  template/product/home.html
     */
    @PostMapping("/new")
    public String newProduct(Product product){
        System.out.println(product);
        productService.insertProduct(product);
        return "redirect:/product";
    }

}
