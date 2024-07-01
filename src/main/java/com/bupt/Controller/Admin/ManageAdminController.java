package com.bupt.Controller.Admin;

import com.bupt.Context.NameContext;
import com.bupt.Pojo.Admin;
import com.bupt.Pojo.Result;
import com.bupt.Service.ManageAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/admin")
public class ManageAdminController {
     @Autowired
    private ManageAdminService manageAdminService;

     @GetMapping
    public Result showAllAdmin(){
         log.info("管理员:{}在查询所有管理员的信息", NameContext.getCurrentName());
         List<Admin> list = manageAdminService.showAllAdmin();
         return Result.success(list);
     }

     @DeleteMapping("/{id}")
    public Result deleteAdmin(@PathVariable Integer id){
         log.info("管理员:{}在删除某一个管理员:{}",NameContext.getCurrentName(),id);
         manageAdminService.deleteAdmin(id);
         return Result.success();
     }

     @PutMapping
    public Result updateAdmin(@RequestBody Admin admin){
         log.info("管理员:{}在更新某一个管理员:{}的信息",NameContext.getCurrentName(),admin.getName());
         manageAdminService.updateAdmin(admin);
         return Result.success();
     }

     @GetMapping("/new")
    public Result showPendingAdmin(){
         log.info("管理员:{}在查询所有待审核管理员的信息", NameContext.getCurrentName());
         List<Admin> list = manageAdminService.showPendingAdmin();
         return Result.success(list);
     }

    @PatchMapping("/new/{status}/{id}")
    public Result approved(@PathVariable Integer status,@PathVariable Integer id){
        log.info("审核通过:{},{}",id,status);
        manageAdminService.approve(id,status);
        return Result.success();
    }
}
