package com.ilyuc.easy_excel.controller;

import com.ilyuc.easy_excel.entity.UserEntity;
import com.ilyuc.easy_excel.service.Impl.UserBasicServiceImpl;
import com.ilyuc.easy_excel.service.UserService;
import com.ilyuc.easy_excel.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ilyuc
 * @version v 1.0.0
 * @Description
 * @date 2021/1/8 10:42
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    UserService userService;

    /**
     * 下载 Excel
     * @param response
     */
    @GetMapping("/download")
    public void downloadTemplate(HttpServletResponse response){

        String fileName = "文件名";
        String sheetName="sheet页名";
        List<UserEntity> userList = userService.getUserList(new UserEntity());
        try {
            //UserEntity.class对应你的模板实体类
            ExcelUtil.writeExcel(response,userList,fileName,sheetName,UserEntity.class);
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }

    /**
     * 上传 Excel
     * @param excel
     * @return
     */
    @PostMapping(value = "/upload")
    public String read(MultipartFile excel) {
        ExcelUtil.readExcel(excel,new UserBasicServiceImpl(), UserEntity.class, 0);
        return "Excel import successful";
    }

}