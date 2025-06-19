import {Component, OnInit} from '@angular/core';
import {Api} from '../../../services/api';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-order-new',
  standalone: false,
  templateUrl: './order-new.html',
  styleUrl: './order-new.css'
})
export class OrderNew implements OnInit{
  accountNo = '';
  products: any[] = [];
  orderDetails: any[] = [];

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

  updateFromCode(index: number): void {
    const selected = this.products.find(p => p.productCode === this.orderDetails[index].productCode);
    if (selected) this.orderDetails[index].productName = selected.productName;
  }

  updateFromName(index: number): void {
    const selected = this.products.find(p => p.productName === this.orderDetails[index].productName);
    if (selected) this.orderDetails[index].productCode = selected.productCode;
  }

  save(): void {
    if (this.orderDetails.length === 0) return;

    const dto = {
      accountNo: this.accountNo,
      orderStatus: 'PLACED',
      orderDetails: this.orderDetails.map(d => ({
        productNo: d.productCode,
        quantity: d.quantity
      }))
    };

    console.log(dto)

    this.api.createOrder(dto).subscribe(created => {
      this.router.navigate(['/order', created.id]);
      localStorage.setItem('lastOrderId', created.id.toString()); // ✅

    });
  }
}
