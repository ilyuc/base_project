package com.ilyuc.easy_excel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.ilyuc.easy_excel.service.BasicService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author ilyuc
 * @version v 1.0.0
 * @Description
 * @date 2021/1/8 10:39
 */
public class ExcelUtil {

    /**
     * 导出 Excel
     * @param response
     * @param data 需导出的实体类列表
     * @param fileName  导出的文件名
     * @param sheetName  sheet页名
     * @param clazz  实体类字节码对象
     * @throws Exception
     */
    public static void writeExcel(HttpServletResponse response, List<? extends Object> data, String fileName, String sheetName, Class clazz) throws Exception {

       /* 1.使用简单样式，并通过浏览器保存
        //表头样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        //设置表头居中对齐
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置内容靠左对齐
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        EasyExcel.write(getOutputStream(fileName, response), clazz)
                .excelType(ExcelTypeEnum.XLSX).sheet(sheetName)
                .registerWriteHandler(horizontalCellStyleStrategy)
                .doWrite(data);
         */

        /* 2.使用自定义样式，通过绝对路径保存
        String fileName = "C:\\Users\\Administrator\\Desktop\\customHandlerWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, clazz)
                .registerWriteHandler(CellStyleUtil.getHorizontalCellStyleStrategy())
                .registerWriteHandler(new CustomRowWriteHandler())
                .sheet(sheetName)
                .doWrite(data);
         */

        // 3.使用自定义样式，并通过浏览器保存
        EasyExcel.write(getOutputStream(fileName, response), clazz)
                .registerWriteHandler(CellStyleUtil.getHorizontalCellStyleStrategy())
                .registerWriteHandler(new CustomRowWriteHandler())
                .sheet(sheetName)
                .doWrite(data);
    }

    private static ServletOutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        response.addHeader("Access-Control-Expose-Headers", "Content-disposition");
        return response.getOutputStream();
    }

    /**
     * 单页读取导入
     * @param excel 导入的excel
     * @param basicService 业务处理类
     * @param clazz  实体类
     * @param sheetNo  页面号
     * @param <T>
     */
    public static <T>void readExcel(MultipartFile excel, BasicService basicService, Class<T> clazz, int sheetNo) {
        BasicExcelListener excelListener = new BasicExcelListener(basicService);
        ExcelReader excelReader = getReader(excel,clazz,excelListener);
        if (excelReader == null) {
            return ;
        }
        ReadSheet readSheet = EasyExcel.readSheet(sheetNo).build();
        excelReader.read(readSheet);
        excelReader.finish();
    }

    /**
     * 多页读取导入
     * @param excel 导入的excel
     * @param basicService 业务数据处理类
     * @param clazz  实体类
     * @param <T>
     */
    public static <T> void readExcel(MultipartFile excel, BasicService basicService,Class<T> clazz) {

        BasicExcelListener excelListener = new BasicExcelListener(basicService);
        ExcelReader excelReader = getReader(excel,clazz,excelListener);

        if (excelReader == null) {
            return ;
        }

        List<ReadSheet> readSheetList = excelReader.excelExecutor().sheetList();

        for (ReadSheet readSheet:readSheetList){
            excelReader.read(readSheet);
        }
        excelReader.finish();
    }

    /**
     * 返回 ExcelReader
     * @param excel 文件
     * @param clazz 实体类
     * @param excelListener
     */
    private static <T> ExcelReader getReader(MultipartFile excel, Class<T> clazz, BasicExcelListener excelListener) {
        String filename = excel.getOriginalFilename();
        try {
            if (filename == null || (!filename.toLowerCase().endsWith(".xls") && !filename.toLowerCase().endsWith(".xlsx"))) {
                return null;
            }
            InputStream inputStream = new BufferedInputStream(excel.getInputStream());
            ExcelReader excelReader = EasyExcel.read(inputStream, clazz, excelListener).build();
            inputStream.close();
            return excelReader;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}