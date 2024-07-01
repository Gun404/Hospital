package com.bupt.Service.ServiceImpl;

import com.bupt.Constant.StatusConstant;
import com.bupt.Mapper.AdminMapper;
import com.bupt.Pojo.Admin;
import com.bupt.Pojo.Doctor;
import com.bupt.Service.ManageAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ManageAdminServiceImpl implements ManageAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> showAllAdmin() {
        return adminMapper.getAll();
    }

    @Transactional
    @Override
    public void deleteAdmin(Integer id) {
        String name = adminMapper.getById(id).getName();
        Admin admin=Admin.builder().id(id).name(name).build();
        adminMapper.deleteById(admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        String password= DigestUtils.md5DigestAsHex(admin.getPassword().getBytes());
        admin.setPassword(password);
        adminMapper.update(admin);
    }

    @Override
    public List<Admin> showPendingAdmin() {
        return adminMapper.getPending();
    }

    @Transactional
    @Override
    public void approve(Integer id, Integer status) {
        String name = adminMapper.getById(id).getName();
        if (Objects.equals(status, StatusConstant.DISABLE)){
            Admin admin=Admin.builder().id(id).name(name).build();
            adminMapper.deleteById(admin);
        }
        Admin admin=Admin.builder()
                .id(id).status(status).name(name)
                .build();
        adminMapper.update(admin);
    }
}
