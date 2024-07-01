package com.bupt.Service;

import com.bupt.Pojo.Admin;
import com.bupt.Pojo.Attendance;
import com.bupt.Pojo.AttendanceVO;
import com.bupt.Pojo.Doctor;

import java.util.List;

public interface ManageDoctorService {
    List<Doctor> showAllDoctor();

    void deleteDoctor(Integer id);

    void updateDoctor(Doctor doctor);

    List<Doctor> showPendingDoctor();

    void approveDoctor(Integer id, Integer status);

    List<AttendanceVO> showPendingAttendance();

    void approveAttendance(Integer status, Integer id);
}
