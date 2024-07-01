package com.bupt.Service;

import com.bupt.Pojo.Patient;

import java.util.List;

public interface ManagePatientService {
    List<Patient> showAllPatient();

    void deletePatient(Integer id);

    void updatePatient(Patient patient);

    List<Patient> showPendingPatient();

    void approve(Integer id, Integer status);
}
