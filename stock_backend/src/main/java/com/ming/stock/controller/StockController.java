package com.ming.stock.controller;

import com.ming.stock.domain.InnerMarketDomain;
import com.ming.stock.domain.StockBlockDomain;
import com.ming.stock.domain.StockUpdownDomain;
import com.ming.stock.service.StockService;
import com.ming.stock.vo.resp.PageResult;
import com.ming.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
