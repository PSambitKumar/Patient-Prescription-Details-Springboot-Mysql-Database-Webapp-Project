package com.csm.Repository;

import com.csm.Model.PrescriptionMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionMasterRepository extends JpaRepository<PrescriptionMaster, Integer> {
}