package com.ming.stock.pojo.mapper;

import com.ming.stock.domain.StockSearchDomain;
import com.ming.stock.pojo.entity.StockBusiness;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author jingjing
* @description 针对表【stock_business(主营业务表)】的数据库操作Mapper
* @createDate 2026-08-02 09:34:57
* @Entity com.ming.stock.pojo.entity.StockBusiness
*/
public interface StockBusinessMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBusiness record);

    int insertSelective(StockBusiness record);

    StockBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBusiness record);

    int updateByPrimaryKey(StockBusiness record);

    List<String> getStockIds();

    List<StockSearchDomain> searchStock(@Param("searchStr")String searchStr);



}
