package com.tk.common.config.service;

import com.tk.common.model.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/** 加载用户特定数据的核心接口
 * @author: TK
 * @date: 2021/8/13 14:10
 */
public abstract class BaseUserDetailService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LoginUser loginUser = getUser(username);
    return loginUser;
  }

  /**
   * 获取用户信息
   * @param var1
   * @return
   */
  protected abstract LoginUser getUser(String var1);
}
