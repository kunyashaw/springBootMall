package com.zzl.demo01.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.zzl.demo01.api.CommonResult;
import com.zzl.demo01.service.impl.RedisServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(tags = "phoneValidCOntronner",description = "手机验证码")
@RequestMapping("/phone")
public class PhoneValidController extends BaseController{

    @Autowired
    private RedisServiceImpl redisService;
    @Value("${redis.key.prefix.authCode:\"authCode\"}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @ApiOperation("发送验证码")
    @RequestMapping(value = "/send",method = RequestMethod.GET)
    CommonResult send(@ApiParam("手机号") @RequestParam("phone")String phone){
        String code = RandomUtil.randomString(6);
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+phone,code);
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+phone,60*3);
        return CommonResult.success("验证码为"+code);
    }

    @ApiOperation("检查验证码")
    @RequestMapping(value = "/verify",method = RequestMethod.POST)
    CommonResult verify(@ApiParam("手机号")@RequestParam(value =  "phone",required = true)String phone,
                        @ApiParam("验证码")@RequestParam(value = "code",required = true)String code){
        String codeRedis = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE+phone);
        //对比验证码
        if(StrUtil.equals(code,codeRedis)){
            //从redis中移除
            redisService.remove(phone);
            return CommonResult.success(null,"验证成功");
        }else{
            return CommonResult.validateFailed("验证码错误");
        }
    }



    @ApiOperation("查询验证码是否正确")
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    CommonResult check(@ApiParam("手机号与验证码")@RequestBody() Map map){
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();
        if(!ObjectUtil.isEmpty(phone) && !ObjectUtil.isEmpty(code)){
            //从redis中读取指定手机号对应的验证码
            String codeRedis = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE+phone);
            //对比验证码
            if(StrUtil.equals(code,codeRedis)){
                //从redis中移除
                redisService.remove(phone);
                return CommonResult.success(null,"验证成功");
            }else{
                return CommonResult.validateFailed("验证码错误");
            }
        }else{
            return CommonResult.validateFailed("参数校验失败");
        }
    }
}
