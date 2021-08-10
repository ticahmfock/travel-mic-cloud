package com.tk.common.config.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.annotation.Resource;
import javax.sql.DataSource;

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
  private DataSource dataSource;

  /**
   * 用来配置令牌端点(Token Endpoint)的安全约束.
   * @param security
   * @throws Exception
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()")
        //主要是让/oauth/token支持client_id以及client_secret作登录认证
        .allowFormAuthenticationForClients();
    log.error("---------------加载令牌端点---------------------");
  }

  /**
   * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化
   *
   * @param clients
   * @throws Exception
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.withClientDetails(new JdbcClientDetailsService(dataSource));
    log.error("------------------加载客户端详情--------------------------");
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
}
