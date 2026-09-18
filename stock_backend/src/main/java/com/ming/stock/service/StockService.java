package com.ming.stock.service;

import com.ming.stock.domain.*;
import com.ming.stock.vo.resp.PageResult;
import com.ming.stock.vo.resp.R;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

public interface StockService {
    /**
     * @return
     * @Decription:获取国内最新大盘数据
     */
    R<List<InnerMarketDomain>> getInnerMarketInfo();

    R<List<StockBlockDomain>> sectorAllLimit();

    R<PageResult<StockUpdownDomain>> getStockInfoByPage(Integer Page, Integer PageSize);

    R<List<StockUpdownDomain>> getTopStocksByIncrease();

    R<Map<String, List>> getStockUpDownCount();

    void exportStockUpdownInfo(Integer Page, Integer PageSize, HttpServletResponse response);

    R<Map<String, List>> getComparedStockTradeAmt();

    R<Map> getIncreaseRangeInfoByDate();

    R<List<Stock4MinuteDomain>> getStockScreenTimeSharing(String stockCode);

    R<List<Stock4MinuteDomain>> getStock4DkLine(String stockCode);

    R<List<OuterMarketDomain>> getOuterMarketInfo();

    R<List<StockSearchDomain>> searchStock(String searchStr);
}
