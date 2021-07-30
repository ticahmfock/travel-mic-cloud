package com.tk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * 动态路由
 *
 * @author: TK
 * @date: 2021/7/23 10:27
 */
@Data
public class DynamicRoute implements Serializable {

  private static final long serialVersionUID = 2845688035774666013L;

  /**
   * 路由的Id
   */
  private String id;

  /**
   * 路由断言集合配置
   */
  private List<DynamicRoutePredicate> predicates = new ArrayList<>();

  /**
   * 路由过滤器集合配置
   */
  private List<DynamicRouteFilter> filters = new ArrayList<>();

  /**
   * 路由规则转发的目标uri
   */
  private String uri;

  /**
   * 路由执行的顺序
   */
  private int order = 0;
}
