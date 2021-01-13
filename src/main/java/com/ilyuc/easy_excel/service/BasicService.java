package com.ilyuc.easy_excel.service;

import java.util.List;

/**
 * @author ilyuc
 * @Description
 * @date 2021/1/12 14:48
 */
public interface BasicService {
    <T> void saveData(List<T> list);
}
