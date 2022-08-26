package com.yoyo.yoyomall.controller;


import com.yoyo.yoyomall.entity.User;
import com.yoyo.yoyomall.service.UserService;
import com.yoyo.yoyomall.utils.JwtUtils;
import com.yoyo.yoyomall.utils.R;
import com.yoyo.yoyomall.utils.YoyoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author serol
 * @since 2022-08-24
 */
@RestController
@RequestMapping("/web/user")
@CrossOrigin  //跨域
public class WebUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    //TODO 把token放入redis中,定时保存
    @PostMapping("/login")
    public R login(User user) {

        try {
            User userVo = userService.selectByName(user.getNickname());
            if(userVo==null || !userVo.getPassword().equals(user.getPassword())){
                return R.error().msg("用户名或者密码错误");
            }

            String jwtToken = JwtUtils.getJwtToken(user.getNickname());
            ValueOperations<String, User> redis = redisTemplate.opsForValue();
            redis.set(user.getNickname(),userVo, Duration.ofDays(7));
            return R.ok().data("access_token",jwtToken);
        }catch (Exception e){
            e.printStackTrace();
            throw new YoyoException(20001, "登录失败");
        }


    }

    @PostMapping("/loginout")
    public R loginOut(User user) {
        try {

            redisTemplate.delete(user.getNickname());

            return R.ok();

        } catch (Exception e) {
            e.printStackTrace();
            throw new YoyoException(20001, "退出登陆失败");
        }
    }

    @PostMapping("/regist")
    public R save(User user) {
        try {
            userService.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new YoyoException(20001, "保存失败");
        }
        return R.ok().msg("注册成功");
    }

    @PostMapping("/delete")
    public R delete(String id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new YoyoException(20001, "注销失败");
        }
        return R.ok().msg("注销成功");
    }

    @PostMapping("/update")
    public R update(User user) {
        try {
            userService.update(user);

        } catch (Exception e) {
            throw new YoyoException(20001, "修改失败");
        }
        return R.ok().msg("修改成功");
    }

    //TODO
    @GetMapping("/info")
    public R selectInfo(HttpServletRequest request){
        try {
            String info = JwtUtils.getIdByJwtToken(request);
            User user = userService.selectByName(info);
            return R.ok().data("user",user);
        }catch (Exception e){
            return R.error().msg("获取用户信息失败");
        }
    }

    @GetMapping("/select")
    public R selectById(String id){
        try {
            User user = userService.selectById(id);
            return R.ok().data("user",user);
        }catch (Exception e){
            return R.error().msg("获取用户信息失败");
        }
    }

    @GetMapping("/selectall")
    public R selectAll(String name,Integer currentPage,Integer pageSize){
        try {
            List<User> userList = userService.selectAll(name, currentPage, pageSize);
            return R.ok().data("userList",userList);
        }catch (Exception e){
            return R.error();
        }
    }

}

