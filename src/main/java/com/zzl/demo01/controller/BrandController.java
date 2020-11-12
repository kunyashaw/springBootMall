package com.zzl.demo01.controller;

import cn.hutool.Hutool;
import cn.hutool.core.util.ObjectUtil;
import com.zzl.demo01.api.CommonResult;
import com.zzl.demo01.mbg.model.PmsBrand;
import com.zzl.demo01.service.impl.BrandServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/brand")
@Api(tags = "BrandController",description = "品牌管理")
public class BrandController {
    @Autowired
    BrandServiceImpl bs;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("获取商品列表")
    CommonResult<List<PmsBrand>> getList(){
        return CommonResult.success(bs.getAllList(),"hello");
    }

    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @ResponseBody
    CommonResult<PmsBrand> getBrand(@RequestParam(value = "id",defaultValue = "1")Long id){
            return CommonResult.success(bs.getBrand(id));
    }
    @ApiOperation("根据id获取详情")
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    @ResponseBody
    CommonResult<PmsBrand> getBrandById(@ApiParam("商品id") @PathVariable("id")Long id){
        return CommonResult.success(bs.getBrand(id));
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    CommonResult<PmsBrand> getBrandDetailById(@PathVariable("id")Long id){
        return CommonResult.success(bs.getBrand(id));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    CommonResult deleteBrand(@ApiParam("参考{\"id\":1}")@RequestBody Map map){
        //判断对象非空 （ObjectUtil来自于Hutool）
        if(!ObjectUtil.isEmpty( map.get("id"))){
            String idStr = map.get("id").toString();
            Long id = Long.parseLong(idStr);
            //检查该id的商品品牌是否存在
            if(ObjectUtil.isEmpty(bs.getBrand(id))){
                return CommonResult.failed("id对应的商品不存在");
            }else{
                return CommonResult.success(bs.getBrand(id));
            }
        }else{
            return CommonResult.failed("传参错误");
        }
    }
}
