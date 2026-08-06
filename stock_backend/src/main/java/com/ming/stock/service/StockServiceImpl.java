package com.ming.stock.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ming.stock.domain.InnerMarketDomain;
import com.ming.stock.domain.StockBlockDomain;
import com.ming.stock.domain.StockUpdownDomain;
import com.ming.stock.pojo.mapper.StockBlockRtInfoMapper;
import com.ming.stock.pojo.mapper.StockMarketIndexInfoMapper;
import com.ming.stock.pojo.mapper.StockRtInfoMapper;
import com.ming.stock.vo.StockInfoConfig;
import com.ming.stock.vo.resp.PageResult;
import com.ming.stock.vo.resp.R;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockInfoConfig stockInfoConfig;
    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;
    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;
    @Autowired
    private StockRtInfoMapper stockRtInfoMapper;


    @Override
    public R<List<InnerMarketDomain>> getInnerMarketInfo() {
        //1.获取股票最新的交易时间点
       // Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //伪数据
        Date curDate = DateTime.parse("2022-12-28 09:31:00",
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //2.获取大盘编码集合
        List<String> mCodes = stockInfoConfig.getInner();
        //3.调用mapper查询数据
        List<InnerMarketDomain> data = stockMarketIndexInfoMapper.getMarketInfo(curDate,mCodes);
        //4.封装并响应
        return R.ok(data);
    }

    @Override
    public R<List<StockBlockDomain>> sectorAllLimit() {
        Date timePoint = DateTime.parse("2022-12-21 09:30:00",
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        List<StockBlockDomain> data = stockBlockRtInfoMapper.sectorAllLimit(timePoint);
        return R.ok(data);
    }

    @Override
    public R<PageResult<StockUpdownDomain>> getStockInfoByPage(Integer Page, Integer PageSize) {
        Date timePoint = DateTime.parse("2022-12-30 09:32:00",
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        PageHelper.startPage(Page,PageSize);
        List<StockUpdownDomain> data = stockRtInfoMapper.getStockInfoByTime(timePoint);
        PageInfo<StockUpdownDomain> pageInfo = new PageInfo<>(data);
        PageResult<StockUpdownDomain> pageResult = new PageResult<>(pageInfo);
         return R.ok(pageResult);
    }

    @Override
    public R<List<StockUpdownDomain>> getTopStocksByIncrease() {
        Date timePoint = DateTime.parse("2022-12-30 09:32:00",
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        List<StockUpdownDomain> data = stockRtInfoMapper.getTopStocksByIncrease(timePoint);
        return R.ok(data);
    }
}
