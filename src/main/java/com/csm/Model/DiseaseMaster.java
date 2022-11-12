package com.csm.Model;

import javax.persistence.*;

/**
 * @Project : PatientInformation
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 12/11/2022 - 9:50 AM
 */
@Entity
@Table
public class DiseaseMaster {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diseaseId;
	@Column
    private String diseaseName;

    public DiseaseMaster() {
    }

    public DiseaseMaster(int diseaseId, String diseaseName, String diseaseDescription) {
	   this.diseaseId = diseaseId;
	   this.diseaseName = diseaseName;
    }

    public int getDiseaseId() {
	   return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
	   this.diseaseId = diseaseId;
    }

    public String getDiseaseName() {
	   return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
	   this.diseaseName = diseaseName;
    }

	@Override
	public String toString() {
		return "DiseaseMaster{" +
			   "diseaseId=" + diseaseId +
			   ", diseaseName='" + diseaseName + '\'' +
			   '}';
	}
}
