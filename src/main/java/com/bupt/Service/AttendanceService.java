package com.bupt.Service;

import com.bupt.Pojo.Attendance;
import com.bupt.Pojo.AttendanceVO;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceService {
    void publish(Attendance attendance);

    void delete(Integer id);

    List<AttendanceVO> getAttendance(Integer id, LocalDateTime begin, LocalDateTime end);
}
