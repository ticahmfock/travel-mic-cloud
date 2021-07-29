package com.tk.common.config.route;

import com.alibaba.fastjson.JSON;
import constant.StringConstant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**将定义的路由表数据通过此类读取到redis中
 * @author: TK
 * @date: 2021/7/23 16:10
 */
@Slf4j
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

  @Resource
  private StringRedisTemplate redisTemplate;
  /**
   * 获取路由信息
   * @return
   */
  @Override
  public Flux<RouteDefinition> getRouteDefinitions() {
    final List<RouteDefinition> routeDefinitions = new ArrayList<RouteDefinition>();
    redisTemplate.opsForHash().values(StringConstant.GATEWAY_ROUTES).stream().forEach(routeDefinition -> {
      routeDefinitions.add(JSON.parseObject(routeDefinition.toString(),RouteDefinition.class));
    });
    return Flux.fromIterable(routeDefinitions);
  }

  /**
   * 设置路由信息
   * @param route
   * @return
   */
  @Override
  public Mono<Void> save(Mono<RouteDefinition> route) {
    return route
        .flatMap(routeDefinition -> {
          redisTemplate.opsForHash().put(StringConstant.GATEWAY_ROUTES, routeDefinition.getId(),
              JSON.toJSONString(routeDefinition));
          return Mono.empty();
        });
  }

  /**
   * 删除路由信息
   * @param routeId
   * @return
   */
  @Override
  public Mono<Void> delete(Mono<String> routeId) {
    return routeId.flatMap(id -> {
      if (redisTemplate.opsForHash().hasKey(StringConstant.GATEWAY_ROUTES, id)) {
        redisTemplate.opsForHash().delete(StringConstant.GATEWAY_ROUTES, id);
        return Mono.empty();
      }
      return Mono.defer(() -> Mono.error(new NotFoundException("route definition is not found, routeId: " + routeId)));
    });
  }
}
