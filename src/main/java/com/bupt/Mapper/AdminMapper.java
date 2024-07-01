package com.bupt.Mapper;

import com.bupt.Annotation.AutoFill;
import com.bupt.Annotation.OperationLog;
import com.bupt.Pojo.Admin;
import com.bupt.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where name=#{name}")
    Admin getByName(String name);

    @AutoFill(OperationType.INSERT)
    @Insert("insert into admin(name, address, phone, id_card, password, status, create_time, " +
            "update_time, must_change_password)" +
            " values (#{name},#{address},#{phone},#{idCard},#{password},#{status},#{createTime}," +
            "#{updateTime},#{mustChangePassword}) ")
    void insert(Admin admin);

    @Select("select * from admin where id=#{id}")
    Admin getById(Integer id);

    @AutoFill(OperationType.UPDATE)
    @OperationLog(OperationType.UPDATE)
    void update(Admin admin) ;

    @Select("select * from admin where status=1")
    List<Admin> getAll();

    @OperationLog(OperationType.DELETE)
    @Delete("delete from admin where id=#{id}")
    void deleteById(Admin admin);

    @Select("select * from admin where status=0")
    List<Admin> getPending();
}
