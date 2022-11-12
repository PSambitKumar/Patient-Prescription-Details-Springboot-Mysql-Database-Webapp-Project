package com.csm.Repository;

import com.csm.Model.PatientMaster;
import com.csm.Model.PrescriptionMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionMasterRepository extends JpaRepository<PrescriptionMaster, Integer> {
	List<PrescriptionMaster> getPrescriptionMastersByPatientId(PatientMaster patientId);
}