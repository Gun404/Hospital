package com.bupt.Controller.Common;

import com.bupt.Pojo.OperateLog;
import com.bupt.Pojo.Result;
import com.bupt.Service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/common/operationLog")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @GetMapping
    public Result getAllLogs(){
        log.info("正在查询所有的操作记录");
        List<OperateLog> list= operationLogService.getAll();
        return Result.success(list);
    }
}
