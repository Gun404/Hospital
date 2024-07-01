package com.bupt.Controller.Admin;



import com.bupt.Constant.MessageConstant;
import com.bupt.Constant.StatusConstant;
import com.bupt.Pojo.Admin;
import com.bupt.Pojo.Result;

import com.bupt.Pojo.UserLoginVO;
import com.bupt.Service.AdminService;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        log.info("登录操作:{}",admin);
        Admin u=adminService.login(admin);
        log.info("登录操作:{}",u);
        if (u!=null) {
            if (Objects.equals(u.getStatus(), StatusConstant.DISABLE)){
                return Result.error(MessageConstant.NOT_REVIEWED);
            }
            Map<String, Object> claims=new HashMap<>();
            claims.put("adminId",u.getId());
            claims.put("adminPassword",u.getPassword());
            claims.put("adminName",u.getName());
            String jwt= JwtUtil.generateJwt(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(),claims);
            log.info(jwt);
            UserLoginVO userLoginVO=new UserLoginVO(u.getId(),"Admin",u.getName(),jwt,u.getMustChangePassword());
            return Result.success(userLoginVO);
        }
        return Result.error(MessageConstant.LOGIN_FAILED);
    }

    @PostMapping("/register")
    public Result register(@RequestBody  Admin admin) {
        log.info("注册操作:{}", admin);
        boolean b = adminService.register(admin);
        if (!b){
            return Result.error(MessageConstant.ACCOUNT_EXISTS);
        }
        return Result.success();
    }

    @GetMapping("/user/{id}")
    public Result showInformation(@PathVariable Integer id){
        log.info("展示管理员:{}个人信息",id);
        Admin admin=adminService.showInformation(id);
        return Result.success(admin);
    }

    @PutMapping("/user")
    public Result updateInformation(@RequestBody Admin admin){
        log.info("更新管理员:{}个人信息",admin);
        adminService.updateInformation(admin);
        return Result.success();
    }
}
