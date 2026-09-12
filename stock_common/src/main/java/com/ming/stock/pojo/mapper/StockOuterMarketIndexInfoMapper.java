package com.ming.stock.pojo.mapper;

import com.ming.stock.domain.OuterMarketDomain;
import com.ming.stock.pojo.entity.StockOuterMarketIndexInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author jingjing
* @description 针对表【stock_outer_market_index_info(外盘详情信息表)】的数据库操作Mapper
* @createDate 2026-08-02 09:34:57
* @Entity com.ming.stock.pojo.entity.StockOuterMarketIndexInfo
*/
public interface StockOuterMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockOuterMarketIndexInfo record);

    int insertSelective(StockOuterMarketIndexInfo record);

    StockOuterMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockOuterMarketIndexInfo record);

    int updateByPrimaryKey(StockOuterMarketIndexInfo record);

    List<OuterMarketDomain> getOuterMarketInfo(@Param("curDate") Date curDate,@Param("mCode") List<String> mCode);

    int insertBatch(@Param("infos") List<StockOuterMarketIndexInfo> infos);

}
