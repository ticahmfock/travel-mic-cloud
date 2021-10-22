package com.tk.controller;

import com.tk.common.TravelFutureTask;
import com.tk.service.TestService;
import constant.JsonResult;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: TK
 * @date: 2021/10/21 10:52
 */
@RestController
@RequestMapping(value = "/user/test")
public class FutureController {

  @Resource
  private TestService testService;
  @Resource
  private TravelFutureTask travelFutureTask;

  @GetMapping(value = "/1")
  public JsonResult getTest(){
    System.out.println("UserController的线程:" + Thread.currentThread());
    long begin = System.currentTimeMillis();
    ConcurrentHashMap concurrentHashMap = travelFutureTask.getUserAggregatedResult(1L);
    long end = System.currentTimeMillis();
    System.out.println("===============总耗时:" + (end - begin) /1000.0000+ "秒");
    return JsonResult.success(concurrentHashMap,"获取成功");
  }
}
