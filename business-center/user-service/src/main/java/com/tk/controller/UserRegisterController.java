package com.tk.controller;

import constant.JsonResult;
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

  @GetMapping(value = "/test")
  public JsonResult test(){
    return JsonResult.success("OK");
  }
}
