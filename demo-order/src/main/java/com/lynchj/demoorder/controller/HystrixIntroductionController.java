package com.lynchj.demoorder.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：大漠知秋
 * @Description：Hystrix 入门案例
 * @CreateDate：10:23 AM 2018/10/29
 */
@RestController
@RequestMapping("/hystrixIntroduction")
public class HystrixIntroductionController {

    @RequestMapping(value = "/getUser")
    @HystrixCommand(fallbackMethod = "fallbackGetUser")
    public String getUser(String username) {

        if ("SpringCloud".equals(username)) {
            return "Hello " + username;
        } else {
            throw new RuntimeException("哈哈哈，出错了");
//            throw new HystrixBadRequestException("Test");
        }

    }

    public String fallbackGetUser(String username, Throwable throwable) {
        System.err.println(throwable.fillInStackTrace());
        return "No Hello " + username;
    }

}
