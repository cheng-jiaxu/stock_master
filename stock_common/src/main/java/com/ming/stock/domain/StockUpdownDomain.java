package com.ming.stock.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 股票涨跌信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "股票涨跌信息")
public class StockUpdownDomain {
    @Schema(description = "交易量")
    private Long tradeAmt;

    @Schema(description = "股票编码")
    private String code;

    @Schema(description = "股票名称")
    private String name;

    @Schema(description = "交易金额")
    private BigDecimal tradeVol;

    @Schema(description = "涨跌")
    private BigDecimal increase;

    @Schema(description = "涨幅")
    private BigDecimal upDown;

    @Schema(description = "振幅")
    private BigDecimal amplitude;

    @Schema(description = "当前价格")
    private BigDecimal tradePrice;

    @Schema(description = "前收盘价")
    private BigDecimal preClosePrice;

    @Schema(description = "当前日期")
    private Date curDate;
}
