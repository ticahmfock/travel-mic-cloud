package com.tk.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author: TK
 * @date: 2021/7/30 10:00
 */
@Data
public class DynamicRouteFilter implements Serializable {

  private static final long serialVersionUID = -6278863101802582788L;

  /**
   * Filter Name
   */
  private String name;

  /**
   * 对应的路由规则
   */
  private Map<String, String> args = new LinkedHashMap<>();
}
