package com.bupt.Aspect;

import com.bupt.Annotation.AutoFill;
import com.bupt.Annotation.OperationLog;
import com.bupt.Context.NameContext;
import com.bupt.Mapper.OperationLogMapper;
import com.bupt.Pojo.OperateLog;
import com.bupt.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Component
@Slf4j
@Aspect
public class OperationLogAspect {
    @Autowired
    private OperationLogMapper operationLogMapper;
    /**
     * 切入点
     */
    @Pointcut("execution(* com.bupt.Mapper.*.*(..))&&@annotation(com.bupt.Annotation.OperationLog)")
    public void autoFillPointCut(){}

    /**
     * 后置通知
     */

    @After("autoFillPointCut()")
    public void recordLog(JoinPoint joinPoint)  throws Exception{
        log.info("开始记录操作日志");
        //从注获取数据库操作类型
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        OperationLog annotation = methodSignature.getMethod().getAnnotation(OperationLog.class);
        OperationType operationType=annotation.value();
//获取当前操作的实体对象
        Object[] args=joinPoint.getArgs();
        if (args==null||args.length==0){
            log.info("未找到实体对象");
            return;
        }

        Object entity = args[0];
        String[] s = entity.getClass().getName().split("\\.");
        String objectType = s[3];
        Field field = entity.getClass().getDeclaredField("name");
        field.setAccessible(true);
        String objectName = (String) field.get(entity);
        String operatorName= NameContext.getCurrentName();
        OperateLog operateLog=new OperateLog(null,operatorName,objectName,objectType,operationType, LocalDateTime.now());
        operationLogMapper.insert(operateLog);
        log.info("操作日志已输入数据库");

    }
}
