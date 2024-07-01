package com.bupt.Service;

import com.bupt.Pojo.Department;
import com.bupt.Pojo.Doctor;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();

    List<Doctor> getDoctors(Integer id);
}
