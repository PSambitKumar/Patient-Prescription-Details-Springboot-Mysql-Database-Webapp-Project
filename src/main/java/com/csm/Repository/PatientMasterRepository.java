package com.csm.Repository;

import com.csm.Model.PatientMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientMasterRepository extends JpaRepository<PatientMaster, Integer> {
	PatientMaster getPatientMaterByPatientPhone(String phone);
}