package com.yoyo.yoyomall.filter;

import com.mysql.cj.util.StringUtils;
import com.yoyo.yoyomall.entity.Admin;
import com.yoyo.yoyomall.entity.User;
import com.yoyo.yoyomall.entity.vo.AdminVo;
import com.yoyo.yoyomall.service.AdminService;
import com.yoyo.yoyomall.utils.JwtUtils;
import com.yoyo.yoyomall.utils.YoyoException;
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
import springfox.documentation.service.ResourceGroup;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate redisTemplate;

    private static ReentrantLock l = new ReentrantLock();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        l.lock();
        try {
            String token = httpServletRequest.getHeader("Access_token");

            cd:if (!StringUtils.isNullOrEmpty(token)) {
                //user用户登录
                String requestURI = httpServletRequest.getRequestURI();
                requestURI = requestURI.substring(0, requestURI.lastIndexOf("/"));
                if(requestURI.equals("/web/user")){
                    String nickname = JwtUtils.getInfoByJwtToken(token);
                    ValueOperations<String, User> redis = redisTemplate.opsForValue();
                    User user = redis.get(nickname);
                    if(user==null){
                        throw new YoyoException(20001,"认证出错了");
                    }
                    break cd;
                }
                //admin用户登录
//              token=token.substring(7);
                String jwtToken = JwtUtils.getInfoByJwtToken(token);
                String s = jwtToken.substring(jwtToken.lastIndexOf("Username: "));
                String account = s.substring(10, s.indexOf(59));

                ValueOperations<String, AdminVo> redis = redisTemplate.opsForValue();
                AdminVo adminVo = redis.get(account);

//            AdminVo adminVo = adminService.selectInfoByAccount(account);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                List<String> roles = adminVo.getRole();
                for (int i = 0; i < roles.size(); i++) {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roles.get(i));
                    authorities.add(authority);
                }
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(adminVo.getAccount(), adminVo.getPassword(), authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new YoyoException(20001, "认证出错了");
        } finally {
            l.unlock();
        }

    }
}
