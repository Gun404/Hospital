package com.bupt.Service;

import com.bupt.Pojo.Admin;

import java.util.List;

public interface ManageAdminService {
    List<Admin> showAllAdmin();

    void deleteAdmin(Integer id);

    void updateAdmin(Admin admin);

    List<Admin> showPendingAdmin();

    void approve(Integer id, Integer status);
}
