package com.tk.model;

import java.io.Serializable;
import lombok.Data;

/**
 * 路由实体类对象
 *
 * @author: TK
 * @date: 2021/7/23 10:27
 */
@Data
public class DynamicRoute implements Serializable {

  private static final long serialVersionUID = 2845688035774666013L;

  /**
   * 唯一ID
   */
  private String id;

  /**
   * 转发标识
   */
  private String path;

  /**
   * 分组，如果需要双机负载均衡，就把两个路由group填写一致
   */
  private String group;

  /**
   * 权重，配合分组使用，如果分组填写了，就可以调节权重0-100
   */
  private Integer weight;

  /**
   * 转发目标的地址
   */
  private String uri;
}
