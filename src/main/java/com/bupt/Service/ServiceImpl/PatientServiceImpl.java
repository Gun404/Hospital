package com.bupt.Service.ServiceImpl;

import com.bupt.Constant.MessageConstant;
import com.bupt.Constant.StatusConstant;
import com.bupt.Exception.AccountNotFoundException;
import com.bupt.Exception.AccountNotReviewedException;
import com.bupt.Exception.PasswordErrorException;
import com.bupt.Mapper.PatientMapper;
import com.bupt.Pojo.Doctor;
import com.bupt.Pojo.Patient;
import com.bupt.Service.PatientService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    /**
     * 患者登录
     * @param patient
     * @return
     */
    @Override
    public Patient login(Patient patient) {
        String name= patient.getName();
        String password= patient.getPassword();

        //根据姓名，查询数据库中的数据
        Patient p=patientMapper.getByName(name);

        //处理各种异常情况（账户不存在，密码错误，账号未审核）
        if (p==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // 后期需要进行md5加密，然后再进行比对
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(p.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (p.getStatus() == StatusConstant.DISABLE) {
            //账号未审核
            throw new AccountNotReviewedException(MessageConstant.NOT_REVIEWED);
        }
        return p;
    }

    @Override
    public boolean register(Patient patient) {
        String password= DigestUtils.md5DigestAsHex(patient.getPassword().getBytes());
        patient.setPassword(password);
        patient.setStatus(0);
        Patient p = patientMapper.getByName(patient.getName());
        if (p!=null){
            return false;
        }
        patientMapper.insert(patient);
        return true;
    }

    @Override
    public Patient showInformation(Integer id) {
        Patient patient=patientMapper.getById(id);
        return patient;
    }

    @Override
    public void updateInformation(Patient patient) {
        String password= DigestUtils.md5DigestAsHex(patient.getPassword().getBytes());
        patient.setPassword(password);
        patientMapper.update(patient);
    }
}
