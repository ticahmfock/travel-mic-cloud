package com.tk.common.model;

import constant.StringConstant;
import java.util.Collection;
import model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author: TK
 * @date: 2021/8/12 14:14
 */
public class LoginUser extends User implements UserDetails {

  private static final long serialVersionUID = -180478943193726767L;

  /**
   * 返回授予用户的权限
   *
   * @return
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    return null;
  }

  /**
   * 返回用于验证用户身份的密码
   *
   * @return
   */
  @Override
  public String getPassword() {
    return getPassword();
  }

  /**
   * 返回用于验证用户身份的用户名
   *
   * @return
   */
  @Override
  public String getUsername() {
    return getUserName();
  }

  /**
   * 指示用户的帐户是否已过期。无法删除过期的帐户认证
   *  true:帐户有效
   *  false:账户无效
   * @return
   */
  @Override
  public boolean isAccountNonExpired() {
    if (StringConstant.NORMAL.equals(getStatus())){
      return true;
    }
    return false;
  }

  /**
   * 指示用户是否已锁定或未锁定。无法锁定已锁定的用户认证
   * true:用户未被锁定
   * false:用户被锁定
   * @return
   */
  @Override
  public boolean isAccountNonLocked() {
    if (StringConstant.NORMAL.equals(getStatus())){
      return true;
    }
    return false;
  }

  /**
   * 指示用户的凭据（密码）是否已过期,期满凭据阻止身份验证
   * true:用户的凭据有效
   * false:用户的凭据无效
   * @return
   */
  @Override
  public boolean isCredentialsNonExpired() {
    if (StringConstant.NORMAL.equals(getStatus())){
      return true;
    }
    return false;
  }

  /**
   * 指示用户是启用还是禁用,无法对禁用的用户进行身份验证
   * true:用户可用
   * false:用户不可用
   * @return
   */
  @Override
  public boolean isEnabled() {
    if (StringConstant.NORMAL.equals(getStatus())){
      return true;
    }
    return false;
  }
}
