package com.bupt.Mapper;

import com.bupt.Pojo.Registration;
import com.bupt.Pojo.RegistrationVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegistrationMapper {

    @Insert("insert into registration(patient_id, doctor_id, reception_time, diagnosis_time, pay_status)" +
            " VALUES (#{patientId},#{doctorId},#{receptionTime},#{diagnosisTime},#{payStatus})")
    void insert(Registration registration);

    @Select("select r.id, doctor_id, reception_time, diagnosis_time, pay_status, d.name doctor_name" +
            " from registration r left join doctor d on r.doctor_id = d.id where r.patient_id=#{patientId}")
    List<RegistrationVO> getByPatientId(Integer patientId);
}
