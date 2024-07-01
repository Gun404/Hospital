package com.bupt.Mapper;

import com.bupt.Annotation.AutoFill;
import com.bupt.Annotation.OperationLog;
import com.bupt.Pojo.Admin;
import com.bupt.Pojo.Doctor;
import com.bupt.Pojo.Patient;
import com.bupt.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DoctorMapper {

    @Select("select * from doctor where name=#{name}")
    Doctor getByName(String name);

    @AutoFill(OperationType.INSERT)
    @Insert("insert into doctor(name, age,sex,address, phone,id_card, password,department,   " +
            "title,specialty,status,create_time,update_time)" +
            " values (#{name},#{age},#{sex},#{address},#{phone},#{idCard},#{password},#{department}," +
            "#{title},#{specialty},#{status},#{createTime},#{updateTime}) ")
    void insert(Doctor doctor);

    @Select("select * from doctor where id=#{id}")
    Doctor getById(Integer id);

    @AutoFill(OperationType.UPDATE)
    @OperationLog(OperationType.UPDATE)
    void update(Doctor doctor);

    @OperationLog(OperationType.DELETE)
    @Delete("delete from doctor where id=#{id}")
    void deleteById(Doctor doctor);

    @Select("select * from doctor where status=1")
    List<Doctor> getAll();

    @Select("select * from doctor where status=0")
    List<Doctor> getPending();

    @Select("select * from doctor where department=#{id}")
    List<Doctor> getByDepartmentId(Integer id);
}
