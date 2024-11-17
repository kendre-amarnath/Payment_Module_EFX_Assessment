import { Component, OnInit } from '@angular/core';
import { PaymentHistoryService } from '../payment-history.service';
//import { Component, OnInit } from '@angular/core';

import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-payment-history',
  templateUrl: './payment-history.component.html',
  styleUrls: ['./payment-history.component.css']
})
export class PaymentHistoryComponent implements OnInit {
  paymentHistory: any[] = [];
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(private paymentHistoryService: PaymentHistoryService) {}

  ngOnInit(): void {
    this.fetchPaymentHistory();
  }

  fetchPaymentHistory(): void {
    this.isLoading = true;
    this.paymentHistoryService.getPaymentHistory().subscribe({
      next: (data) => {
        this.paymentHistory = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Failed to load payment history';
        this.isLoading = false;
      }
    });
  }
}
