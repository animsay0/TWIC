import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Api} from '../../services/api';

@Component({
  selector: 'app-customer',
  standalone: false,
  templateUrl: './customer.html',
  styleUrl: './customer.css'
})
export class Customer {

  accountNo = '';
  customer: any = null;
  orders: any[] = [];
  page = 0;
  size = 10;
  totalPages = 0;

  constructor(private api: Api, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.accountNo = this.route.snapshot.paramMap.get('accountNo')!;
    localStorage.setItem('lastCustomerAccountNo', this.accountNo); // 🔥 sauvegarde

    this.loadCustomer();
    this.loadOrders();
  }

  loadCustomer(): void {
    this.api.getCustomer(this.accountNo).subscribe(data => {
      this.customer = data;
    });
  }

  loadOrders(page = 0): void {
    this.page = page;
    this.api.getCustomerOrders(this.accountNo, this.page, this.size).subscribe(data => {
      this.orders = data.content;
      this.totalPages = data.totalPages;
    });
  }

  nextPage(): void {
    if (this.page + 1 < this.totalPages) {
      this.loadOrders(this.page + 1);
    }
  }

  previousPage(): void {
    if (this.page > 0) {
      this.loadOrders(this.page - 1);
    }
  }

  goToOrder(orderId: number): void {
    this.router.navigate(['/order', orderId]);
  }

  createOrder(): void {
    this.router.navigate(['/order/new', this.accountNo]);
  }

}
