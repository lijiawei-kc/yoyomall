package com.yoyo.yoyomall.config;

import com.yoyo.yoyomall.filter.JWTFilter;
import com.yoyo.yoyomall.utils.YoyoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import java.util.logging.LogRecord;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTFilter jwtFilter;
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.formLogin().disable();//暂停springsecurity起作用.

        http.csrf().disable();
        http.cors();
        http.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);

        http

                .authorizeRequests().antMatchers("/manager/admin/login","/manager/**").anonymous()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//
    }
    /**
     * 高版本的SpringSecurity必须配置一个 "密码编码器"提供给SpringBoot使用
     *
     * @return 哈希密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public class Myfilter extends SecurityProperties.Filter {


    }

}
