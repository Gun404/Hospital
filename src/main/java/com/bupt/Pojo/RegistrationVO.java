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
public class RegistrationVO {
    private Integer id;//主键id
    private Integer doctorId;//医生id
    private LocalDateTime receptionTime;//挂号时间
    private LocalDateTime diagnosisTime;//预约诊断时间
    private Integer payStatus;//支付状态
    private String  doctorName;//医生姓名
}
