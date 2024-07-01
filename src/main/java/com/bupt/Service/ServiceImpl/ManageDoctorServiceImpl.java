package com.bupt.Service.ServiceImpl;

import com.bupt.Constant.StatusConstant;
import com.bupt.Mapper.AttendanceMapper;
import com.bupt.Mapper.DoctorMapper;
import com.bupt.Pojo.Admin;
import com.bupt.Pojo.Attendance;
import com.bupt.Pojo.AttendanceVO;
import com.bupt.Pojo.Doctor;
import com.bupt.Service.ManageDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ManageDoctorServiceImpl implements ManageDoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public List<Doctor> showAllDoctor() {
        return doctorMapper.getAll();
    }

    @Transactional
    @Override
    public void deleteDoctor(Integer id) {
        String name = doctorMapper.getById(id).getName();
        Doctor doctor=Doctor.builder().id(id).name(name).build();
        doctorMapper.deleteById(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        String password= DigestUtils.md5DigestAsHex(doctor.getPassword().getBytes());
        doctor.setPassword(password);
        doctorMapper.update(doctor);
    }

    @Override
    public List<Doctor> showPendingDoctor() {
        return doctorMapper.getPending();
    }

    @Transactional
    @Override
    public void approveDoctor(Integer id, Integer status) {
        String name = doctorMapper.getById(id).getName();
        if (Objects.equals(status, StatusConstant.DISABLE)){
          Doctor doctor=Doctor.builder().id(id).name(name).build();
          doctorMapper.deleteById(doctor);
        }
        Doctor doctor=Doctor.builder()
                .id(id).status(status).name(name)
                .build();
            doctorMapper.update(doctor);
    }

    @Override
    public List<AttendanceVO> showPendingAttendance() {
       return attendanceMapper.getPending();
    }

    @Override
    public void approveAttendance(Integer status, Integer id) {
        if (Objects.equals(status, StatusConstant.DISABLE)){
            attendanceMapper.deleteById(id);
        }
        Attendance attendance=Attendance.builder().
                id(id).status(status).build();
        attendanceMapper.update(attendance);
    }
}
