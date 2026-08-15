package com.ming.stock.config;

import com.ming.stock.utils.IdWorker;
import com.ming.stock.utils.ParserStockInfoUtil;
import com.ming.stock.vo.StockInfoConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@EnableConfigurationProperties(StockInfoConfig.class)
@Configuration
public class CommonConfig {

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1L,2L);
    }
    @Bean
    public ParserStockInfoUtil parserStockInfoUtil(){
        return new ParserStockInfoUtil(idWorker());
    }
}
