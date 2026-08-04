package com.ming.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Schema(description = ":定义大盘的领域对象")
public class InnerMarketDomain {
    @Schema(description = "大盘编码")
    private String code;

    @Schema(description = "大盘名称")
    private String name;

    @Schema(description = "开盘点")
    private BigDecimal openPoint;

    @Schema(description = "当前点")
    private BigDecimal curPoint;

    @Schema(description = "前收盘点")
    private BigDecimal preClosePoint;

    @Schema(description = "交易量")
    private Long tradeAmt;

    @Schema(description = "交易金额")
    private Long tradeVol;

    @Schema(description = "涨跌值")
    private BigDecimal upDown;

    @Schema(description = "涨幅")
    private BigDecimal rose;

    @Schema(description = "振幅")
    private BigDecimal amplitude;

    @Schema(description = "当前时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm")
    private Date curTime;
}
