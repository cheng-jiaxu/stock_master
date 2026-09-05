package com.ming.stock.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.ming.stock.utils.IdWorker;
import com.ming.stock.vo.StockInfoConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;


/**
 * @Description: 配置公共类
 */
@Configuration
public class CommonConfig {
    /**
     * 密码加密器
     * BCryptPassWordEncoder方法采用SHA-256对密码进行加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * 配置id生成器bean
     * @return
     */
    @Bean
    public IdWorker idWorker(){
        //参数1:机器id 参数2:机房id 一般有运维人员定唯一性
        //基于运维人员对机房和机器的编号自行约定
        return new IdWorker(1L,2L);
    }
    @Bean
    public StockInfoConfig stockInfoConfig() {
        return new StockInfoConfig();
    }

    /**
     * 构建缓存bean
     * @return
     */
    @Bean
    public Cache<String,Object> caffeineCache(){
        Cache<String,Object> cache = Caffeine.newBuilder()
                .maximumSize(200)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .expireAfterWrite(1,TimeUnit.SECONDS)
                .initialCapacity(100)
                .recordStats().build();
        return cache;
    }
}
