package com.csm.Controller;

import com.csm.Model.DiseaseMaster;
import com.csm.Model.PatientMaster;
import com.csm.Model.PrescriptionMaster;
import com.csm.Model.Response;
import com.csm.Repository.DiseaseMasterRepository;
import com.csm.Repository.PatientMasterRepository;
import com.csm.Repository.PrescriptionMasterRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Contended;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
	public void downloadPatientHistory(@PathVariable("patientId") String patientId,
	                                   HttpServletResponse httpServletResponse) {
		System.out.println("Inside Download Patient History");
		System.out.println("Patient Id : " + patientId);
		List<PrescriptionMaster> prescriptionMasterList = prescriptionMasterRepository.getPrescriptionMastersByPatientId(
			   patientMasterRepository.getOne(Integer.parseInt(patientId)));
		PatientMaster patientMaster = patientMasterRepository.getOne(Integer.parseInt(patientId));

		try {
			String imagePath = "C:\\Users\\sambit.pradhan\\Pictures\\sambit.jpg";
			Document myDoc = new Document(PageSize.A4);
			String fileName = "PatientInformation.pdf";
			PdfWriter writer = PdfWriter.getInstance(myDoc, httpServletResponse.getOutputStream());
			httpServletResponse.setContentType("application/pdf");
			httpServletResponse.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			myDoc.open();

			com.itextpdf.text.Font f = new com.itextpdf.text.Font();
			com.itextpdf.text.Font myFont = new com.itextpdf.text.Font();
			myFont.setStyle(com.itextpdf.text.Font.BOLD);
			myFont.setSize(9);
			Paragraph p;

			myDoc.add(new Paragraph("\n"));

//	          Adding of Image
	            Image image = Image.getInstance(imagePath);
	            image.scaleAbsoluteHeight(50);
	            image.scaleAbsoluteWidth(50);
	            image.setAlignment(Element.ALIGN_LEFT);

//	          Adding Space
			myDoc.add(new Paragraph("\n"));

//	          Adding Heading Paragraph
			f.setSize(15);
			f.setStyle(com.itextpdf.text.Font.BOLD);
			p = new Paragraph("Patient Medical History",f);
			p.setAlignment(Element.ALIGN_CENTER);
			myDoc.add(p);

//	          Adding Space
			myDoc.add(new Paragraph("\n"));

////		          Adding Heading Paragraph
//			f.setSize(10);
//			f.setStyle(com.itextpdf.text.Font.ITALIC);
//			p = new Paragraph("CSF NO. : CSF NUMBER, "+"DATED  : Date\n",f);
//			p.setAlignment(Element.ALIGN_CENTER);
//			myDoc.add(p);
//
////		          Adding Space
//			myDoc.add(new Paragraph("\n"));
//			String licenseeNmae="Sambit Kumar Pradhan";
//			String sonOrCeo=null;
//
//
//			String gpNmae=null;
//			String block=null;
//			String distNmae=null;
//			Integer pincodedt=null;
//			String postname=null;
//
////      	          For Adding Body
//			f.setSize(9);
//			f.setStyle(com.itextpdf.text.Font.NORMAL);
//    	          /*  p = new Paragraph("The "+basicInformationLicenseFinal.getClassId().getClassName()+" Contractor registration certificate issues vide this office registration No. " +
//    	                   contractorCode+" in favour of "+ licenseeNmae+ " ,"+sonOrCeo+", At-:"+ gpNmae +", Post-:"+ postname +", " +
//    	                   "Dist-: "+distNmae+", Odisha-: "+pincodedt+" is hearby Cancelled as per request of the Party.",f);
//    	            */
//
//			p = new Paragraph();
//			p.add(new Chunk("              The  Contractor registration certificate issues vide this office  ", f));
//			p.add(new Chunk(" registration No.  ", myFont));
//			p.add(new Chunk( " in favour of ", f));
//			p.add(new Chunk(licenseeNmae, myFont));
//			p.add(new Chunk(" ,"+sonOrCeo+", At-:"+ gpNmae +", Post-:"+ postname +", " + "Dist-: "+distNmae+", Odisha-: "+pincodedt+" is hearby", f));
//			p.add(new Chunk(" Cancelled", myFont));
//			p.add(new Chunk(" as per request of the Party.", f));
//
//
//			p.setAlignment(Element.ALIGN_JUSTIFIED);
//			myDoc.add(p);
//
//			myDoc.add(new Paragraph("\n\n"));
//
//
//
//
////	          Adding Signature Part
//			f.setSize(9);
//			f.setStyle(com.itextpdf.text.Font.NORMAL);
////            p = new Paragraph(
////                    basicInformationLicenseFinal.getAuthId().getDesignationId().getDesignationName()+"\n" +
////                            basicInformationLicenseFinal.getDeptId().getDeptName(),f);
//			p.setAlignment(Element.ALIGN_RIGHT);
//			myDoc.add(p);

			myDoc.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"));

			f.setSize(9);
			f.setStyle(com.itextpdf.text.Font.NORMAL);
			p = new Paragraph("** This is system generated copy, does not required seal and signature.",f);
			p.setAlignment(Element.ALIGN_CENTER);
			myDoc.add(p);

			myDoc.close();

		}catch (DocumentException | IOException e) {
			e.printStackTrace();
		}














//		try {
//			PatientMaster patientMaster = patientMasterRepository.getOne(Integer.parseInt(patientId));
//			List<PrescriptionMaster> prescriptionMasterList = prescriptionMasterRepository.getPrescriptionMastersByPatientId(
//				   patientMasterRepository.getOne(Integer.parseInt(patientId)));
//
////		Create A Beautiful PDF File Format Using Java
//			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
//			String fileName = "PatientHistory.pdf";
//			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
//			document.open();
//
////			Add Heading in PDF File
//			Paragraph paragraph = new Paragraph("Patient's Medical History");
//			paragraph.setAlignment(Element.ALIGN_CENTER);
//			document.add(paragraph);
//
////			Add Patient Details in PDF File
//			paragraph = new Paragraph("Patient Name : " + patientMaster.getPatientName());
//			paragraph.setAlignment(Element.ALIGN_LEFT);
//			document.add(paragraph);
//			paragraph = new Paragraph("Patient Phone : " +patientMaster.getPatientPhone());
//			paragraph.setAlignment(Element.ALIGN_LEFT);
//			document.add(paragraph);
//			paragraph = new Paragraph("Patient Address : " + "Bhubaneswar");
//			paragraph.setAlignment(Element.ALIGN_LEFT);
//			document.add(paragraph);
//
//			Date dob = patientMaster.getPatientDOB();
//			int age = new Date().getYear() - dob.getYear();
//			System.out.println("Age : " + age);
//
//			paragraph = new Paragraph("Patient Age : " + age);
//			paragraph.setAlignment(Element.ALIGN_LEFT);
//			document.add(paragraph);
//
//			response.setContentType("application/pdf");
//			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//			response.setHeader("Content-Length", String.valueOf(fileName.length()));
//			response.setHeader("Content-Transfer-Encoding", "binary");
//			response.setHeader("Pragma", "no-cache");
//			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//			response.setHeader("Expires", "0");
//
//			ServletOutputStream outputStream = response.getOutputStream();
//			PdfWriter.getInstance(document, outputStream);
//			document.open();
//			document.add(paragraph);
//			document.close();
//
//			outputStream.flush();
//			outputStream.close();
//			response.flushBuffer();
//
//
//		} catch (DocumentException | IOException e) {
//			throw new RuntimeException(e);
//		}


	}
}
