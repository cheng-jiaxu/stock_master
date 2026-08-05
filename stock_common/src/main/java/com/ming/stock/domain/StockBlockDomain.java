package com.ming.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 股票板块domain
 */
@Schema(description = "定义股票板块对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockBlockDomain {
    @Schema(description = "公司数量")
    private Integer companyNum;

    @Schema(description = "交易量")
    private Long tradeAmt;

    @Schema(description = "板块编码")
    private String code;

    @Schema(description = "平均价格")
    private BigDecimal avgPrice;

    @Schema(description = "板块名称")
    private String name;

    @Schema(description = "当前日期")
    @JsonFormat(pattern = "yy-MM-dd HH:mm")
    private Date curTime;

    @Schema(description = "交易总金额")
    private BigDecimal tradeVol;

    @Schema(description = "涨幅")
    private BigDecimal updownRate;



}
