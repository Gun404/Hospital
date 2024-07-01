package com.bupt.Controller.Common;

import com.bupt.Pojo.Department;
import com.bupt.Pojo.Doctor;
import com.bupt.Pojo.Result;
import com.bupt.Service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/common/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public Result getAllDepartment(){
        log.info("正在获取所有的科室信息");
        List<Department> list=departmentService.getAll();
        return Result.success(list);
    }

    @GetMapping("/doctor/{id}")
    public Result getDoctorWithDepartment(@PathVariable Integer id){
        log.info("正在查询某科室:{}的所有医生",id);
        List<Doctor> list=departmentService.getDoctors(id);
        return Result.success(list);
    }
}
