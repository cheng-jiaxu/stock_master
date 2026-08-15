package com.ming.stock.service;

import cn.hutool.core.util.StrUtil;
import com.ming.stock.pojo.entity.StockMarketIndexInfo;
import com.ming.stock.pojo.mapper.StockMarketIndexInfoMapper;
import com.ming.stock.utils.DateTimeUtil;
import com.ming.stock.utils.IdWorker;
import com.ming.stock.vo.StockInfoConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class StockTimerTaskServiceImpl implements StockTimerTaskService {
        @Autowired
        private RestTemplate restTemplate;
        @Autowired
        private StockInfoConfig stockInfoConfig;
        @Autowired
        private IdWorker idWorker;
        @Autowired
        private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;

    @Override
    public void getInnerMarketInfo() {
        //1.定义采集的url接口
        String url = stockInfoConfig.getMarketUrl() + String.join(",", stockInfoConfig.getInner());
        //2.调用restTemplate采用数据
        //2.1组装请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Referer", "https://finance.sina.com.cn/stock/");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        //2.2 组装请求对象
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        //2.3restTemplate 发起请求
        String resString = restTemplate.postForObject(url, entity, String.class);
        String reg = "var hq_str_(.+)=\"(.+)\";";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(resString);
        ArrayList<StockMarketIndexInfo> infos = new ArrayList<>();
        while (matcher.find()) {
            String marketCode = matcher.group(1);
            String otherInfo = matcher.group(2);
            String[] splitArr = otherInfo.split(",");
            String marketName = splitArr[0];
            BigDecimal openPoint = new BigDecimal(splitArr[1]);
            BigDecimal preClosePoint = new BigDecimal(splitArr[2]);
            BigDecimal curPoint = new BigDecimal(splitArr[3]);
            BigDecimal maxPoint = new BigDecimal(splitArr[4]);
            BigDecimal minPoint = new BigDecimal(splitArr[5]);
            Long tradeAmt = 0L;
            if (splitArr.length > 6 && StrUtil.isNotBlank(splitArr[6])) {
                try {
                    // 使用 BigDecimal 转 Long，可以兼容 "0"、"0.00" 或科学计数法形式的字符串
                    tradeAmt = new BigDecimal(splitArr[6].trim()).longValue();
                } catch (Exception e) {
                    tradeAmt = 0L; // 解析失败安全兜底为 0L
                }
            }
            BigDecimal tradeVol = new BigDecimal(splitArr[7]);
            Date curTime = DateTimeUtil.getDateTimeWithoutSecond(splitArr[30] + " " + splitArr[31]).toDate();
            //组装entity对象
            StockMarketIndexInfo info = StockMarketIndexInfo.builder().id(idWorker.nextId())
                    .marketCode(marketCode)
                    .marketName(marketName)
                    .curPoint(curPoint)
                    .openPoint(openPoint)
                    .preClosePoint(preClosePoint)
                    .maxPoint(maxPoint)
                    .minPoint(minPoint)
                    .tradeVolume(tradeVol)
                    .tradeAmount(tradeAmt)
                    .curTime(curTime)
                    .build();
            //收集封装的对象,方便批量插入
            infos.add(info);
        }
        log.info("采集当前的大盘数据:{}", infos);
        if (CollectionUtils.isEmpty(infos)) {
            return;
        }
        int count = this.stockMarketIndexInfoMapper.insertBatch(infos);
        log.info("插入了{}条数据",count);
    }
    }

