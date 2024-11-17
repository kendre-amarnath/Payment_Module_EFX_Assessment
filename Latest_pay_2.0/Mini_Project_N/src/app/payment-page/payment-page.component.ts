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
    { name: 'JBL Headset', price: 199.99, quantity: 1, image: '/assets/jbl-headset.jpg' },
    { name: 'Nike Shoes', price: 129.99, quantity: 1, image: '/assets/nike-shoes.jpg' },
    { name: 'One Piece Manga', price: 9.99, quantity: 3, image: '/assets/one-piece-manga.jpg' },
    { name: 'One Piece T-Shirt', price: 24.99, quantity: 2, image: '/assets/one-piece-tshirt.jpg' },
    { name: 'Katana', price: 299.99, quantity: 1, image: '/assets/katana.jpg' }
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
        // If the product exists, increase its quantity
        existingProduct.quantity++;
      } else {
        // If the product does not exist, find it from productList and add it to the cart
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
    { name: 'JBL Headset', price: 199.99, quantity: 0, image: '/assets/jbl-headset.jpg' },
    { name: 'Nike Shoes', price: 129.99, quantity: 0, image: '/assets/nike-shoes.jpg' },
    { name: 'One Piece Manga', price: 9.99, quantity: 0, image: '/assets/one-piece-manga.jpg' },
    { name: 'One Piece T-Shirt', price: 24.99, quantity: 0, image: '/assets/one-piece-tshirt.jpg' },
    { name: 'Katana', price: 299.99, quantity: 0, image: '/assets/katana.jpg' }
  ];
}
