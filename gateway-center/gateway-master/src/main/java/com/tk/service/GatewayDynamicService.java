package com.tk.service;

import com.tk.model.DynamicRoute;
import constant.JsonResult;

/**
 * @author: TK
 * @date: 2021/7/23 15:34
 */
public interface GatewayDynamicService {

  /**
   * 新增路由
   *
   * @return
   */
  JsonResult addGatewayDynamic(DynamicRoute dynamicRoute);
}
