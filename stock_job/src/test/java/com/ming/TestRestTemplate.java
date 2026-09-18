package com.ming;

import com.ming.stock.service.StockTimerTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestRestTemplate {
    @Autowired
    private StockTimerTaskService stockTimerTaskService;


    @Test
    public void Test01(){
        stockTimerTaskService.getInnerMarketInfo();
    }
    @Test
    public void Test02(){
        stockTimerTaskService.getStockRtIndex();
    }
    @Test void Test03() throws InterruptedException{
        stockTimerTaskService.getStockSectorRtIndex();
    }
}
