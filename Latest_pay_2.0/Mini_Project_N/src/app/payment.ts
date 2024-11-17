export class Payment {
    name: string;
    email: string;
    contact: string;
    amount: number;
    address: string;
    status: string;
  
  
    constructor(
      name: string,
      email: string,
      contact: string,
      amount: number,
      address: string,
      status: string,
   
    ) {

    
      this.name = name;
      this.email = email;
      this.contact = contact;
      this.amount = amount;
      this.address = address;
      this.status = status;
    }
  }
  