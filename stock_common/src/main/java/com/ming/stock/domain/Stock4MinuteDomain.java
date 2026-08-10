package com.ming.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "个股分时数据封装")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock4MinuteDomain {
    @Schema(description = "日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "Asia/Shanghai")
    private Date date;
    @Schema(description = "交易数量")
    private Long tradeAmt;
    @Schema(description = "股票编码")
    private String code;
    @Schema(description = "最低价")
    private BigDecimal lowPrice;
    @Schema(description = "前收盘价")
    private BigDecimal preClosePrice;
    @Schema(description = "股票名称")
    private String name;
    @Schema(description = "最高价")
    private BigDecimal highPrice;
    @Schema(description = "开盘价")
    private BigDecimal openPrice;
    @Schema(description = "交易金额")
    private BigDecimal tradeVol;
    @Schema(description = "当前价格")
    private BigDecimal tradePrice;

}
