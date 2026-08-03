package com.ming.stock.pojo.mapper;

import com.ming.stock.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
* @author jingjing
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2026-08-02 09:34:57
* @Entity com.ming.stock.pojo.entity.SysUser
*/

public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findByUserName(@Param("userName")String Name);

}
