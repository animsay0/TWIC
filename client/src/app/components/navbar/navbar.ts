import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: false,
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {

  currentUrl: string = '';

  constructor(private router: Router) {
    this.router.events.subscribe(() => {
      this.currentUrl = this.router.url;
    });
  }

  goToLastCustomer(): void {
    const last = localStorage.getItem('lastCustomerAccountNo');
    if (last) {
      this.router.navigate(['/customer', last]);
    } else {
      this.router.navigate(['/search']);
    }
  }

  isCustomerActive(): boolean {
    return this.currentUrl.startsWith('/customer');
  }

  goToLastOrder(): void {
    const lastOrderId = localStorage.getItem('lastOrderId');
    if (lastOrderId) {
      this.router.navigate(['/order', lastOrderId]);
    } else {
      this.router.navigate(['/search']);
    }
  }

  isOrderActive(): boolean {
    return this.router.url.startsWith('/order');
  }

}
