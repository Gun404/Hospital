package com.bupt.Mapper;

import com.bupt.Annotation.AutoFill;
import com.bupt.Pojo.Attendance;
import com.bupt.Pojo.AttendanceVO;
import com.bupt.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AttendanceMapper {

    @Select(" select a.id,a.doctor_id,a.attendance_time,a.register_number,a.status,a.create_time,d.name doctor_name " +
            "from attendance a left join doctor d on a.doctor_id=d.id where a.status=0")
    List<AttendanceVO> getPending();

    @Delete("delete from attendance where id=#{id}")
    void deleteById(Integer id);

    void update(Attendance attendance);

    @AutoFill(OperationType.INSERT)
    @Insert("insert into attendance(doctor_id, attendance_time, register_number, status, create_time,update_time) " +
            "values(#{doctorId},#{attendanceTime},#{registerNumber},#{status},#{createTime},#{updateTime}) ")
    void add(Attendance attendance);

    List<AttendanceVO> select(Integer id, LocalDateTime begin, LocalDateTime end);
}
