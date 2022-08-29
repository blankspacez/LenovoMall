package com.mall.lenovoMall.controller;

import com.github.pagehelper.PageInfo;
import com.mall.lenovoMall.entity.Category;
import com.mall.lenovoMall.entity.Product;
import com.mall.lenovoMall.entity.searchData;
import com.mall.lenovoMall.service.CategoryService;
import com.mall.lenovoMall.service.ProductService;
import com.mall.lenovoMall.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/product")
public class ProductController {

//    @Autowired
//    private ResultMessage resultMessage;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/limit/{categoryId}")
    public ResultMessage getProductByCategoryId(@PathVariable Integer categoryId) {
        ResultMessage resultMessage=new ResultMessage();

        List<Product> list = productService.getProductByCategoryId(categoryId);
        resultMessage.success("001", list);

//        ResultMessage rList= (ResultMessage) resultMessage.success("001", list);
//        System.out.println(categoryId.toString()+ list.toString());
        return resultMessage;
    }

    @GetMapping("/category/hot")
    public ResultMessage getHotProduct() {
        ResultMessage resultMessage=new ResultMessage();
        List<Product> list = productService.getHotProduct();
        resultMessage.success("001", list);
        return resultMessage;

    }

    @GetMapping("/{productId}")
    public ResultMessage getProduct(@PathVariable Integer productId) {
        ResultMessage resultMessage=new ResultMessage();
        Product product = productService.getProductById(productId);
        resultMessage.success("001", product);
        return resultMessage;
    }

    @GetMapping("/page/{currentPage}/{pageSize}/{categoryId}")
    public Map<String, Object> getProductByPage(@PathVariable String currentPage, @PathVariable String pageSize, @PathVariable String categoryId) {

        PageInfo<Product> pageInfo = productService.getProductByPage(currentPage, pageSize, categoryId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "001");
        map.put("data", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @PostMapping("/getProductBySearch")
    public Map<String, Object> getProductBySearch(@RequestBody searchData data){
        List<Category> categories = categoryService.getAll();
        HashMap<String, Object> map = new HashMap<>();
        for(int i=0; i<categories.size(); i++){
            Category category=categories.get(i);
            if(Objects.equals(data.search, category.getCategoryName())) {
                System.out.println(String.valueOf(category.getCategoryId()));
                PageInfo<Product> pageInfo = productService.getProductByPage(data.currentPage, data.pageSize, String.valueOf(category.getCategoryId()));
                map.put("code", "001");
                map.put("Product", pageInfo.getList());
                map.put("total", pageInfo.getTotal());
                return map;
            }
        }
        PageInfo<Product> pageInfo =productService.getProductBySearch(data.search,data.currentPage,data.pageSize);
        map.put("code", "001");
        map.put("Product", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }
}
