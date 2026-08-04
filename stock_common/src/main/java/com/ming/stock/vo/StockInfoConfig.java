package com.ming.stock.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "stock")
@Data
public class StockInfoConfig {
    private List<String> inner;
    private List<String> outer;

}
