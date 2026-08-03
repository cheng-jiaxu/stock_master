package com.ming.stock.controller;

import com.ming.stock.pojo.entity.SysUser;
import com.ming.stock.service.UserService;
import com.ming.stock.vo.req.LoginReqVo;
import com.ming.stock.vo.resp.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userservice;
    //使用位置:方法上 接口描述
    @Operation(summary = "通过用户名字查询用户信息",responses = {
            @ApiResponse(responseCode = "200",description = "成功获取用户信息",content =
            @Content(schema = @Schema(implementation = SysUser.class)))
    })
    //描述单个参数
    @Parameter(name = "userName",description = "用户名",required = true)
    @GetMapping("user/{userName}")
    public SysUser getUserByUsername(@PathVariable("userName") String userName){
        return userservice.getUserByUserName(userName);
    }
    /**
     * @param vo
     */
    @Operation(summary = "用户登录功能",responses = {@ApiResponse(responseCode = "200",description = "登录成功",
    content = @Content(schema = @Schema(implementation = LoginReqVo.class)))})
    @PostMapping("/login")
    public R<LoginReqVo> login(@RequestBody LoginReqVo vo){
        return userservice.login(vo);
    }
    @Operation(summary = "验证码生成",responses = {@ApiResponse(responseCode = "200",description = "成功获取验证码",
            content = @Content(schema = @Schema(implementation = Map.class)))})
    @GetMapping("/captcha")
    public R<Map> getCaptchaCode(){
        return userservice.getCaptchaCode();
    }

}
