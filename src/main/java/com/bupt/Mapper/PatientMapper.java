package com.bupt.Mapper;

import com.bupt.Annotation.AutoFill;
import com.bupt.Annotation.OperationLog;
import com.bupt.Pojo.Admin;
import com.bupt.Pojo.Patient;
import com.bupt.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PatientMapper {

    @Select("select * from patient where name=#{name}")
    Patient getByName(String name);

    @AutoFill(OperationType.INSERT)
    @Insert("insert into patient(name, age,sex,address, phone,medical_record, id_card, password,   " +
            "status,create_time,update_time)" +
            " values (#{name},#{age},#{sex},#{address},#{phone},#{medicalRecord},#{idCard},#{password}," +
            "#{status},#{createTime},#{updateTime}) ")
    void insert(Patient patient);

    @Select("select * from patient where id=#{id}")
    Patient getById(Integer id);

    @AutoFill(OperationType.UPDATE)
    @OperationLog(OperationType.UPDATE)
    void update(Patient patient);
    @Select("select * from patient where status=1")
    List<Patient> getAll();

    @OperationLog(OperationType.DELETE)
    @Delete("delete from patient where id=#{id}")
    void deleteById(Patient patient);

    @Select("select * from patient where status=0")
    List<Patient> getPending();
}
