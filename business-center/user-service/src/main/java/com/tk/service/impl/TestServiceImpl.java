package com.tk.service.impl;

import com.tk.service.TestService;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

/**
 * @author: TK
 * @date: 2021/10/21 11:21
 */
@Service(value = "testServiceImpl")
public class TestServiceImpl implements TestService {

  @Override
  public long countFansCountByUserId(Long userId) {
    try {
      Thread.sleep(10000);
      System.out.println("获取FansCount===睡眠:" + 10 + "s");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("UserService获取FansCount的线程  " + Thread.currentThread().getName());
    return 520;
  }

  @Override
  public long countMsgCountByUserId(Long userId) {
    System.out.println("UserService获取MsgCount的线程  " + Thread.currentThread().getName());
    try {
      Thread.sleep(10000);
      System.out.println("获取MsgCount===睡眠:" + 10 + "s");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return 618;
  }

  @Override
  public long countCollectCountByUserId(Long userId) {
    System.out.println("UserService获取CollectCount的线程  " + Thread.currentThread().getName());
    try {
      Thread.sleep(10000);
      System.out.println("获取CollectCount==睡眠:" + 10 + "s");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return 6664;
  }

  @Override
  public long countFollowCountByUserId(Long userId) {
    System.out.println("UserService获取FollowCount的线程  " + Thread.currentThread().getName());
    try {
      Thread.sleep(10000);
      System.out.println("获取FollowCount===睡眠:" + 10 + "s");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return 999;
  }

  @Override
  public long countRedBagCountByUserId(Long userId) {
    System.out.println("UserService获取RedBagCount的线程  " + Thread.currentThread().getName());
    try {
      TimeUnit.SECONDS.sleep(4);
      System.out.println("获取RedBagCount===睡眠:" + 4 + "s");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return 99;
  }

  @Override
  public long countCouponCountByUserId(Long userId) {
    System.out.println("UserService获取CouponCount的线程  " + Thread.currentThread().getName());
    try {
      TimeUnit.SECONDS.sleep(8);
      System.out.println("获取CouponCount===睡眠:" + 8 + "s");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return 66;
  }
}
