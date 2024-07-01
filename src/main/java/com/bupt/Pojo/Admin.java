package com.bupt.Pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Integer id;//主键id值
    private String name;//姓名
    private String address;//住址
    private String phone;//电话
    private String idCard;//身份证
    private String password;//密码
    private Integer status;//可用状态码
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
    private Integer mustChangePassword;//是否需要强制修改密码
}
