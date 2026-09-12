package com.ming.stock.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Schema(description = "定义外盘行情对象")
public class OuterMarketDomain {

    @Schema(description = "外盘编码")
    private String code;

    @Schema(description = "外盘名称")
    private String name;

    @Schema(description = "当前点位")
    private BigDecimal curPoint;

    @Schema(description = "涨跌值")
    private BigDecimal upDown;

    @Schema(description = "涨幅")
    private BigDecimal rose;

    @Schema(description = "当前日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date curDate;


}
