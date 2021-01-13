package com.ilyuc.easy_excel.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ilyuc.easy_excel.service.BasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ilyuc
 * @version v 1.0.0
 * @Description 上传 Excel 时自定义监听
 * @date 2021/1/12 11:33
 */
public class BasicExcelListener<T> extends AnalysisEventListener<T> {

    private static Logger LOGGER = LoggerFactory.getLogger(BasicExcelListener.class);

    private BasicService basicService;
    List<T> list = new ArrayList<T>();

    /**
     * 构造时传入 对应的 批处理业务类
     * @param basicService
     */
    public BasicExcelListener(BasicService basicService) {
        this.basicService = basicService;
    }

    /**
     * 批处理阈值2000
     */
    private static final int BATCH_COUNT = 2000;
    /**
     * 计数
     */
    private int count = 0;

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        //获取对应的行数
        int num = analysisContext.readRowHolder().getRowIndex();
        LOGGER.info("处理第{}行",num);
        list.add(t);
        count++;
        if (count >= BATCH_COUNT) {
            saveData();
            list.clear();
            count=0;
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    private void saveData() {
        //调用处理数据的业务类的方法
        if(list.size()>0){
            basicService.saveData(list);
        }
    }
}