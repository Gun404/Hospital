package com.bupt.Service.ServiceImpl;

import com.bupt.Constant.StatusConstant;
import com.bupt.Mapper.PatientMapper;
import com.bupt.Pojo.Doctor;
import com.bupt.Pojo.Patient;
import com.bupt.Service.ManagePatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ManagePatientServiceImpl implements ManagePatientService {
    @Autowired
    private PatientMapper patientMapper;


    @Override
    public List<Patient> showAllPatient() {
        return patientMapper.getAll();
    }

    @Transactional
    @Override
    public void deletePatient(Integer id) {
        String name = patientMapper.getById(id).getName();
        Patient patient=Patient.builder().id(id).name(name).build();
        patientMapper.deleteById(patient);
    }

    @Override
    public void updatePatient(Patient patient) {
        String password= DigestUtils.md5DigestAsHex(patient.getPassword().getBytes());
        patient.setPassword(password);
        patientMapper.update(patient);

    }

    @Override
    public List<Patient> showPendingPatient() {
        return patientMapper.getPending();
    }

    @Transactional
    @Override
    public void approve(Integer id, Integer status) {
        String name = patientMapper.getById(id).getName();
        if (Objects.equals(status, StatusConstant.DISABLE)){
            Patient patient=Patient.builder().id(id).name(name).build();
            patientMapper.deleteById(patient);
        }
        Patient patient=Patient.builder()
                .id(id).status(status).name(name)
                .build();
        patientMapper.update(patient);
    }
}
