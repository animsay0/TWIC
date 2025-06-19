import {Component, OnInit} from '@angular/core';
import {Api} from '../../../services/api';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-order-update',
  standalone: false,
  templateUrl: './order-update.html',
  styleUrl: './order-update.css'
})
export class OrderUpdate implements OnInit{
  orderId: number = 0;
  order: any = null;

  orderStatus: string = '';
  originalStatus: string = '';
  modified = false;

  constructor(private api: Api, private route: ActivatedRoute, private router: Router, private toastr: ToastrService) {}

  ngOnInit(): void {
    this.orderId = +this.route.snapshot.paramMap.get('orderId')!;
    this.api.getOrder(this.orderId).subscribe(data => {
      this.order = data;
      this.orderStatus = data.orderStatus;
      this.originalStatus = data.orderStatus;
      localStorage.setItem('lastOrderId', this.orderId.toString()); // ✅

    });
  }

  onStatusChange(): void {
    this.modified = this.orderStatus !== this.originalStatus;

    if (this.orderStatus === 'DELIVERED') {
      this.order.deliveredTimestamp = new Date().toISOString();
      this.order.cancelledTimestamp = '';
    } else if (this.orderStatus === 'CANCELLED') {
      this.order.cancelledTimestamp = new Date().toISOString();
      this.order.deliveredTimestamp = '';
    }
  }

  modify(): void {
    if (!this.modified) return;

    this.api.updateOrder(this.orderId, {
      orderStatus: this.orderStatus
    }).subscribe({
      next: updated => {
        this.toastr.success('Order updated successfully!', 'Success');
        this.router.navigate(['/order', updated.id]);
      },
      error: () => {
        this.toastr.error('Failed to update order.', 'Error', { positionClass : 'toast-top-center' });
      }
    });
  }

}
