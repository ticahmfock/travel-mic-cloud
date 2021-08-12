package com.tk.common.config.server;

import com.tk.common.config.client.RedisClientDetailService;
import com.tk.common.config.token.RedisTokenStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.annotation.Resource;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

/**
 * oauth认证服务
 *
 * @author: TK
 * @Time: 2021/8/1 10:23
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Resource
  private UserDetailsService userDetailsService;
  @Resource
  private RedisClientDetailService redisClientDetailService;
  @Resource
  private RedisTokenStore redisTokenStore;

  /**
   * 用来配置令牌端点(Token Endpoint)的安全约束.
   *
   * @param security
   * @throws Exception
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()")
        //主要是让/oauth/token支持client_id以及client_secret作登录认证
        .allowFormAuthenticationForClients();//允许表单认证
  }

  /**
   * 配置客户端详细信息
   *
   * @param clients
   * @throws Exception
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    log.info("----------初始化客户端配置信息----------");
    clients.withClientDetails(redisClientDetailService);
    redisClientDetailService.loadAllClientToCache();
  }

  /**
   * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
   *
   * @param endpoints
   * @throws Exception
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.userDetailsService(userDetailsService);
    log.error("-------------------加载授权和令牌端点-----------------------");
  }

  /**
   * 自定义tokenService
   * @return
   */
  @Bean
  @Primary
  public DefaultTokenServices tokenService() {
    log.info("----------自定义token配置服务信息----------");
    DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setTokenStore(redisTokenStore);
    tokenServices.setSupportRefreshToken(true);
    return tokenServices;
  }
}
