package com.tk.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author: TK
 * @date: 2021/7/30 9:59
 */
@Data
public class DynamicRoutePredicate implements Serializable {

  private static final long serialVersionUID = 5899982145764961205L;

  /**
   * 断言对应的Name
   */
  private String name;

  /**
   * 配置的断言规则
   */
  private Map<String, String> args = new LinkedHashMap<>();
}
