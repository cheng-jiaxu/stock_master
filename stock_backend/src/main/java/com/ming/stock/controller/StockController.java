package com.ming.stock.controller;

import com.ming.stock.domain.*;
import com.ming.stock.service.StockService;
import com.ming.stock.vo.resp.PageResult;
import com.ming.stock.vo.resp.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "股票行情", description = "大盘、个股、板块及外盘行情相关接口")
public class StockController {
    @Autowired
    private StockService stockService;

    @Operation(summary = "查询内盘大盘指数行情", description = "查询上证指数、深证成指等内盘大盘的最新有效行情")
    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> getInnerMarketInfo(){
        return stockService.getInnerMarketInfo();
    }

    @Operation(summary = "查询热门股票板块", description = "查询最新有效交易时间点下成交量靠前的股票板块")
    @GetMapping("/sector/all")
    public R<List<StockBlockDomain>> sectorAllLimit(){
        return  stockService.sectorAllLimit();
    }

    @Operation(summary = "分页查询个股涨跌行情", description = "分页查询最新有效交易时间点下的个股行情及涨跌数据")
    @GetMapping("/stock/all")
    public R<PageResult<StockUpdownDomain>> getStockInfoByPage(
            @Parameter(description = "页码", example = "1") @RequestParam(name = "Page", required = false, defaultValue = "1") Integer Page,
            @Parameter(description = "每页记录数", example = "20") @RequestParam(name = "PageSize", required = false, defaultValue = "20") Integer PageSize){
        return stockService.getStockInfoByPage(Page,PageSize);
    }
    @Operation(summary = "查询涨幅榜", description = "查询最新有效交易时间点下涨幅最高的个股")
    @GetMapping("stock/increase")
    public R<List<StockUpdownDomain>> getTopStocksByIncrease(){
        return stockService.getTopStocksByIncrease();
    }
    @Operation(
            summary = "查询每分钟涨停或者跌停数量",
            description = "查询最近有效交易日内每分钟涨停和跌停的数量"
    )
    @GetMapping("stock/updown/count")
    public R<Map<String,List>> getStockUpDownCount(){
        return stockService.getStockUpDownCount();
    }
    @Operation(summary = "导出个股涨跌行情", description = "按分页条件导出最新有效交易时间点下的个股涨跌行情 Excel 文件")
    @GetMapping("/stock/export")
    public void exportsStockUpDownInfo(
            @Parameter(description = "页码", example = "1") @RequestParam(name="page",required = false,defaultValue = "1")Integer Page,
            @Parameter(description = "每页记录数", example = "20") @RequestParam(name = "PageSize",required = false,defaultValue = "20") Integer PageSize,
            HttpServletResponse response){
        stockService.exportStockUpdownInfo(Page,PageSize,response);
    }
    @Operation(summary = "对比大盘成交额", description = "查询当日与上一交易日的大盘累计成交额，用于分时对比图")
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
    @Operation(summary = "查询个股分时行情", description = "查询指定股票在最近有效交易日内的分钟级行情数据")
    @GetMapping("/stock/screen/time-sharing")
    public R<List<Stock4MinuteDomain>>
    getStockScreenTimeSharing(@Parameter(name = "code", description = "股票编码", in = ParameterIn.QUERY, required = true, example = "600000") @RequestParam(value = "code",required = true) String stockCode){
        return stockService.getStockScreenTimeSharing(stockCode);
    }
    @Operation(summary = "查询个股日 K 线行情", description = "查询指定股票的日 K 线数据")
    @RequestMapping("/stock/screen/dkline")
    public R<List<Stock4MinuteDomain>> getStockScreenDkLine(@RequestParam(value
            = "code",required = true) @Parameter(name = "code", description = "股票编码", in = ParameterIn.QUERY, required = true, example = "600000") String stockCode){
        return stockService.getStock4DkLine(stockCode);
    }
    @Operation(summary = "查询外盘指数行情", description = "查询配置的外盘指数在当前时刻之前的最新行情")
    @RequestMapping("/external/index")
    public R<List<OuterMarketDomain>> getOuterMarketInfo(){
       return stockService.getOuterMarketInfo();
    }
    @Operation(summary = "股票代码搜索联想", description = "根据输入的股票代码前缀进行模糊查询，返回股票编码和股票名称")
    @RequestMapping("/stock/search")
    public R<List<StockSearchDomain>> searchStock(@Parameter(description = "股票代码前缀", required = true, example = "600") @RequestParam("searchStr")String searchStr){
        return stockService.searchStock(searchStr);
    }



}
