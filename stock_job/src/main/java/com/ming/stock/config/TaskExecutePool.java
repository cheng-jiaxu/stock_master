package com.ming.stock.config;

import com.ming.stock.vo.TaskThreadPoolInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableConfigurationProperties(TaskThreadPoolInfo.class)
@Slf4j
public class TaskExecutePool {
    private TaskThreadPoolInfo info;

    public TaskExecutePool(TaskThreadPoolInfo info){
        this.info = info;
    }

    @Bean(name = "threadPoolTaskExecutor",destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(info.getCorePoolSize());
        taskExecutor.setMaxPoolSize(info.getMaxPoolSize());
        taskExecutor.setQueueCapacity(info.getQueueCapacity());
        taskExecutor.setKeepAliveSeconds(info.getKeepAliveSeconds());
        taskExecutor.setThreadNamePrefix("StockThread-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
