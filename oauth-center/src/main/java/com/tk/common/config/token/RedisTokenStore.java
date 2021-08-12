package com.tk.common.config.token;

import com.tk.common.model.LoginUser;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

/**
 * 重新实现redisTokenStore
 *
 * @author: TK
 * @date: 2021/8/11 16:50
 */
@Slf4j
@Component
public class RedisTokenStore implements TokenStore {

  @Resource
  private RedisTemplate<String, Object> redisTemplate;

  private static final String ACCESS = "access:";
  private static final String AUTH_TO_ACCESS = "auth_to_access:";
  private static final String AUTH = "auth:";
  private static final String REFRESH_AUTH = "refresh_auth:";
  private static final String ACCESS_TO_REFRESH = "access_to_refresh:";
  private static final String REFRESH = "refresh:";
  private static final String REFRESH_TO_ACCESS = "refresh_to_access:";
  private static final String CLIENT_ID_TO_ACCESS = "client_id_to_access:";
  private static final String UNAME_TO_ACCESS = "uname_to_access:";

  /**
   * 从数据库中获取认证信息的策略接口
   */
  private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

  public void setAuthenticationKeyGenerator(AuthenticationKeyGenerator authenticationKeyGenerator) {
    this.authenticationKeyGenerator = authenticationKeyGenerator;
  }

  /**
   * 获取accessToken
   *
   * @param authentication
   * @return
   */
  @Override
  public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
    log.info("----------获取accessToken----------");
    //标识身份验证的唯一密钥
    String key = authenticationKeyGenerator.extractKey(authentication);
    OAuth2AccessToken accessToken = (OAuth2AccessToken) redisTemplate.opsForValue().get(AUTH_TO_ACCESS.concat(key));
    if (accessToken != null) {
      OAuth2Authentication storedAuthentication = readAuthentication(accessToken.getValue());
      if ((storedAuthentication == null || !key.equals(authenticationKeyGenerator.extractKey(storedAuthentication)))) {
        //保持存储一致
        storeAccessToken(accessToken, authentication);
      }
    }
    return accessToken;
  }

  /**
   * 读取存储在指定令牌值下的身份验证，重新认证
   *
   * @param token
   * @return
   */
  @Override
  public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
    return readAuthentication(token.getValue());
  }

  /**
   * 读取存储在指定令牌值下的身份验证，重新认证
   *
   * @param token
   * @return
   */
  @Override
  public OAuth2Authentication readAuthentication(String token) {
    return (OAuth2Authentication) this.redisTemplate.opsForValue().get(AUTH + token);
  }

  /**
   * 存储访问令牌
   *
   * @param token
   * @param authentication
   */
  @Override
  public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
    //获取当前accessToken
    OAuth2AccessToken currentAccessToken = getAccessToken(authentication);
    //存储令牌信息
    redisTemplate.opsForValue().set(ACCESS.concat(token.getValue()), token);
    redisTemplate.opsForValue().set(AUTH.concat(token.getValue()), authentication);
    redisTemplate.opsForValue().set(AUTH_TO_ACCESS.concat(authenticationKeyGenerator.extractKey(authentication)), token);
    //为自定义类别token添加相关信息
    Map<String, Object> map = new ConcurrentHashMap<>();
    map.put("clientId", authentication.getOAuth2Request().getClientId());
    //用户名密码身份验证令牌
    if (authentication.getUserAuthentication() instanceof UsernamePasswordAuthenticationToken) {
      UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication.getUserAuthentication();
      LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
      map.put("userName",loginUser.getUsername());
      map.put("authorities",loginUser.getAuthorities());
    }

  }

  /**
   * 从存储中读取访问令牌
   *
   * @param tokenValue
   * @return
   */
  @Override
  public OAuth2AccessToken readAccessToken(String tokenValue) {
    OAuth2AccessToken accessToken = (OAuth2AccessToken) redisTemplate.opsForValue().get(ACCESS.concat(tokenValue));
    return accessToken;
  }

  @Override
  public void removeAccessToken(OAuth2AccessToken token) {

  }

  @Override
  public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {

  }

  @Override
  public OAuth2RefreshToken readRefreshToken(String tokenValue) {
    return null;
  }

  @Override
  public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
    return null;
  }

  @Override
  public void removeRefreshToken(OAuth2RefreshToken token) {

  }

  @Override
  public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {

  }

  @Override
  public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
    return null;
  }

  @Override
  public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
    return null;
  }
}
