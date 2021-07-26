package com.tk.controller;

import constant.JsonResult;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: TK
 * @date: 2021/7/23 9:10
 */
@RestController
@RequestMapping(value = "/user/register")
public class UserRegisterController {

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @GetMapping(value = "/test")
  public JsonResult test(){
    String key = "test";
    if (stringRedisTemplate.hasKey(key)){
      return JsonResult.success(stringRedisTemplate.opsForValue().get("test"),"获取成功");
    }
    stringRedisTemplate.opsForValue().set(key,"测试",3, TimeUnit.SECONDS);
    return JsonResult.success("OK");
  }
}
