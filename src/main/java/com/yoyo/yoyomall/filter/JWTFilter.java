package com.yoyo.yoyomall.filter;

import com.mysql.cj.util.StringUtils;
import com.yoyo.yoyomall.entity.Admin;
import com.yoyo.yoyomall.entity.vo.AdminVo;
import com.yoyo.yoyomall.service.AdminService;
import com.yoyo.yoyomall.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = httpServletRequest.getHeader("access_token");
        System.out.println(token);
        if(!StringUtils.isNullOrEmpty(token)){
            String jwtToken = JwtUtils.getInfoByJwtToken(token);
            String s = jwtToken.substring(jwtToken.lastIndexOf("Username: "));
            String account=s.substring(10,s.indexOf(59));
            ValueOperations<String, AdminVo> redis = redisTemplate.opsForValue();
            AdminVo adminVo = redis.get(account);
//            AdminVo adminVo = adminService.selectInfoByAccount(account);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            List<String> roles = adminVo.getRole();
            for (int i = 0; i < roles.size(); i++) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.get(i));
                authorities.add(authority);
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(adminVo.getAccount(),adminVo.getPassword(),authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//todO   不通过数据库取数据而是通过redis

        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
