package com.csm.Model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @Project : PatientInformation
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 12/11/2022 - 10:05 AM
 */
@Entity
@Table
public class PatientMaster {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    private String patientName;
    private String patientPhone;
    private Date patientDOB;
    private String patientEmail;
    private String patientGender;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public Date getPatientDOB() {
		return patientDOB;
	}

	public void setPatientDOB(Date patientDOB) {
		this.patientDOB = patientDOB;
	}

	@Override
	public String toString() {
		return "PatientMater{" +
			   "patientId=" + patientId +
			   ", patientName='" + patientName + '\'' +
			   ", patientPhone='" + patientPhone + '\'' +
			   ", patientDOB=" + patientDOB +
			   ", patientEmail='" + patientEmail + '\'' +
			   ", patientGender='" + patientGender + '\'' +
			   '}';
	}
}
