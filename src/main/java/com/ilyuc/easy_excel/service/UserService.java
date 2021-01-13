package com.ilyuc.easy_excel.service;

import com.ilyuc.easy_excel.entity.UserEntity;

import java.util.List;

/**
 * @author ilyuc
 * @Description
 * @date 2021/1/6 19:28
 */
public interface UserService {

    List<UserEntity> getUserList(UserEntity userEntity);

    void saveEasyExcelData(List<UserEntity> userList);
}
