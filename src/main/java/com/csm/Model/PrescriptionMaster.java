package com.csm.Model;

import javax.persistence.*;

/**
 * @Project : PatientInformation
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 12/11/2022 - 10:06 AM
 */
@Entity
@Table
public class PrescriptionMaster {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prescriptionId;
    private String doctorName;
    private String dateOfVisit;
    private String prescriptionDescription;
    @ManyToOne
    private PatientMaster patientId;
    @ManyToOne
    private DiseaseMaster diseaseId;

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public String getPrescriptionDescription() {
		return prescriptionDescription;
	}

	public void setPrescriptionDescription(String prescriptionDescription) {
		this.prescriptionDescription = prescriptionDescription;
	}

	public PatientMaster getPatientId() {
		return patientId;
	}

	public void setPatientId(PatientMaster patientId) {
		this.patientId = patientId;
	}

	public DiseaseMaster getDiseaseId() {
		return diseaseId;
	}

	public void setDiseaseId(DiseaseMaster diseaseId) {
		this.diseaseId = diseaseId;
	}

	@Override
	public String toString() {
		return "PrescriptionMaster{" +
			   "prescriptionId=" + prescriptionId +
			   ", doctorName='" + doctorName + '\'' +
			   ", dateOfVisit='" + dateOfVisit + '\'' +
			   ", prescriptionDescription='" + prescriptionDescription + '\'' +
			   ", patientId=" + patientId +
			   ", diseaseId=" + diseaseId +
			   '}';
	}
}
