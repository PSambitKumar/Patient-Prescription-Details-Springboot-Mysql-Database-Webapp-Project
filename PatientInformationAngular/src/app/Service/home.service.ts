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

  public savePrescriptionDetails(details: HttpParams) : Observable<any> {
    return this.httpClient.post(this.baseURL + "/savePrescriptionDetails", details);
  }

  public downloadPatientHistory(patientId: number) : Observable<any> {
    return this.httpClient.get(this.baseURL + "/downloadPatientHistory/" + patientId);
  }
}
