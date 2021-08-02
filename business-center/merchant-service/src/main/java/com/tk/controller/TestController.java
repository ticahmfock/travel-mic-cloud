package com.tk.controller;

import constant.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: TK
 * @date: 2021/7/30 14:09
 */
@RestController
@RequestMapping(value = "/merchant")
public class TestController {

  @GetMapping(value = "/test")
  public JsonResult test(){
    return JsonResult.success("OK");
  }
}
