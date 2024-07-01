package com.bupt.Controller.Patient;

import com.bupt.Constant.MessageConstant;
import com.bupt.Constant.StatusConstant;
import com.bupt.Pojo.Doctor;
import com.bupt.Pojo.Patient;
import com.bupt.Pojo.Result;
import com.bupt.Pojo.UserLoginVO;
import com.bupt.Service.PatientService;
import com.bupt.Utils.JwtProperties;
import com.bupt.Utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping("/login")
    public Result login(@RequestBody Patient patient){
        log.info("登录操作:{}",patient);
        Patient p=patientService.login(patient);
        log.info("登录操作:{}",p);
        if (p!=null) {
            if (Objects.equals(p.getStatus(), StatusConstant.DISABLE)){
                return Result.error(MessageConstant.NOT_REVIEWED);
            }
            Map<String, Object> claims=new HashMap<>();
            claims.put("patientId",p.getId());
            claims.put("patientPassword",p.getPassword());
            claims.put("patientName",p.getName());
            String jwt= JwtUtil.generateJwt(jwtProperties.getPatientSecretKey(), jwtProperties.getPatientTtl(),claims);
            log.info(jwt);
            UserLoginVO userLoginVO=new UserLoginVO(p.getId(),"Patient",p.getName(),jwt,0);
            return Result.success(userLoginVO);
        }
        return Result.error(MessageConstant.LOGIN_FAILED);
    }


    @PostMapping("/register")
    public Result register(@RequestBody Patient patient) {
        log.info("注册操作:{}", patient);
        boolean b = patientService.register(patient);
        if (!b){
            return Result.error(MessageConstant.ACCOUNT_EXISTS);
        }
        return Result.success();
    }

    @GetMapping("/user/{id}")
    public Result showInformation(@PathVariable Integer id){
        log.info("展示患者:{}个人信息",id);
        Patient patient=patientService.showInformation(id);
        return Result.success(patient);
    }

    @PutMapping("/user")
    public Result updateInformation(@RequestBody Patient patient){
        log.info("更新患者:{}个人信息",patient);
        patientService.updateInformation(patient);
        return Result.success();
    }
}
