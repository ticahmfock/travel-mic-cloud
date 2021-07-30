package com.tk.controller;

import com.tk.model.DynamicRoute;
import com.tk.model.DynamicRouteFilter;
import com.tk.model.DynamicRoutePredicate;
import com.tk.service.GatewayDynamicService;
import constant.JsonResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: TK
 * @date: 2021/7/23 15:33
 */
@RestController
@RequestMapping(value = "/gateway/dynamic")
public class GatewayDynamicController {

  @Resource
  private GatewayDynamicService gatewayDynamicService;

  /**
   * 新增路由
   *
   * @return
   */
  @GetMapping(value = "/add")
  public JsonResult restGatewayDynamic() {
    DynamicRoute dynamicRoute = new DynamicRoute();
    dynamicRoute.setId("api-user");
    dynamicRoute.setUri("lb://user-service");
    dynamicRoute.setOrder(1);

    List<DynamicRoutePredicate> predicates = new ArrayList<>();
    DynamicRoutePredicate dynamicRoutePredicate = new DynamicRoutePredicate();
    dynamicRoutePredicate.setName("Path");
    Map<String, String> args1 = new HashMap<>();
    args1.put("pattern","/api-user/**");
    dynamicRoutePredicate.setArgs(args1);
    predicates.add(dynamicRoutePredicate);
    dynamicRoute.setPredicates(predicates);

    List<DynamicRouteFilter> filters = new ArrayList<>();
    DynamicRouteFilter dynamicRouteFilter = new DynamicRouteFilter();
    dynamicRouteFilter.setName("StripPrefix");
    Map<String, String> args2 = new HashMap<>();
    args2.put("_genkey_0","1");
    dynamicRouteFilter.setArgs(args2);
    filters.add(dynamicRouteFilter);
    dynamicRoute.setFilters(filters);
    return gatewayDynamicService.addGatewayDynamic(dynamicRoute);
  }
}
