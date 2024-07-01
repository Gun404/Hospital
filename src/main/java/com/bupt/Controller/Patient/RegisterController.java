package com.bupt.Controller.Patient;

import com.bupt.Pojo.*;
import com.bupt.Service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/patient/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/attendance")
    public Result getAttendanceInfo(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate diagnosisDate,
                                    Integer doctorId){
        log.info("当前患者正在查询可挂号信息");
        List<AttendanceVO> list= registerService.getAttendanceInfo(diagnosisDate,doctorId);
        return Result.success(list);
    }

    @PostMapping("/{id}/{status}")
    public Result register(@RequestBody Attendance attendance,@PathVariable Integer id,@PathVariable Integer status){
        log.info("当前患者:{}正在挂号",id);
        registerService.register(attendance,id,status);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result InquireInfo(@PathVariable Integer id){
        log.info("当前患者:{}正在查看其挂号记录",id);
        List<RegistrationVO> list=registerService.getRegistration(id);
        return Result.success(list);
    }


}
