import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PaymentService } from '../services/paymentService.service';
import { Router } from '@angular/router';

interface Product {
  name: string;
  price: number;
  quantity: number;
  image: string;
}

@Component({
  selector: 'app-payment-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './payment-page.component.html',
  styleUrls: ['./payment-page.component.css']
})

export class PaymentPageComponent {
  constructor(private paymentservice: PaymentService, private route: Router) {}

  
  products: Product[] = [
    { name: 'JBL Headset', price: 1999.9, quantity: 1, image: '/assets/jbl-headset.jpg' },
    { name: 'Nike Shoes', price: 1299.9, quantity: 1, image: '/assets/nike-shoes.jpg' },
    { name: 'One Piece Manga', price: 999, quantity: 1, image: '/assets/one-piece-manga.jpg' },
    { name: 'One Piece T-Shirt', price: 249.9, quantity: 1, image: '/assets/one-piece-tshirt.jpg' },
    { name: 'Katana', price: 2999.9, quantity: 1, image: '/assets/katana.jpg' }
  ];

  totalAmount = this.calculateTotal();

  increaseQuantity(index: number) {
    this.products[index].quantity++;
    this.totalAmount = this.calculateTotal();
  }

  decreaseQuantity(index: number) {
    if (this.products[index].quantity > 0) {
      this.products[index].quantity--;
      if (this.products[index].quantity === 0) {
        this.deleteItem(index);
      } else {
        this.totalAmount = this.calculateTotal();
      }
    }
  }

  deleteItem(index: number) {
    this.products.splice(index, 1);
    this.totalAmount = this.calculateTotal();
  }

  selectedProductName: string = '';

  addItem() {
    if (this.selectedProductName) {
      const existingProduct = this.products.find(product => product.name === this.selectedProductName);
      if (existingProduct) {

        existingProduct.quantity++;
      } else {
        const newProduct = this.productList.find(product => product.name === this.selectedProductName);
        if (newProduct) {
          this.products.push({ ...newProduct, quantity: 1 });
        }
      }
      this.totalAmount = this.calculateTotal();
    }
  }

  calculateTotal() {
    return this.products.reduce((acc, product) => acc + (product.price * product.quantity), 0);
  }

  payment = { name: '', email: '', contact: ' ', status: 'PENDING', amount: this.calculateTotal(), address: ' ' };

  payNow(payment: any, event: Event): void {
    event.stopPropagation();
    payment.amount = this.calculateTotal();
    this.paymentservice.createOrder(payment).subscribe(order => {
      this.paymentservice.createPaymentLink(order).subscribe(data => {
        window.location.href = data.short_url;
      });
    });
  }

  productList: Product[] = [
    { name: 'JBL Headset', price: 1999.9, quantity: 0, image: '/assets/jbl-headset.jpg' },
    { name: 'Nike Shoes', price: 1299.9, quantity: 0, image: '/assets/nike-shoes.jpg' },
    { name: 'One Piece Manga', price: 999, quantity: 0, image: '/assets/one-piece-manga.jpg' },
    { name: 'One Piece T-Shirt', price: 249.9, quantity: 0, image: '/assets/one-piece-tshirt.jpg' },
    { name: 'Katana', price: 2999.9, quantity: 0, image: '/assets/katana.jpg' }
  ];
}
