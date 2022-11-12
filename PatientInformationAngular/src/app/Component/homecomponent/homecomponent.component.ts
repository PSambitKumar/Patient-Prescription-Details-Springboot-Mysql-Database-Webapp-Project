import { Component, OnInit } from '@angular/core';
import {HomeService} from "../../Service/home.service";
import {PatientMaster} from "../../Model/PatientMaster";
import {HttpParams} from "@angular/common/http";
import * as $ from 'jquery';

@Component({
  selector: 'app-homecomponent',
  templateUrl: './homecomponent.component.html',
  styleUrls: ['./homecomponent.component.css']
})
export class HomecomponentComponent implements OnInit {
  toggle : any = true;
  patientMaster : PatientMaster = new PatientMaster();
  diseaseList : any = [];
  diseaseId : any;
  prescriptionDetails: any;


  constructor(private homeService : HomeService) { }

  ngOnInit(): void {
    this.getDiseaseList();
  }

  searchPatientByPhoneNumber(event : any){
    if (event.target.value.length == 10){
      this.homeService.searchPatientUsingPhone(event.target.value).subscribe((data : any) => {
        this.patientMaster = data;
        console.log(this.patientMaster);
      });
    }else {
      this.patientMaster = new PatientMaster();
      return;
    }
  }

  getDiseaseList(){
    this.homeService.getDiseaseList().subscribe((data : any) => {
      this.diseaseList = data;
    });
  }

  submitPrescription(){
    this.diseaseId = $("#diseaseName").val();
    this.prescriptionDetails = $("#prescriptionDetails").val();

    let details = new HttpParams();
    details = details.append('patientId', this.patientMaster.patientId);
    details = details.append('diseaseId', this.diseaseId);
    details = details.append('presDetails', this.prescriptionDetails);

    this.homeService.savePrescriptionDetails(details).subscribe((data : any) => {
      console.log(data);
      if (data.status == "Success"){
        alert("Prescription saved successfully");
        this.patientMaster = new PatientMaster();
        $("#diseaseName").val("");
        $("#prescriptionDetails").val("");
        $('#patientPhoneNumber').val("");
      }else {
        alert("Prescription not saved");
      }
    })
  }

  downloadPatientHistory(){
    // window.open("http://localhost:8080/API/downloadPatientHistory/" + this.patientMaster.patientId);
    this.homeService.downloadPatientHistory(this.patientMaster.patientId).subscribe((data : any) => {
      console.log(data);
    });
  }

}
