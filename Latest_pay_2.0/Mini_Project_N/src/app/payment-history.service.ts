// payment-history.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentHistoryService {
  private apiUrl = 'http://localhost:8080/api/payments'; // Backend API URL

  constructor(private http: HttpClient) {}

  getPaymentHistory(): Observable<any> {
    return this.http.get(this.apiUrl); // Call the API to get payment history
  }
}
