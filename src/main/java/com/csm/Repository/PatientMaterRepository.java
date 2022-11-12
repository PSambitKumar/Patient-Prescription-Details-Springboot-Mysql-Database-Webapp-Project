package com.csm.Repository;

import com.csm.Model.PatientMater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientMaterRepository extends JpaRepository<PatientMater, Integer> {
	PatientMater getPatientMaterByPatientPhone(String phone);
}