package com.bupt.Controller.Admin;

import com.bupt.Context.NameContext;
import com.bupt.Pojo.*;
import com.bupt.Service.ManageAdminService;
import com.bupt.Service.ManageDoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/doctor")
public class ManageDoctorController {
     @Autowired
    private ManageDoctorService manageDoctorService;

     @GetMapping
    public Result showAllDoctor(){
         log.info("管理员:{}在查询所有医生的信息", NameContext.getCurrentName());
         List<Doctor> list = manageDoctorService.showAllDoctor();
         return Result.success(list);
     }

     @DeleteMapping("/{id}")
    public Result deleteDoctor(@PathVariable Integer id){
         log.info("管理员:{}在删除某一个医生:{}",NameContext.getCurrentName(),id);
         manageDoctorService.deleteDoctor(id);
         return Result.success();
     }

     @PutMapping
    public Result updateDoctor(@RequestBody Doctor doctor){
         log.info("管理员:{}在更新某一个医生:{}的信息",NameContext.getCurrentName(),doctor.getName());
         manageDoctorService.updateDoctor(doctor);
         return Result.success();
     }

    @GetMapping("/new")
    public Result showPendingDoctor(){
        log.info("管理员:{}在查询所有待审核医生的信息", NameContext.getCurrentName());
        List<Doctor> list = manageDoctorService.showPendingDoctor();
        return Result.success(list);
    }

    @PatchMapping("/new/{status}/{id}")
    public Result approvedDoctor(@PathVariable Integer status,@PathVariable Integer id){
               log.info("审核医生个人信息通过:{},{}",id,status);
               manageDoctorService.approveDoctor(id,status);
               return Result.success();
    }

    @GetMapping("/attendance")
    public Result showPendingAttendance(){
         log.info("管理员:{}在查询所有待审核专家出诊信息",NameContext.getCurrentName());
         List<AttendanceVO> list=manageDoctorService.showPendingAttendance();
         return Result.success(list);
    }

    @PatchMapping("/attendance/{status}/{id}")
    public Result approvedAttendance(@PathVariable Integer status,@PathVariable Integer id){
         log.info("审核专家出诊信息通过:{},{}",status,id);
         manageDoctorService.approveAttendance(status,id);
         return Result.success();
    }
}
