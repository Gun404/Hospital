package com.bupt.Pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVO {
    private Integer id;//该用户id
    private String type;//该用户类型
    private String name;//用户的姓名
    private String jwt;//jwt令牌
    private Integer mustChangePassword;//是否需要强制修改密码

}
