package com.ming.stock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "stock")
@Data
public class StockInfoConfig {
    //封装国内A股大盘编码合集
    private List<String> inner;
    private List<String> outer;

    @Schema(hidden = true)
    private List<String> upDownRange;
    @Schema(description = "大盘 外盘 个股的公共URL")
    private String marketUrl;
    @Schema(description = "板块采集URL")
    private String blockUrl;

}
