import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PaymentPageComponent } from "./payment-page/payment-page.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, PaymentPageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Mini_Project';
}
