package com.tk.common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.tk.service.impl.TestServiceImpl;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author: TK
 * @date: 2021/10/21 10:52
 */
@Slf4j
@Component
public class TravelFutureTask {

  @Resource
  private TestServiceImpl testServiceImpl;


  private static ExecutorService executorService = new ThreadPoolExecutor(
      8,20,30L, TimeUnit.SECONDS,new LinkedBlockingDeque<>(10),
      new ThreadFactoryBuilder().setNameFormat("User_Async_FutureTask-%d").setDaemon(true).build(),
      new ThreadPoolExecutor.CallerRunsPolicy());


  public ConcurrentHashMap getUserAggregatedResult(final Long userId) {

    System.out.println("MyFutureTask的线程:" + Thread.currentThread());


    long fansCount = 0, msgCount = 0, collectCount = 0,
        followCount = 0, redBagCount = 0, couponCount = 0;

        fansCount = testServiceImpl.countFansCountByUserId(userId);
        msgCount = testServiceImpl.countMsgCountByUserId(userId);
        collectCount = testServiceImpl.countCollectCountByUserId(userId);
        followCount = testServiceImpl.countFollowCountByUserId(userId);
        redBagCount = testServiceImpl.countRedBagCountByUserId(userId);
        couponCount = testServiceImpl.countCouponCountByUserId(userId);

   /* try {

      Future<Long> fansCountFT = executorService.submit(() -> testServiceImpl.countFansCountByUserId(userId));
      Future<Long> msgCountFT = executorService.submit(() -> testServiceImpl.countMsgCountByUserId(userId));
      Future<Long> collectCountFT = executorService.submit(() -> testServiceImpl.countCollectCountByUserId(userId));
      Future<Long> followCountFT = executorService.submit(() -> testServiceImpl.countFollowCountByUserId(userId));
      Future<Long> redBagCountFT = executorService.submit(() -> testServiceImpl.countRedBagCountByUserId(userId));
      Future<Long> couponCountFT = executorService.submit(() -> testServiceImpl.countCouponCountByUserId(userId));

      //get阻塞
      fansCount = fansCountFT.get();
      msgCount = msgCountFT.get();
      collectCount = collectCountFT.get();
      followCount = followCountFT.get();
      redBagCount = redBagCountFT.get();
      couponCount = couponCountFT.get();

    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
      log.error(">>>>>>聚合查询用户聚合信息异常:" + e + "<<<<<<<<<");
    }*/
    ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap<>();
    concurrentHashMap.put("fansCount",fansCount);
    concurrentHashMap.put("msgCount",msgCount);
    concurrentHashMap.put("collectCount",collectCount);
    concurrentHashMap.put("followCount",followCount);
    concurrentHashMap.put("redBagCount",redBagCount);
    concurrentHashMap.put("couponCount",couponCount);
    return concurrentHashMap;
  }


}
