package com.ming.stock.vo.req;
import  lombok.Data;
@Data
public class LoginReqVo {
    /**
     * 用户名
     */
    private String username;

    private String password;

    private String code;
}
