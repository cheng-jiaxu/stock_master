package com.ming.stock.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "定义股票搜索联想对象")
public class StockSearchDomain {
   @Schema(description = "股票名称")
   private String name;

   @Schema(description = "股票编码")
   private String code;
}
