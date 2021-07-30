package com.tk.common.config.util;

import com.tk.model.DynamicRoute;
import com.tk.model.DynamicRouteFilter;
import com.tk.model.DynamicRoutePredicate;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.web.util.UriComponentsBuilder;

/**动态路由构造工具类
 * @author: TK
 * @date: 2021/7/30 10:10
 */
public class DynamicRouteDefinitionUtil {

  /**
   * 获取路由定义信息
   * @return
   */
  public static RouteDefinition getDynamicRouteDefinition(DynamicRoute dynamicRoute){
    RouteDefinition definition = new RouteDefinition();
    definition.setId(dynamicRoute.getId());

    // 路由断言集合配置
    List<PredicateDefinition> predicateList = new ArrayList<>();
    for (DynamicRoutePredicate predicate: dynamicRoute.getPredicates()) {
      PredicateDefinition predicateDefinition = new PredicateDefinition();
      predicateDefinition.setArgs(predicate.getArgs());
      predicateDefinition.setName(predicate.getName());
      predicateList.add(predicateDefinition);
    }
    definition.setPredicates(predicateList);

    //路由过滤器集合配置
    List<FilterDefinition> filterList = new ArrayList<>();
    for (DynamicRouteFilter filter: dynamicRoute.getFilters()) {
      FilterDefinition filterDefinition = new FilterDefinition();
      filterDefinition.setArgs(filter.getArgs());
      filterDefinition.setName(filter.getName());
      filterList.add(filterDefinition);
    }
    definition.setFilters(filterList);

    URI uri = UriComponentsBuilder.fromUriString(dynamicRoute.getUri()).build().toUri();
    definition.setUri(uri);
    definition.setOrder(dynamicRoute.getOrder());
    return definition;
  }
}
