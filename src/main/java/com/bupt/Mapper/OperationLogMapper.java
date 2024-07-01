package com.bupt.Mapper;

import com.bupt.Pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperationLogMapper {

    @Insert("insert into operation_log(operator_name, object_name, object_type, time, operation_type) " +
            "VALUES (#{operatorName},#{objectName},#{objectType},#{time},#{operationType}) ")
    void insert(OperateLog log);

    @Select("select * from operation_log")
    List<OperateLog> select();
}
