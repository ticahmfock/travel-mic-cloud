package com.tk.common.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: TK
 * @date: 2021/8/3 10:36
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().
        and().csrf().disable();
    http.authorizeRequests()
        .antMatchers("/oauth/**").permitAll();
  }

  @Override
  protected UserDetailsService userDetailsService() {
    return super.userDetailsService();
  }
}
