package com.ming.stock.job.jobhandler;

import com.ming.stock.service.StockTimerTaskService;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockJob {
    @Autowired
    private StockTimerTaskService stockTimerTaskService;

    @XxlJob("hema_job_test")
    public void JobTest(){
        System.out.println("jobTest run...");
    }

    @XxlJob("getInnerMarketInfo")
    public void getStockInnerMarketInfos(){
        stockTimerTaskService.getInnerMarketInfo();
    }
    @XxlJob("getStockInfos")
    public void getStockInfos(){
        stockTimerTaskService.getStockRtIndex();
    }
    @XxlJob("getStockBlockInfoTask")
    public void getStockBlockInfoTask() {
        stockTimerTaskService.getStockSectorRtIndex();
    }

    }
