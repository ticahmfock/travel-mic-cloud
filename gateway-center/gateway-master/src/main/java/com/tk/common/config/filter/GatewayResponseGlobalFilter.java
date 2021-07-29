package com.tk.common.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: TK
 * @date: 2021/7/22 9:39
 */
@Slf4j
public class GatewayResponseGlobalFilter implements GlobalFilter, Ordered {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpResponse response = exchange.getResponse();
    log.info("-----------响应信息:{}",response);
    return Mono.empty();
  }

  @Override
  public int getOrder() {
    return -1;
  }
}
