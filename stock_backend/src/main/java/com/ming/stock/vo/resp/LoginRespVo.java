package com.ming.stock.vo.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    private long id;
    private  String phone;
    private String username;
    private String nickName;
}

