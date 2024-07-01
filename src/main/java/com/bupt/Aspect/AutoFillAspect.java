package com.bupt.Aspect;


import com.bupt.Annotation.AutoFill;
import com.bupt.Constant.AutoFillConstant;
import com.bupt.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * 切入点
     */
    @Pointcut("execution(* com.bupt.Mapper.*.*(..))&&@annotation(com.bupt.Annotation.AutoFill)")
    public void autoFillPointCut(){}

    /**
     * 前置通知
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("开始自动公共字段填充");
//从注获取数据库操作类型
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = methodSignature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType=annotation.value();
//获取当前操作的实体对象
        Object[] args=joinPoint.getArgs();
        if (args==null||args.length==0){
            return;
        }

        Object entity = args[0];

//准备好将要赋值的数据
        LocalDateTime now=LocalDateTime.now();

//根据当前不同操作类型通过反射赋值

       if (operationType== OperationType.INSERT){
           try {
               Method setCreateTime=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME,LocalDateTime.class);
               Method setUpdateTime=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);

               //反射赋值
               setCreateTime.invoke(entity,now);
               setUpdateTime.invoke(entity,now);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       else{

           try {
               Method setUpdateTime=entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);
               setUpdateTime.invoke(entity,now);

           } catch (Exception e) {
               e.printStackTrace();
           }
       }

    }
}
