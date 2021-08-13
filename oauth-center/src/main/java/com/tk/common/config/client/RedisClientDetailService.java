package com.tk.common.config.client;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 使用redis存储客户端配置信息
 *
 * @author: TK
 * @date: 2021/8/10 15:42
 */
@Slf4j
@Component
public class RedisClientDetailService extends JdbcClientDetailsService {

  @Resource
  private RedisTemplate<String, Object> redisTemplate;

  public RedisClientDetailService(DataSource dataSource) {
    super(dataSource);
  }

  private static final String CACHE_CLIENT_KEY = "oauth_client_detail";

  /**
   * 通过客户端clientId获取客户端信息
   *
   * @param clientId
   * @return
   * @throws InvalidClientException
   */
  @Override
  public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
    ClientDetails clientDetails = null;
    String value = (String) redisTemplate.boundHashOps(CACHE_CLIENT_KEY).get(clientId);
    if (StringUtils.isEmpty(value)) {
      clientDetails = cacheClientDetails(clientId);
    } else {
      clientDetails = JSONObject.parseObject(value, BaseClientDetails.class);
    }
    return clientDetails;
  }

  /**
   * 更新clientDetails
   *
   * @param clientDetails
   * @throws NoSuchClientException
   */
  @Override
  public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
    super.updateClientDetails(clientDetails);
    cacheClientDetails(clientDetails.getClientId());
  }

  /**
   * 更新ClientSecret
   *
   * @param clientId
   * @param secret
   * @throws NoSuchClientException
   */
  @Override
  public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
    super.updateClientSecret(clientId, secret);
    cacheClientDetails(clientId);
  }

  /**
   * 删除clientDetails
   *
   * @param clientId
   * @throws NoSuchClientException
   */
  @Override
  public void removeClientDetails(String clientId) throws NoSuchClientException {
    super.removeClientDetails(clientId);
    removeRedisCache(clientId);
  }

  /**
   * 通过clientId从数据库中获取ClientDetails并进行缓存
   *
   * @param clientId
   * @return
   */
  private ClientDetails cacheClientDetails(String clientId) {
    ClientDetails clientDetails = null;
    try {
      clientDetails = super.loadClientByClientId(clientId);
      if (clientDetails != null) {
        redisTemplate.boundHashOps(CACHE_CLIENT_KEY).
            put(clientId, JSONObject.toJSONString(clientDetails));
        log.info("缓存clientDetails为:clientId为:{},{}", clientDetails, clientId);
      }
    } catch (NoSuchClientException | InvalidClientException e) {
      log.error("无法从数据库中获取clientDetail数据,clientId为:{}", clientId);
      log.error("异常信息为:{}", e.getMessage());
      e.printStackTrace();
    }
    return clientDetails;
  }

  /**
   * 根据clientId删除redis缓存中clientDetails信息
   *
   * @param clientId
   */
  private void removeRedisCache(String clientId) {
    redisTemplate.boundHashOps(CACHE_CLIENT_KEY).delete(clientId);
  }

  /**
   * 初始化时将oauth_client_details表中的客户端信息读取到缓存中
   */
  public void loadAllClientToCache() {
    if (redisTemplate.hasKey(CACHE_CLIENT_KEY)) {
      return;
    }
    log.info("读取oauth_client_details表中数据导缓存中");
    List<ClientDetails> list = super.listClientDetails();
    if (CollectionUtils.isEmpty(list)) {
      log.error("oauth_client_details表数据为空，数据有误");
      return;
    }
    list.parallelStream().forEach(client -> {
      redisTemplate.boundHashOps(CACHE_CLIENT_KEY)
          .put(client.getClientId(), JSONObject.toJSONString(client));
    });
  }
}
