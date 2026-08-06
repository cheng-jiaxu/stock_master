package com.ming.stock.service;

import com.ming.stock.domain.InnerMarketDomain;
import com.ming.stock.domain.StockBlockDomain;
import com.ming.stock.domain.StockUpdownDomain;
import com.ming.stock.vo.resp.PageResult;
import com.ming.stock.vo.resp.R;

import java.util.List;
import java.util.Map;

public interface StockService {
    /**
     * @Decription:获取国内最新大盘数据
     * @return
     */
    R<List<InnerMarketDomain>> getInnerMarketInfo();
    R<List<StockBlockDomain>> sectorAllLimit();
    R<PageResult<StockUpdownDomain>> getStockInfoByPage(Integer Page, Integer PageSize);
    R<List<StockUpdownDomain>> getTopStocksByIncrease();
    R<Map<String,List>> getStockUpDownCount();
}
