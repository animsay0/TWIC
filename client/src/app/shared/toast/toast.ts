import { Component } from '@angular/core';

@Component({
  selector: 'app-toast',
  standalone: false,
  templateUrl: './toast.html',
  styleUrl: './toast.css'
})
export class Toast {
  visible = false;
  message = '';
  type: 'success' | 'error' = 'success';

  show(msg: string, type: 'success' | 'error' = 'success') {
    this.message = msg;
    this.type = type;
    this.visible = true;

    setTimeout(() => this.visible = false, 3000);
  }
}
