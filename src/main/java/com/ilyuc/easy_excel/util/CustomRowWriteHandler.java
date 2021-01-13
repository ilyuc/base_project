package com.ilyuc.easy_excel.util;

import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ilyuc
 * @version v 1.0.0
 * @Description 自定义行拦截器
 * @date 2021/1/8 11:19
 */
public class CustomRowWriteHandler implements RowWriteHandler {

    private static Logger LOGGER= LoggerFactory.getLogger(CustomRowWriteHandler.class);
    /**
     * 一定将样式设置成全局变量
     * 首行只需要创建一次样式就可以 不然每行都创建一次 数据量大的话会保错
     * 异常信息：The maximum number of Cell Styles was exceeded. You can define up to 64000 style in a .xlsx Workbook
     */
    private CellStyle firstCellStyle;

    /**
     * 列号
     */
    private int count = 0;

    @Override
    public void beforeRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, int i, int i1, boolean b) {

    }

    /**
     * @Author ilyuc
     * @Description 给首列加序号和样式
     * @Date 15:00 2021/1/8
     * @Param [writeSheetHolder, writeTableHolder, row, i, b]
     * @return void
     */
    @Override
    public void afterRowCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, int i, boolean b) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("第{"+row.getRowNum()+"}行创建完毕！");
        }
        if(row.getRowNum()==0){
            return;
        }

        Cell cell = row.createCell(0);
        if (firstCellStyle == null) {
            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();

            firstCellStyle = CellStyleUtil.firstCellStyle(workbook);
            LOGGER.info("设置首列样式成功");
        }
        cell.setCellStyle(firstCellStyle);
        //设置列宽  0列   10个字符宽度
        writeSheetHolder.getSheet().setColumnWidth(0, 10 * 256);
//       实体类中没有首列 序号 标题时候，可以使用下面代码，且还需加标题样式
//        if (row.getRowNum() == 0) {
//            cell.setCellValue("序号");
//            return;
//        }

        cell.setCellValue(++count);
    }
}