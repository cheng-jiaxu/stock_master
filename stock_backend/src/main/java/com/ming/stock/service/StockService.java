package com.ming.stock.service;

import com.ming.stock.domain.InnerMarketDomain;
import com.ming.stock.vo.resp.R;

import java.util.List;

public interface StockService {
    /**
     * @Decription:获取国内最新大盘数据
     * @return
     */
    R<List<InnerMarketDomain>> getInnerMarketInfo();
}
