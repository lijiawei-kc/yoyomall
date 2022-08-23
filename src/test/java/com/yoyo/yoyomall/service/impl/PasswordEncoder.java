package com.yoyo.yoyomall.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class
)
@SpringBootTest
public class PasswordEncoder {


    @Autowired
   private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;
    @Test
    public void password(){
        System.out.println(passwordEncoder.encode("admin"));
    }
}
