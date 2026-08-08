package com.ming.stock.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @ExcelProperty(value ={"股票涨幅信息统计表","交易量"} ,index = 7)
    @Schema(description = "交易量")
    private Long tradeAmt;
    @ExcelProperty(value ={"股票涨幅信息统计表","股票编码"} ,index =0 )
    @Schema(description = "股票编码")
    private String code;
    @ExcelProperty(value ={"股票涨幅信息统计表","股票名称"} ,index =1 )
    @Schema(description = "股票名称")
    private String name;
    @ExcelProperty(value = {"股票涨幅信息统计表","交易金额"},index =8)
    @Schema(description = "交易金额")
    private BigDecimal tradeVol;
    @ExcelProperty(value ={"股票涨幅信息统计表","涨跌"} ,index =4 )
    @Schema(description = "涨跌")
    private BigDecimal increase;
    @ExcelProperty(value ={"股票涨幅信息统计表","涨幅"} ,index =5 )
    @Schema(description = "涨幅")
    private BigDecimal upDown;
    @ExcelProperty(value ={"股票涨幅信息统计表","振幅"} ,index =6 )
    @Schema(description = "振幅")
    private BigDecimal amplitude;
    @ExcelProperty(value ={"股票涨幅信息统计表","当前价格"} ,index =2 )
    @Schema(description = "当前价格")
    private BigDecimal tradePrice;
    @ExcelProperty(value ={"股票涨幅信息统计表","前收盘价"} ,index =3 )
    @Schema(description = "前收盘价")
    private BigDecimal preClosePrice;
    @ExcelProperty(value ={"股票涨幅信息统计表","当前日期"} ,index =9 )
    @DateTimeFormat("yyyy-MM-DD HH:mm")
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm")
    @Schema(description = "当前日期")
    private Date curDate;
}
