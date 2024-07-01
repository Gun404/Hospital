package com.bupt.Service;

import com.bupt.Pojo.Doctor;

public interface DoctorService {
    Doctor login(Doctor doctor);

    boolean register(Doctor doctor);

    Doctor showInformation(Integer id);

    void updateInformation(Doctor doctor);
}
