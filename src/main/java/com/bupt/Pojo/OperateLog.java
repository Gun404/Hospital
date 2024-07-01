package com.bupt.Pojo;

import com.bupt.enumeration.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog {

    private Integer id;//主键id
    private String operatorName; //操作管理员的姓名
    private String objectName; //被操作者的姓名
    private String objectType;//被操作者的类型
    private OperationType operationType;//操作的类型
    private LocalDateTime time;//操作的时间
}
