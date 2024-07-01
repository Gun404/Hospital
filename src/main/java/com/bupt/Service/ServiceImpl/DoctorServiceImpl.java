package com.bupt.Service.ServiceImpl;

import com.bupt.Constant.MessageConstant;
import com.bupt.Constant.StatusConstant;
import com.bupt.Exception.AccountNotFoundException;
import com.bupt.Exception.AccountNotReviewedException;
import com.bupt.Exception.PasswordErrorException;
import com.bupt.Mapper.DoctorMapper;
import com.bupt.Pojo.Admin;
import com.bupt.Pojo.Doctor;
import com.bupt.Service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;

    /**
     * 医生登录
     * @param doctor
     * @return
     */
    @Override
    public Doctor login(Doctor doctor) {
        String name= doctor.getName();
        String password= doctor.getPassword();

        //根据姓名，查询数据库中的数据
        Doctor d=doctorMapper.getByName(name);

        //处理各种异常情况（账户不存在，密码错误，账号未审核）
        if (d==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // 后期需要进行md5加密，然后再进行比对
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        log.info(password);
        if (!password.equals(d.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (d.getStatus() == StatusConstant.DISABLE) {
            //账号未审核
            throw new AccountNotReviewedException(MessageConstant.NOT_REVIEWED);
        }
        return d;
    }

    @Override
    public boolean register(Doctor doctor) {
        String password= DigestUtils.md5DigestAsHex(doctor.getPassword().getBytes());
        doctor.setPassword(password);
        doctor.setStatus(0);
        Doctor d = doctorMapper.getByName(doctor.getName());
        if (d!=null){
            return false;
        }
        doctorMapper.insert(doctor);
        return true;
    }

    @Override
    public Doctor showInformation(Integer id) {
        Doctor doctor=doctorMapper.getById(id);
        return doctor;
    }

    @Override
    public void updateInformation(Doctor doctor) {
        String password= DigestUtils.md5DigestAsHex(doctor.getPassword().getBytes());
        doctor.setPassword(password);
        doctorMapper.update(doctor);
    }
}
