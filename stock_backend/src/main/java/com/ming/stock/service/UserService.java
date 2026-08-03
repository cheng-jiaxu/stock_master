package com.ming.stock.service;

import com.ming.stock.pojo.entity.SysUser;
import com.ming.stock.vo.req.LoginReqVo;
import com.ming.stock.vo.resp.R;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserService {
    SysUser getUserByUserName(@Param("userName") String UserName);
    R<LoginReqVo> login( LoginReqVo vo);
    R<Map> getCaptchaCode();
}
