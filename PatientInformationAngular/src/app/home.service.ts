import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  baseURL = "http://localhost:8080/API";

  constructor(private httpClient : HttpClient) { }

  searchPatientUsingPhone(phone: string) {
    return this.httpClient.get(this.baseURL + "/getPatientUsingPhone/" + phone);
  }
  getDiseaseList() {
    return this.httpClient.get(this.baseURL + "/getDiseaseList");
  }


  public savePrescriptionDetails(patientId : any, diseaseId : any, presDetails : any){
    let params = new HttpParams();
    params = params.append('patientId', patientId);
    params = params.append('diseaseId', diseaseId);
    params = params.append('presDetails', presDetails);
    return this.httpClient.post(this.baseURL + "/savePrescriptionDetails", params);
  }
}
