import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Api} from '../../services/api';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-order',
  standalone: false,
  templateUrl: './order.html',
  styleUrl: './order.css'
})
export class Order implements OnInit{

  accountNo = '';
  products: any[] = [];
  orderDetails: any[] = [];
  saving = false;

  constructor(private api: Api, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.accountNo = this.route.snapshot.paramMap.get('accountNo')!;
    this.api.getProducts().subscribe(data => this.products = data);
  }

  addLine(): void {
    this.orderDetails.push({ productCode: '', productName: '', quantity: 1 });
  }

  removeLine(index: number): void {
    this.orderDetails.splice(index, 1);
  }

  updateProductFromCode(index: number): void {
    const code = this.orderDetails[index].productCode;
    const product = this.products.find(p => p.productCode === code);
    if (product) {
      this.orderDetails[index].productName = product.productName;
    }
  }

  updateProductFromName(index: number): void {
    const name = this.orderDetails[index].productName;
    const product = this.products.find(p => p.productName === name);
    if (product) {
      this.orderDetails[index].productCode = product.productCode;
    }
  }

  saveOrder(): void {
    if (this.orderDetails.length === 0) return;

    const dto = {
      accountNo: this.accountNo,
      orderStatus: 'PLACED',
      orderDetails: this.orderDetails.map(d => ({
        productCode: d.productCode,
        quantity: d.quantity
      }))
    };

    this.saving = true;
    this.api.createOrder(dto).subscribe({
      next: created => {
        this.router.navigate(['/order', created.id]);
      },
      complete: () => this.saving = false
    });
  }
}
