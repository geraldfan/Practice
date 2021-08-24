import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Vendor } from './vendor';

@Injectable({
  providedIn: 'root'
})
export class VendorService {
  private baseURL = "http://localhost:8080/vendor";

  constructor(private httpClient: HttpClient) { }

  getVendorList(): Observable<Vendor[]> {
    return this.httpClient.get<Vendor[]>(`${this.baseURL}/fetch`);
  }

  createVendor(vendor: Vendor): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/insert`,vendor);
  }

  getVendorById(id: number): Observable<Vendor>{
    return this.httpClient.get<Vendor>(`${this.baseURL}/find/${id}`);
  }

  deleteVendor(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/delete/${id}`);
  }
}
