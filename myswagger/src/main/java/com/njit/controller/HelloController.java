package com.njit.controller;

import com.njit.pojo.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "Hello world";
    }

    @GetMapping("/test/Hellox")
    public String hellox(){
        return "Hello world x";
    }

    @ApiOperation(value = "获取用户信息")
    @PostMapping("/getuser")
    public User getUser(){
        User user = new User();
        user.setUserName("zhanshan");
        user.setPassword("666666");
        return user;
    }

}
