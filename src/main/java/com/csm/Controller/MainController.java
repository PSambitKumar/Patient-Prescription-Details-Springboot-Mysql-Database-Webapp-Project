package com.csm.Controller;

import com.csm.Model.DiseaseMaster;
import com.csm.Model.PatientMater;
import com.csm.Model.PrescriptionMaster;
import com.csm.Repository.DiseaseMasterRepository;
import com.csm.Repository.PatientMaterRepository;
import com.csm.Repository.PrescriptionMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Contended;

/**
 * @Project : PatientInformation
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 12/11/2022 - 10:58 AM
 */
@Controller
@RequestMapping(value = "/API/")
@CrossOrigin(origins = "*")
public class MainController {

	@Autowired
	private DiseaseMasterRepository diseaseMasterRepository;
	@Autowired
	private PatientMaterRepository patientMaterRepository;
	@Autowired
	private PrescriptionMasterRepository prescriptionMasterRepository;

	@ResponseBody
	@GetMapping(value = "getPatientUsingPhone/{phone}")
	public ResponseEntity<PatientMater> getPatientUsingPhone(@PathVariable("phone") String phone) {
		System.out.println("Inside Get Patient Using Phone");
		PatientMater patientMater = patientMaterRepository.getPatientMaterByPatientPhone(phone);
		System.out.println("Patient Master : " + patientMater);
		return ResponseEntity.ok(patientMater);
	}

	@ResponseBody
	@GetMapping(value = "getDiseaseList")
	public ResponseEntity<?> getDiseaseList() {
		System.out.println("Inside Get Disease List");
		return ResponseEntity.ok(diseaseMasterRepository.findAll());
	}

	@ResponseBody
	@PostMapping(value = "savePrescriptionDetails")
	public ResponseEntity<?> savePrescriptionDetails(@RequestParam("patientId") String patientId,
	                                                 @RequestParam("diseaseId") String diseaseId,
	                                                 @RequestParam("presDetails") String presDetails) {
		System.out.println("Inside Save Prescription Details");
		System.out.println("Patient Id : " + patientId + " Disease Id : " + diseaseId + " Pres Details : " + presDetails);
		PatientMater patientMater = patientMaterRepository.getOne(Integer.parseInt(patientId));
		DiseaseMaster diseaseMaster = diseaseMasterRepository.getOne(Integer.parseInt(diseaseId));
		PrescriptionMaster prescriptionMaster = new PrescriptionMaster();
		prescriptionMaster.setDoctorName("Dr. Sanjay Kumar");
		prescriptionMaster.setPrescriptionDescription(presDetails);
		prescriptionMaster.setPatientId(patientMater);
		prescriptionMaster.setDiseaseId(diseaseMaster);
		prescriptionMasterRepository.save(prescriptionMaster);
		if (prescriptionMaster.getPrescriptionId() > 0) {
			return ResponseEntity.ok("Prescription Saved Successfully");
		} else {
			return ResponseEntity.ok("Prescription Not Saved");
		}
	}
}
