export class PaymentResponse {
    orderId: string;
    paymentId: string;
    paymentLinkId: string;
    paymentStatus: string;
    paymentSignature: string;
  
    constructor(
      orderId:string ,
      paymentId: string,
      paymentLink: string,
      paymentStatus: string,
      paymentsignature: string
    ) {
      this.orderId = orderId;
      this.paymentId = paymentId;
      this.paymentLinkId = paymentLink;
      this.paymentStatus = paymentStatus;
      this.paymentSignature = paymentsignature;
    }
  }
  