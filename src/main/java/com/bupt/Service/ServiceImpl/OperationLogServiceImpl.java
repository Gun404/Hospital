package com.bupt.Service.ServiceImpl;

import com.bupt.Mapper.OperationLogMapper;
import com.bupt.Pojo.OperateLog;
import com.bupt.Service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public List<OperateLog> getAll() {
        return operationLogMapper.select();
    }
}
