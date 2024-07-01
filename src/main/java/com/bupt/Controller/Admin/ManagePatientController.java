package com.bupt.Controller.Admin;

import com.bupt.Context.NameContext;
import com.bupt.Pojo.Admin;
import com.bupt.Pojo.Doctor;
import com.bupt.Pojo.Patient;
import com.bupt.Pojo.Result;
import com.bupt.Service.ManageAdminService;
import com.bupt.Service.ManagePatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/patient")
public class ManagePatientController {
     @Autowired
    private ManagePatientService managePatientService;

     @GetMapping
    public Result showAllPatient(){
         log.info("管理员:{}在查询所有患者的信息", NameContext.getCurrentName());
         List<Patient> list = managePatientService.showAllPatient();
         return Result.success(list);
     }

     @DeleteMapping("/{id}")
    public Result deletePatient(@PathVariable Integer id){
         log.info("管理员:{}在删除某一个患者:{}",NameContext.getCurrentName(),id);
         managePatientService.deletePatient(id);
         return Result.success();
     }

     @PutMapping
    public Result updatePatient(@RequestBody Patient patient){
         log.info("管理员:{}在更新某一个患者:{}的信息",NameContext.getCurrentName(),patient.getName());
         managePatientService.updatePatient(patient);
         return Result.success();
     }

    @GetMapping("/new")
    public Result showPendingPatient(){
        log.info("管理员:{}在查询所有待审核患者的信息", NameContext.getCurrentName());
        List<Patient> list = managePatientService.showPendingPatient();
        return Result.success(list);
    }

    @PatchMapping("/new/{status}/{id}")
    public Result approved(@PathVariable Integer status,@PathVariable Integer id){
        log.info("审核通过:{},{}",id,status);
        managePatientService.approve(id,status);
        return Result.success();
    }
}
