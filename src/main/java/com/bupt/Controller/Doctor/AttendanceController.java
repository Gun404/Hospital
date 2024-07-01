package com.bupt.Controller.Doctor;

import com.bupt.Context.NameContext;
import com.bupt.Pojo.Attendance;
import com.bupt.Pojo.AttendanceVO;
import com.bupt.Pojo.Result;
import com.bupt.Service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/doctor/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping
    public Result publishAttendance(@RequestBody Attendance attendance){
        log.info("当前医生:{}在发布出诊信息", NameContext.getCurrentName());
        attendanceService.publish(attendance);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public  Result deleteAttendance(@PathVariable Integer id){
        log.info("当前医生:{}正在删除某一出诊信息:{}",NameContext.getCurrentName(),id);
        attendanceService.delete(id);
        return Result.success();
    }

    @GetMapping
        public Result getAttendance(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime begin,
                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime end,
                                Integer doctorId){
         log.info("当前医生:{}正在查询某一段时间的出诊信息:{},{}",NameContext.getCurrentName(),begin,end);
         List<AttendanceVO> list= attendanceService.getAttendance(doctorId,begin,end);
         return Result.success(list);
    }

}
