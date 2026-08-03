package com.ming.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.ming.stock.pojo.entity.SysUser;
import com.ming.stock.pojo.mapper.SysUserMapper;
import com.ming.stock.service.UserService;
import com.ming.stock.vo.req.LoginReqVo;
import com.ming.stock.vo.resp.R;
import com.ming.stock.vo.resp.ResponseCode;
import io.micrometer.common.util.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUser getUserByUserName(@Param("userName") String userName) {
        return sysUserMapper.findByUserName(userName);
    }

    @Override
    public R<LoginReqVo> login(LoginReqVo vo) {
        //判断参数是否合法
        if(vo ==null || StringUtils.isBlank(vo.getUsername())||
        StringUtils.isBlank(vo.getPassword())||StringUtils.isBlank(vo.getCode())){
            return R.error(ResponseCode.DATA_ERROR);
        }
        //根据用户名去数据库查询用户信息 获取密码加密后的密文
        SysUser user = sysUserMapper.findByUserName(vo.getUsername());
        //判断用户是否存在
        if(user == null){
            return  R.error(ResponseCode.ACCOUNT_NOT_EXISTS);
        }
        if(!passwordEncoder.matches(vo.getPassword(),user.getPassword())){
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        LoginReqVo reqVo = new LoginReqVo();
        BeanUtils.copyProperties(user,reqVo);
        return R.ok(reqVo);
    }

    @Override
    public R<Map> getCaptchaCode() {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(250,40,4,5);
        String checkCode = captcha.getCode();
        String imageData = captcha.getImageBase64();

    }
}
