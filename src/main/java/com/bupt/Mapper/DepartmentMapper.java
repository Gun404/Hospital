package com.bupt.Mapper;

import com.bupt.Pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Select("select * from department")
    List<Department> select();
}
