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
public class AttendanceVO {

    private Integer id;//主键id
    private Integer doctorId;//医生id
    private LocalDateTime attendanceTime;//出诊时间
    private Integer registerNumber;//挂号数量
    private Integer status;//审核状态
    private LocalDateTime createTime;//创建时间
    private String doctorName;//医生姓名
}
