import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { Search } from './pages/search/search';
import { Customer } from './pages/customer/customer';
import { Order } from './pages/order/order';
import {OrderNew} from './pages/order/order-new/order-new';
import {OrderUpdate} from './pages/order/order-update/order-update';




const routes: Routes = [
  { path: '', redirectTo: 'search', pathMatch: 'full' },
  { path: 'search', component: Search },
  { path: 'customer/:accountNo', component: Customer },
  { path: 'order/:orderId', component: OrderUpdate },
  { path: 'order/new/:accountNo', component: OrderNew }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
