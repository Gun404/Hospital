package com.bupt.Service.ServiceImpl;

import com.bupt.Mapper.DepartmentMapper;
import com.bupt.Mapper.DoctorMapper;
import com.bupt.Pojo.Department;
import com.bupt.Pojo.Doctor;
import com.bupt.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public List<Department> getAll() {
       return departmentMapper.select();
    }

    @Override
    public List<Doctor> getDoctors(Integer id) {
       return doctorMapper.getByDepartmentId(id);
    }
}
