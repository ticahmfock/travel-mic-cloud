package com.tk.service.impl;

import com.tk.common.config.util.DynamicRouteDefinitionUtil;
import com.tk.model.DynamicRoute;
import com.tk.service.GatewayDynamicService;
import constant.JsonResult;
import javax.annotation.Resource;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author: TK
 * @date: 2021/7/23 15:34
 */
@Service("gatewayDynamicServiceImpl")
public class GatewayDynamicServiceImpl implements GatewayDynamicService, ApplicationEventPublisherAware {

  @Resource
  private ApplicationEventPublisher publisher;
  @Resource
  private RouteDefinitionWriter routeDefinitionWriter;

  /**
   * 新增路由
   * @param dynamicRoute
   * @return
   */
  @Override
  public JsonResult addGatewayDynamic(DynamicRoute dynamicRoute) {
    //构建gateway路由对象
    /*RouteDefinition routeDefinition = DynamicRouteDefinitionUtil.getDynamicRouteDefinition(dynamicRoute);
    routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
    this.publisher.publishEvent(new RefreshRoutesEvent(this));*/
    return JsonResult.success("success");
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }


}
