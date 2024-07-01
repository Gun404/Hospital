package com.bupt.Service;


import com.bupt.Pojo.Admin;

public interface AdminService {
    Admin login(Admin admin);

    boolean register(Admin admin);

    Admin showInformation(Integer id);

    void updateInformation(Admin admin);
}
