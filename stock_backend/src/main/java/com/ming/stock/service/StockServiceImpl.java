package com.ming.stock.service;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ming.stock.domain.InnerMarketDomain;
import com.ming.stock.domain.StockBlockDomain;
import com.ming.stock.domain.StockUpdownDomain;
import com.ming.stock.pojo.mapper.StockBlockRtInfoMapper;
import com.ming.stock.pojo.mapper.StockMarketIndexInfoMapper;
import com.ming.stock.pojo.mapper.StockRtInfoMapper;
import com.ming.stock.utils.DateTimeUtil;
import com.ming.stock.vo.StockInfoConfig;
import com.ming.stock.vo.resp.PageResult;
import com.ming.stock.vo.resp.R;
import com.ming.stock.vo.resp.ResponseCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
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

    @Override
    public R<Map<String, List>> getStockUpDownCount() {
        DateTime curDateTime = DateTime.parse("2023-01-06 14:25:00",
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        Date endDate = curDateTime.toDate();
        Date startDate = DateTimeUtil.getOpenDate(curDateTime).toDate();
        List<Map> uplist = stockRtInfoMapper.getStockUpDownCount(startDate,endDate,1);
        List<Map> downlist = stockRtInfoMapper.getStockUpDownCount(startDate,endDate,0);
        HashMap<String,List> info = new HashMap<>();
        info.put("uplist",uplist);
        info.put("downlist",downlist);
        return R.ok(info);
    }

    @Override
    public void exportStockUpdownInfo(Integer Page, Integer PageSize, HttpServletResponse response) {
        try {
            //1.获取最近最新的一次股票有效交易时间点（精确分钟）

            //在数据库中是没有的，所以，先临时指定一个假数据,后续注释掉该代码即可
            Date curDate=DateTime.parse("2022-12-31 09:47:00",
                    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
            //2.设置分页参数 底层会拦截mybatis发送的sql，并动态追加limit语句实现分页
            PageHelper.startPage(Page,PageSize);
            //3.查询
            List<StockUpdownDomain>
                    infos=stockRtInfoMapper.getStockInfoByTime(curDate);
            //如果集合为空，响应错误提示信息
            if (CollectionUtils.isEmpty(infos)) {
                //响应提示信息
                R<Object> r = R.error(ResponseCode.NO_RESPONSE_DATA);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new
                        ObjectMapper().writeValueAsString(r));
                return;
            }
            //设置响应excel文件格式类型
            response.setContentType("application/vnd.ms-excel");
            //2.设置响应数据的编码格式
            response.setCharacterEncoding("utf-8");
            //3.设置默认的文件名称
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("stockRt", "UTF-8");
            //设置默认文件名称：兼容一些特殊浏览器
            response.setHeader("content-disposition", "attachment;filename=" +
                    fileName + ".xlsx");
            //4.响应excel流
            EasyExcel
                    .write(response.getOutputStream(),StockUpdownDomain.class)
                    .sheet("股票信息")
                    .doWrite(infos);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("当前导出数据异常，当前页：{},每页大小：{},异常信息： {}",Page,PageSize,e.getMessage());
        }
    }
}
