package com.ming.stock.controller;

import com.ming.stock.pojo.entity.SysUser;
import com.ming.stock.service.UserService;
import com.ming.stock.vo.req.LoginReqVo;
import com.ming.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userservice;
    @GetMapping("user/{userName}")
    public SysUser getUserByUsername(@PathVariable("userName") String userName){
        return userservice.getUserByUserName(userName);
    }
    /**
     * @param vo
     */
    @PostMapping("/login")
    public R<LoginReqVo> login(@RequestBody LoginReqVo vo){
        return userservice.login(vo);
    }

    @GetMapping("/captcha")
    public R<Map> getCaptchaCode(){
        return userservice.getCaptchaCode();
    }

}
