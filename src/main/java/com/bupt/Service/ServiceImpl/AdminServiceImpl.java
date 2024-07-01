package com.bupt.Service.ServiceImpl;

import com.bupt.Constant.MessageConstant;
import com.bupt.Constant.StatusConstant;
import com.bupt.Exception.AccountNotFoundException;
import com.bupt.Exception.AccountNotReviewedException;
import com.bupt.Exception.PasswordErrorException;
import com.bupt.Mapper.AdminMapper;
import com.bupt.Pojo.Admin;
import com.bupt.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     *管理员登录
     * @param admin
     * @return
     */
    @Override
    public Admin login(Admin admin) {
        String name= admin.getName();
        String password= admin.getPassword();

        //根据姓名，查询数据库中的数据
        Admin a=adminMapper.getByName(name);

        //处理各种异常情况（账户不存在，密码错误，账号未审核）
        if (a==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
       //如果密码需要强制修改，说明是管理员初始账户。
        if (a.getMustChangePassword()==1&&password.equals(a.getPassword())){
           return a;
        }

        //密码比对
        // 后期需要进行md5加密，然后再进行比对
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(a.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (a.getStatus() == StatusConstant.DISABLE) {
            //账号未审核
            throw new AccountNotReviewedException(MessageConstant.NOT_REVIEWED);
        }
        return a;

    }

    @Override
    public boolean register(Admin admin) {
        String password= DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        admin.setPassword(password);
        admin.setStatus(0);
        admin.setMustChangePassword(0);
        Admin a = adminMapper.getByName(admin.getName());
        if (a!=null){
            return false;
        }
        adminMapper.insert(admin);
        return true;
    }

    @Override
    public Admin showInformation(Integer id) {
        Admin admin=adminMapper.getById(id);
        return admin;
    }

    @Override
    public void updateInformation(Admin admin) {
        String password= DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        admin.setPassword(password);
        admin.setMustChangePassword(0);
        adminMapper.update(admin);

    }
}
