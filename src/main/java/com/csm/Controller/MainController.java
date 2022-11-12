package com.csm.Controller;

import com.csm.Model.DiseaseMaster;
import com.csm.Model.PatientMaster;
import com.csm.Model.PrescriptionMaster;
import com.csm.Model.Response;
import com.csm.Repository.DiseaseMasterRepository;
import com.csm.Repository.PatientMasterRepository;
import com.csm.Repository.PrescriptionMasterRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Contended;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

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
	private PatientMasterRepository patientMasterRepository;
	@Autowired
	private PrescriptionMasterRepository prescriptionMasterRepository;

	@ResponseBody
	@GetMapping(value = "getPatientUsingPhone/{phone}")
	public ResponseEntity<PatientMaster> getPatientUsingPhone(@PathVariable("phone") String phone) {
		System.out.println("Inside Get Patient Using Phone");
		PatientMaster patientMater = null;
		try {
			patientMater = patientMasterRepository.getPatientMaterByPatientPhone(phone);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
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
		PrescriptionMaster prescriptionMaster;
		Response response = new Response();
		try {
			PatientMaster patientMaster = patientMasterRepository.getOne(Integer.parseInt(patientId));
			DiseaseMaster diseaseMaster = diseaseMasterRepository.getOne(Integer.parseInt(diseaseId));

			prescriptionMaster = new PrescriptionMaster();
			prescriptionMaster.setDoctorName("Dr. Sambit Kumar Pradhan");
			prescriptionMaster.setDateOfVisit(new Date().toString());
			prescriptionMaster.setPrescriptionDescription(presDetails);
			prescriptionMaster.setPatientId(patientMaster);
			prescriptionMaster.setDiseaseId(diseaseMaster);
			prescriptionMaster = prescriptionMasterRepository.save(prescriptionMaster);
		} catch (NumberFormatException e) {
			throw new RuntimeException(e);
		}
		if(prescriptionMaster.getPrescriptionId() > 0) {
			response.setStatus("Success");
			response.setMessage("Prescription Details Saved Successfully");
			return ResponseEntity.ok(response);
		} else {
			response.setStatus("Failed");
			response.setMessage("Prescription Details Not Saved");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping(value = "downloadPatientHistory/{patientId}")
	public void downloadPatientHistory(@PathVariable("patientId") String patientId) {
		System.out.println("Inside Download Patient History");
		System.out.println("Patient Id : " + patientId);
		try {
			List<PrescriptionMaster> prescriptionMasterList = prescriptionMasterRepository.getPrescriptionMastersByPatientId(
				   patientMasterRepository.getOne(Integer.parseInt(patientId)));
//		Create A Beautiful PDF File Format Using Java
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			String fileName = "PatientHistory.pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();


		} catch (DocumentException e) {
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}


	}
}
