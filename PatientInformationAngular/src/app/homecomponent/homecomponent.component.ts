import { Component, OnInit } from '@angular/core';
import {HomeService} from "../home.service";
import * as $ from 'jquery';

@Component({
  selector: 'app-homecomponent',
  templateUrl: './homecomponent.component.html',
  styleUrls: ['./homecomponent.component.css']
})
export class HomecomponentComponent implements OnInit {
  patientId : any
  patientName : any;
  patientPhone : any;
  gender: any;
  dob : any;
  diseaseList : any = [];

  constructor(private service : HomeService) { }

  ngOnInit(): void {
    this.getDiseaseList();
  }

  searchPatientUsingPhone(event: any) {
    this.service.searchPatientUsingPhone(event.target.value).subscribe(data => {
      let data1 = JSON.parse(JSON.stringify(data));
      console.log(data1);
      this.patientName = data1.patientName;
      this.patientPhone = data1.patientPhone;
      this.gender = data1.patientGender;
      this.dob = data1.patientDOB;
      this.patientId = data1.patientId;
    });
  }

  getDiseaseList() {
    this.service.getDiseaseList().subscribe(data => {
      let data1 = JSON.parse(JSON.stringify(data));
      console.log(data1);
      this.diseaseList = data1;
    });
  }

  saveInformation(){
    let diseaseId = $('#diseaseName').val();
    let persDetails = $('#prescriptionDetails').val();

    this.service.savePrescriptionDetails(this.patientId, diseaseId, persDetails).subscribe(data => {
      console.log(data);
    })
  }

}
