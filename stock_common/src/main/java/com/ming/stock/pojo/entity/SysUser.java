package com.ming.stock.pojo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 用户表
 * @TableName sys_user
 */
@Data
public class SysUser {
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Long id;

    /**
     * 账户
     */
    @Schema(description = "账户")
    private String username;

    /**
     * 用户密码密文
     */
    @Schema(description = "用户密码密文")
    private String password;

    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phone;

    /**
     * 真实名称
     */
    @Schema(description = "真实名称")
    private String realName;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickName;

    /**
     * 邮箱(唯一)
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 账户状态(1.正常 2.锁定 )
     */
    @Schema(description = "账户状态")
    private Integer status;

    /**
     * 性别(1.男 2.女)
     */
    @Schema(description = "性别")
    private Integer sex;

    /**
     * 是否删除(1未删除；0已删除)
     */
    @Schema(description = "是否删除")
    private Integer deleted;

    /**
     * 创建人
     */
    private Long createId;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private Long updateId;

    /**
     * 创建来源(1.web 2.android 3.ios )
     */
    @Schema(description = "创建来源")
    private Integer createWhere;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;
}