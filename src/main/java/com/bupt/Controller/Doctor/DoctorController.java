package com.bupt.Controller.Doctor;

import com.bupt.Constant.MessageConstant;
import com.bupt.Constant.StatusConstant;
import com.bupt.Pojo.Admin;
import com.bupt.Pojo.Doctor;
import com.bupt.Pojo.Result;
import com.bupt.Pojo.UserLoginVO;
import com.bupt.Service.DoctorService;
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
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping("/login")
    public Result login(@RequestBody Doctor doctor){
        log.info("登录操作:{}",doctor);
        Doctor d=doctorService.login(doctor);
        log.info("登录操作:{}",d);
        if (d!=null) {
            if (Objects.equals(d.getStatus(), StatusConstant.DISABLE)){
                return Result.error(MessageConstant.NOT_REVIEWED);
            }
            Map<String, Object> claims=new HashMap<>();
            claims.put("doctorId",d.getId());
            claims.put("doctorPassword",d.getPassword());
            claims.put("doctorName",d.getName());
            String jwt= JwtUtil.generateJwt(jwtProperties.getDoctorSecretKey(), jwtProperties.getDoctorTtl(),claims);
            log.info(jwt);
            UserLoginVO userLoginVO=new UserLoginVO(d.getId(),"Doctor",d.getName(),jwt,0);
            return Result.success(userLoginVO);
        }
        return Result.error(MessageConstant.LOGIN_FAILED);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Doctor doctor) {
        log.info("注册操作:{}", doctor);
        boolean b = doctorService.register(doctor);
        if (!b){
            return Result.error(MessageConstant.ACCOUNT_EXISTS);
        }
        return Result.success();
    }

    @GetMapping("/user/{id}")
    public Result showInformation(@PathVariable Integer id){
        log.info("展示医生:{}个人信息",id);
        Doctor doctor=doctorService.showInformation(id);
        return Result.success(doctor);
    }
    @PutMapping("/user")
    public Result updateInformation(@RequestBody Doctor doctor){
        log.info("更新医生:{}个人信息",doctor);
        doctorService.updateInformation(doctor);
        return Result.success();
    }
}
