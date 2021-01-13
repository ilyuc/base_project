package com.ilyuc.easy_excel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;

/**
 * @author ilyuc
 * @version v 1.0.0
 * @Description
 * @date 2021/1/6 13:50
 */
@ContentRowHeight(18)//EasyExcel的该表格行高
public class UserEntity{

    @ExcelProperty(value = "序号",index = 0)
    private String num;

//    @ExcelProperty(value = "名称",index = 1)
    @ExcelProperty(value = {"合并列1", "名称"},index = 1)
    private String name;

//    @ExcelProperty(value = "绰号",index = 2)
    @ExcelProperty(value = {"合并列1", "绰号"},index = 2)
    private String alisa;

//    @ExcelProperty(value = "年龄",index = 3)
    @ExcelProperty(value = {"合并列2", "年龄"},index = 3)
    private int age;

//    @ExcelProperty(value = "身高",index = 4)
    @ExcelProperty(value = {"合并列2","合并列3", "身高"},index = 4)
    private String hall;

//    @ExcelProperty(value = "性别",index = 5)
    @ExcelProperty(value = {"合并列2","合并列3", "性别"},index = 5)
    private String sex;

    @ExcelProperty(value = "体重",index = 6)
    private double weight;

    @ColumnWidth(30)//EasyExcel的该字段列宽
    @ExcelProperty(value = "邮箱",index = 7)
    private String email;

    public void setName(String name) {
        this.name = name;
    }

    public void setAlisa(String alisa) {
        this.alisa = alisa;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAlisa() {
        return alisa;
    }

    public int getAge() {
        return age;
    }

    public String getHall() {
        return hall;
    }

    public String getSex() {
        return sex;
    }

    public double getWeight() {
        return weight;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "name='" + name + '\'' +
                ", alisa='" + alisa + '\'' +
                ", age='" + age + '\'' +
                ", hall='" + hall + '\'' +
                ", sex='" + sex + '\'' +
                ", weight='" + weight + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}