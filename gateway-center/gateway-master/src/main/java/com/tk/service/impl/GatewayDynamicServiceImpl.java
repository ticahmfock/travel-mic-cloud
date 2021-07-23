package com.tk.service.impl;

import com.tk.service.GatewayDynamicService;
import constant.JsonResult;
import javax.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: TK
 * @date: 2021/7/23 15:34
 */
@Service("gatewayDynamicServiceImpl")
public class GatewayDynamicServiceImpl implements GatewayDynamicService {

  @Resource
  private ApplicationEventPublisher publisher;
  @Resource
  private RedisTemplate redisTemplate;

  public JsonResult restGatewayDynamic() {
    this.publisher.publishEvent(null);
    return null;
  }
}
