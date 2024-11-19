import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { PaymentService } from '../services/paymentService.service';
import { OrderService, Order } from '../services/order.service';
import { PaymentResponse } from '../payment-response';
import { take } from 'rxjs';

@Component({
  selector: 'app-paystatus',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './paystatus.component.html',
  styleUrls: ['./paystatus.component.css'], // Fixed 'styleUrls'
})
export class PaystatusComponent implements OnInit {
goToPaymentPage() {
this.router.navigate(['']);
}
  currentDate: Date = new Date();
  status: boolean = true;
  order?: Order;
  trackingStatus: string[] = ["Ordered", "Shipped", "On the way", "Delivered"];
  constructor(
    private route: ActivatedRoute,
    private paymentService: PaymentService,
    private orderService: OrderService,
    private router: Router,
  ) {}
  paymentResponse!: PaymentResponse
  private initCallCount = 0;
  ngOnInit(): void {
    this.initCallCount++;
    console.log(`ngOnInit has been called ${this.initCallCount} times`);



    this.paymentResponse = {
      orderId: '',
      paymentStatus: '',
      paymentId: '',
      paymentSignature: '',
      paymentLinkId: ''
    };
  
    this.route.queryParamMap.pipe(take(1)).subscribe(params => {
      const referenceId = params.get('razorpay_payment_link_reference_id')?.slice(3);
      if (!this.paymentResponse.orderId && referenceId) { // Check if data has already been set
        this.paymentResponse.orderId = referenceId;
        this.paymentResponse.paymentStatus = params.get('razorpay_payment_link_status') || '';
        this.paymentResponse.paymentLinkId = params.get('razorpay_payment_link_id') || '';
        this.paymentResponse.paymentId = params.get('razorpay_payment_id') || '';
        this.paymentResponse.paymentSignature = params.get('razorpay_signature') || '';
  
        // Save data only once
        this.paymentService.setPaymentDetails(this.paymentResponse).subscribe(d => console.log('Data saved:', d));
      } else {
        console.log('Data already set, skipping save.');
      }
  
      // Process status change
      const paymentStatus = params.get('razorpay_payment_link_status');
      if (referenceId) {
        const orderId = Number(referenceId);
        if (paymentStatus === 'paid') {
          this.status = true;
          this.paymentService.changeStatus(orderId, 'SUCCESS').subscribe(d => console.log('Status updated to SUCCESS:', d));
        } else {
          this.status = false;
          this.paymentService.changeStatus(orderId, 'FAILED').subscribe(d => console.log('Status updated to FAILED:', d));
        }
        // Fetch order details
        this.fetchOrderDetails(orderId);

        
      }
    });
  }

  returnToHome(): void {
    this.router.navigate(['']);
}
  
  fetchOrderDetails(orderId: number): void {
    this.orderService.getOrderById(orderId).subscribe({
      next: data => {
        this.order = data;
        console.log('Fetched order:', this.order);
      },
      error: err => console.error('Error fetching order:', err),
    });
  }
  // how to dislplay order details in the payststus component html file
  // <div *ngIf="order">
  // <h2>Order Details</h2>
  // <p>Order ID: {{ order.id }}</p>
  // <p>Customer Name: {{ order.customerName }}</p>
  // <p>Customer Email: {{ order.customerEmail }}</p>
  // <p>Customer Phone: {{ order.customerPhone }}</p>
  // <p>Amount: {{ order.amount }}</p>
  // <p>Address: {{ order.address }}</p>
  // <p>Status: {{ order.status }}</p>
  // <p>Tracking Status: {{ trackingStatus[order.trackingStatus] }}</p>
  //payment id 
  // <p>Payment ID: {{ paymentResponse.paymentId }}</p>
  // </div>

  // <div *ngIf="!order">
  // <p>Loading order details...</p>
  // </div>

  // <button (click)="returnToHome()">Return to Home</button>

  // <div *ngIf="status">

  // <h2>Payment Successful</h2>
  // <p>Order ID: {{ order?.id }}</p>
  // <p>Amount: {{ order?.amount }}</p>
  // <p>Payment ID: {{ paymentResponse.paymentId }}</p>
  // <p>Payment Signature: {{ paymentResponse.paymentSignature }}</p>
  // <p>Payment Link ID: {{ paymentResponse.paymentLinkId }}</p>
  // </div>




   


}







      
