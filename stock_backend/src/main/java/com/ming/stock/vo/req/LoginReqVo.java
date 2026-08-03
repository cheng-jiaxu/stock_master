package com.ming.stock.vo.req;
import io.swagger.v3.oas.annotations.media.Schema;
import  lombok.Data;
@Data
public class LoginReqVo {
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
    /**
     * 验证码
     */
    @Schema(description = "验证码")
    private String code;
    /**
     * 保存redis随机码的key，sessionId
     */
    @Schema(description = "会话ID")
    private  String sessionId;
}
