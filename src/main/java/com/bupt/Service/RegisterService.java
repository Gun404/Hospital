package com.bupt.Service;

import com.bupt.Pojo.Attendance;
import com.bupt.Pojo.AttendanceVO;
import com.bupt.Pojo.Registration;
import com.bupt.Pojo.RegistrationVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RegisterService {
    List<AttendanceVO>getAttendanceInfo(LocalDate diagnosisDate, Integer doctorId);

    void register(Attendance attendance, Integer id,Integer status);

    List<RegistrationVO> getRegistration(Integer patientId);
}
