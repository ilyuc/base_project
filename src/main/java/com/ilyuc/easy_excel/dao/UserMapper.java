package com.ilyuc.easy_excel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ilyuc.easy_excel.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ilyuc
 * @version v 1.0.0
 * @Description
 * @date 2021/1/6 19:23
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    List<UserEntity> getUser(UserEntity userEntity);

}