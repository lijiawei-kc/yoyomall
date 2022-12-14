package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.entity.Admin;
import com.yoyo.yoyomall.entity.vo.AdminVo;
import com.yoyo.yoyomall.service.AdminRoleService;
import com.yoyo.yoyomall.service.AdminService;
import com.yoyo.yoyomall.utils.JwtUtils;
import com.yoyo.yoyomall.utils.R;
import com.yoyo.yoyomall.utils.YoyoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/manager/admin")
@CrossOrigin  //跨域
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;

@Autowired
private RedisTemplate redisTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping("/info")
    public R getInfo(String token) {

        try {

            String jwtToken = JwtUtils.getInfoByJwtToken(token);
            String s = jwtToken.substring(jwtToken.lastIndexOf("Username: "));
            String account=s.substring(10,s.indexOf(59));
            ValueOperations<String, AdminVo> redis = redisTemplate.opsForValue();
            AdminVo adminVo = redis.get(account);

            return R.ok().data("info",adminVo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new YoyoException(20001, "获取用户信息失败");
        }

    }
    // 把token放入redis中,定时保存
    @PostMapping("/login")
    public R login(@RequestBody AdminVo admin) {

        try {

            Authentication authentication = new UsernamePasswordAuthenticationToken(admin.getAccount(), admin.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authentication);

            if (authentication.getPrincipal() == null)
                throw new YoyoException(20001, "认证失败");
            else {


                String jwtToken = JwtUtils.getJwtToken(authenticate.getPrincipal().toString());

                String s = authenticate.getPrincipal().toString().substring(authenticate.getPrincipal().toString().lastIndexOf("Username: "));
                String account=s.substring(10,s.indexOf(59));
                AdminVo adminVo = adminService.selectInfoByAccount(account);
                ValueOperations<String, AdminVo> redis = redisTemplate.opsForValue();


                redis.set(account,adminVo, Duration.ofDays(7));

                return R.ok().data("Access_token",jwtToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new YoyoException(20001, "登陆失败");
        }
    } @PostMapping("/loginout")
    public R loginOut(@RequestBody AdminVo admin) {
        try {Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           JwtUtils.getJwtToken(authentication.getPrincipal().toString()) ;

//            Long userid = loginUser.getUser().getId();
//            redisCache.deleteObject(userid);


redisTemplate.delete(admin.getAccount());

                return R.ok();

        } catch (Exception e) {
            e.printStackTrace();
            throw new YoyoException(20001, "登陆失败");
        }
    }


    @GetMapping("/test")
//    @PreAuthorize("hasAnyAuthority('sys:test','sys:test:test')")
    @PreAuthorize("hasAnyRole('superadmin','test01262')")
    public R test() {
        throw new YoyoException(20001, "////");
//        return R.ok();
    }

    @PreAuthorize("hasAnyAuthority('admin-list')")
    @PostMapping("/save")
    public R save(@RequestBody AdminVo admin) {
        System.out.println(admin);
        try {
            adminService.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            throw new YoyoException(20001, "保存失败");
        }
        return R.ok();
    }

    @PostMapping("/select/{id}")
    @PreAuthorize("hasAnyAuthority('admin-list','admin-save')")
    public R selectById(@PathVariable String id) {
        try {
            Admin admin = adminService.getById(id);
            List<String> roleList = adminRoleService.selectList(admin.getId());
            AdminVo adminVo = new AdminVo();
            BeanUtils.copyProperties(admin, adminVo);
            adminVo.setRole(roleList);
            return R.ok().data("admin", adminVo);
        } catch (Exception e) {
            throw new YoyoException(20001, "查询失败");
        }
    }

    @PreAuthorize("hasAnyAuthority('admin-list')")
    @PostMapping("/selectall")
    public R selectall() {
        try {
            List<AdminVo> list = adminService.selectAll();
            return R.ok().data("list", list);
        } catch (Exception e) {
            throw new YoyoException(20001, "查询失败");
        }
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('admin-list')")
    public R deleteById(@PathVariable String id) {
        try {
            adminService.deleteById(id);
            return R.ok();
        } catch (Exception e) {
            throw new YoyoException(20001, "失败");
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('admin-list','admin-save')")
    public R update(@RequestBody AdminVo admin) {
        try {
            adminService.updateByEntity(admin);

        } catch (Exception e) {
            throw new YoyoException(20001, "保存失败");
        }
        return R.ok();
    }


}

