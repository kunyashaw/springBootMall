package com.zzl.demo01.controller;

import com.zzl.demo01.api.CommonResult;
import com.zzl.demo01.nosql.mongo.Product;
import com.zzl.demo01.service.impl.ProductServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "productController" ,description = "商品管理byMongo")
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Autowired
    private ProductServiceImpl psl;

    @ApiOperation("创建商品")
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    CommonResult create(@ApiParam("name")@RequestParam()String name,
                        @ApiParam("price")@RequestParam()int price){
        Product pm = new Product();
        pm.setPrice(price);
        pm.setName(name);
        psl.create(pm);
        return  CommonResult.success(pm);
    }

    @ApiOperation("查询商品")
    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public CommonResult check(){
        return CommonResult.success(psl.list());
    }

}
