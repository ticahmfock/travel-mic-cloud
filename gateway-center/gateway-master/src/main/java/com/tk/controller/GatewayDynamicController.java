package com.tk.controller;

import com.tk.service.GatewayDynamicService;
import constant.JsonResult;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
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
   * 重置路由
   *
   * @return
   */
  @GetMapping(value = "/rest")
  public JsonResult restGatewayDynamic() {
    return gatewayDynamicService.restGatewayDynamic();
  }
}
