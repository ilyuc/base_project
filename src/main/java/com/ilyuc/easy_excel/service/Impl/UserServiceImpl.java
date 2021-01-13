package com.ilyuc.easy_excel.service.Impl;

import com.ilyuc.easy_excel.dao.UserMapper;
import com.ilyuc.easy_excel.entity.UserEntity;
import com.ilyuc.easy_excel.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ilyuc
 * @version v 1.0.0
 * @Description
 * @date 2021/1/6 19:28
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public List<UserEntity> getUserList(UserEntity userEntity){
        return userMapper.getUser(userEntity);
    }

    /**
     * 处理数据
     * @param userList
     */
    @Override
    public void saveEasyExcelData(List<UserEntity> userList) {

        userList.forEach(userEntity -> System.out.println(userEntity));
//        userMapper.insert()
    }
}