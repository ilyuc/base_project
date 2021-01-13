package com.ilyuc.easy_excel.service.Impl;

import com.ilyuc.easy_excel.entity.UserEntity;
import com.ilyuc.easy_excel.service.BasicService;
import com.ilyuc.easy_excel.service.UserService;
import com.ilyuc.easy_excel.util.BeanConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ilyuc
 * @version v 1.0.0
 * @Description 批处理业务类
 * @date 2021/1/12 14:49
 */
@Service
public class UserBasicServiceImpl implements BasicService {

    @Autowired
    private UserService userService;

    /**
     * @Author ilyuc
     * @Description  批处理保存操作
     * @Date 14:52 2021/1/12
     * @Param [list]
     * @return void
     */
    @Override
    public <T> void saveData(List<T> list) {
        List<UserEntity> userEntities = BeanConvert.objectConvertBean(list, UserEntity.class);
        userEntities.forEach(System.out::println);
        //调用各个处理数据的业务类
        //userService.saveEasyExcelData(userEntities);
    }
}