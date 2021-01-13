package com.ilyuc.easy_excel.controller;

import com.ilyuc.easy_excel.entity.UserEntity;
import com.ilyuc.easy_excel.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ilyuc
 * @version v 1.0.0
 * @Description
 * @date 2021/1/6 10:31+
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    private static Logger logger= LoggerFactory.getLogger(UserController.class);
    @RequestMapping("/test")
    public String test(){

        logger.info("测试日志 info 级别！");
        logger.debug("测试日志 debug 级别！");
        logger.error("测试日志 error 级别！");
        return "success";
    }

    @PostMapping("/get")
    public List<UserEntity> queryUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("张三");
        return userService.getUserList(userEntity);
    }

}