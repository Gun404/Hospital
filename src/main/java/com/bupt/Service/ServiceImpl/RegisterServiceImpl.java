package com.bupt.Service.ServiceImpl;

import com.bupt.Constant.StatusConstant;
import com.bupt.Mapper.AttendanceMapper;
import com.bupt.Mapper.RegistrationMapper;
import com.bupt.Pojo.Attendance;
import com.bupt.Pojo.AttendanceVO;
import com.bupt.Pojo.Registration;
import com.bupt.Pojo.RegistrationVO;
import com.bupt.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private RegistrationMapper registrationMapper;


    @Override
    public List<AttendanceVO> getAttendanceInfo(LocalDate diagnosisDate, Integer doctorId) {
        LocalDateTime begin=LocalDateTime.of(diagnosisDate,LocalTime.of(0,0,0));
        LocalDateTime end=LocalDateTime.of(diagnosisDate,LocalTime.of(23,59,59));
        List<AttendanceVO> list = attendanceMapper.select(doctorId, begin, end);
        return list.stream().filter(a->a.getRegisterNumber()>0)
                .filter(a-> Objects.equals(a.getStatus(), StatusConstant.ENABLE)).toList();
    }

    @Transactional
    @Override
    public void register(Attendance attendance, Integer id,Integer status) {
        attendance.setRegisterNumber(attendance.getRegisterNumber()-1);
        attendanceMapper.update(attendance);
        LocalDateTime diagnosisTime = attendance.getAttendanceTime();
        Integer doctorId = attendance.getDoctorId();
        Registration registration = Registration.builder()
                .patientId(id).doctorId(doctorId).diagnosisTime(diagnosisTime).receptionTime(LocalDateTime.now())
                .payStatus(status).build();
        registrationMapper.insert(registration);
    }

    @Override
    public List<RegistrationVO> getRegistration(Integer patientId) {
        return registrationMapper.getByPatientId(patientId);
    }
}
