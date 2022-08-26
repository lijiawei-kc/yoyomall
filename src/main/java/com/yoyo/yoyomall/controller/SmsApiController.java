package com.yoyo.yoyomall.controller;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.utils.StringUtils;
import com.yoyo.yoyomall.utils.R;
import com.yoyo.yoyomall.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login/sms")
@CrossOrigin  //跨域
public class SmsApiController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/send")
    public R smsApi(String phone) throws ClientException {
        //检验验证码是否过期
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return R.ok().msg("验证码已存在，还未过期").data("code",code);
        }

        //生成验证码
        int hashCode = UUID.randomUUID().toString().hashCode();
        code = String.valueOf(hashCode).substring(1,5);

        boolean isSend = SMSUtils.sendShortMessage(phone, code);

        if(isSend){
            //将验证码存储到redis中
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok().msg("验证码发送成功!").data("code",code);
        }
        else{
            return R.error().msg("验证码发送失败!");
        }
    }



}
