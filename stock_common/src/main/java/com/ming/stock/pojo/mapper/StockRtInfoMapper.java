package com.ming.stock.pojo.mapper;

import com.ming.stock.domain.Stock4MinuteDomain;
import com.ming.stock.domain.StockUpdownDomain;
import com.ming.stock.pojo.entity.StockRtInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author jingjing
* @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
* @createDate 2026-08-02 09:34:57
* @Entity com.ming.stock.pojo.entity.StockRtInfo
*/
public interface StockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockRtInfo record);

    int insertSelective(StockRtInfo record);

    StockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockRtInfo record);

    int updateByPrimaryKey(StockRtInfo record);

    List<StockUpdownDomain> getStockInfoByTime(@Param("curDate") Date CurDate);

    List<StockUpdownDomain> getTopStocksByIncrease(@Param("curDate") Date CurDate);

    List<Map> getStockUpDownCount(@Param("startDate")Date startDate,
                                  @Param("endDate")Date EndDate,@Param("flag") Integer flag);
    List<Map> getIncreaseRangeInfoByDate(@Param("curTime") Date curTime);

    List<Stock4MinuteDomain> getStock4MinuteInfo(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("stockCode") String stockCode);

    List<Stock4MinuteDomain> getStock4DkLine(@Param("startDate") Date startDate,
                                             @Param("endDate") Date endDate, @Param("stockCode") String stockCode);
    int insertBatch(@Param("list") List<StockRtInfo> list);
}
