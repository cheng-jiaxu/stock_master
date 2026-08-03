package com.ming.stock.vo.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespVo {
    /**
     * 将Long类型数字进行Json转换时,转成String格式类型
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "用户ID")
    private long id;
    /**
     * 电话
     */
    @Schema(description = "电话")
    private  String phone;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;
    /**
     * 昵称
     */
    @Schema(description = "用户昵称")
    private String nickName;
}

