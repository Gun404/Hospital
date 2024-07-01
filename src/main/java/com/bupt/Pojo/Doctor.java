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
public class Doctor {
    private Integer id;//主键id值
    private String name;//姓名
    private Integer age;//年龄
    private Integer sex;//性别
    private String address;//住址
    private String phone;//电话
    private String idCard;//身份证
    private Integer department;//所属科室
    private String title;//职称
    private String specialty;//专长
    private String password;//密码
    private Integer status;//可用状态码
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
