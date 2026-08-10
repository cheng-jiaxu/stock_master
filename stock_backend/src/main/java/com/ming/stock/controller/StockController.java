package com.ming.stock.controller;

import com.ming.stock.domain.InnerMarketDomain;
import com.ming.stock.domain.Stock4MinuteDomain;
import com.ming.stock.domain.StockBlockDomain;
import com.ming.stock.domain.StockUpdownDomain;
import com.ming.stock.service.StockService;
import com.ming.stock.vo.resp.PageResult;
import com.ming.stock.vo.resp.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: 定义股票相关接口控制器
 */
@RestController
@RequestMapping("/api/quot")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> getInnerMarketInfo(){
        return stockService.getInnerMarketInfo();
    }

    @GetMapping("/sector/all")
    public R<List<StockBlockDomain>> sectorAllLimit(){
        return  stockService.sectorAllLimit();
    }

    @GetMapping("/stock/all")
    public R<PageResult<StockUpdownDomain>> getStockInfoByPage(@RequestParam(name = "Page",required = false,defaultValue = "1") Integer Page, @RequestParam(name = "PageSize",required = false,defaultValue = "20") Integer PageSize){
        return stockService.getStockInfoByPage(Page,PageSize);
    }
    @GetMapping("stock/increase")
    public R<List<StockUpdownDomain>> getTopStocksByIncrease(){
        return stockService.getTopStocksByIncrease();
    }
    @Operation(
            summary = "查询每分钟涨停或者跌停数量",
            description = "查询指定时间范围内每分钟涨停或者跌停的数量；flag=1涨停，flag=0跌停，开始结束时间建议同一天"
    )
    @GetMapping("stock/updown/count")
    public R<Map<String,List>> getStockUpDownCount(){
        return stockService.getStockUpDownCount();
    }
    @GetMapping("/stock/export")
    public void exportsStockUpDownInfo(@RequestParam(name="page",required = false,defaultValue = "1")Integer Page, @RequestParam(name = "PageSize",required = false,defaultValue = "20") Integer PageSize, HttpServletResponse response){
        stockService.exportStockUpdownInfo(Page,PageSize,response);
    }
    @GetMapping("stock/tradeAmt")
    public R<Map<String,List>> getComparedStockTradeAmt(){
        return stockService.getComparedStockTradeAmt();
    }
    @Operation(summary = "统计最新交易时间点下股票(A股) 在各个涨幅区间的数量",
            description = "统计最新交易时间点下股票(A股) 在各个涨幅区间的数量")
    @GetMapping("/stock/updown")
    public R<Map> getIncreaseRangeInfo(){
        return stockService.getIncreaseRangeInfoByDate();
    }
    @Parameter(name = "stockCode", description = "股票编码", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "功能描述：查询单个个股的分时行情数据，也就是统计指定股票T日每分钟 的交易数据；如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询时间点 总结:获取指定股票T日分时数据", description = "功能描述：查询单个个股的分时行情数据，也就 是统计指定股票T日每分钟的交易数据；        如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询时间点   总结:获取指定股票T日分时数据")
    @GetMapping("/stock/screen/time-sharing")
    public R<List<Stock4MinuteDomain>>
    getStockScreenTimeSharing(@RequestParam(value = "code",required = true) String stockCode){
        return stockService.getStockScreenTimeSharing(stockCode);
    }

}
