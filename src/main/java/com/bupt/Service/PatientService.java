package com.bupt.Service;

import com.bupt.Pojo.Patient;

public interface PatientService {
    Patient login(Patient patient);

    boolean register(Patient patient);

    Patient showInformation(Integer id);

    void updateInformation(Patient patient);
}
