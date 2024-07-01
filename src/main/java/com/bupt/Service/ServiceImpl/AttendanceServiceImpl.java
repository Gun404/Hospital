package com.bupt.Service.ServiceImpl;

import com.bupt.Mapper.AttendanceMapper;
import com.bupt.Pojo.Attendance;
import com.bupt.Pojo.AttendanceVO;
import com.bupt.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public void publish(Attendance attendance) {
        attendance.setStatus(0);
        attendanceMapper.add(attendance);
    }

    @Override
    public void delete(Integer id) {
        attendanceMapper.deleteById(id);
    }

    @Override
    public List<AttendanceVO> getAttendance(Integer id, LocalDateTime begin, LocalDateTime end) {
        return attendanceMapper.select(id,begin,end);
    }
}
