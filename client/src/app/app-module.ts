import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Search } from './pages/search/search';
import { Customer } from './pages/customer/customer';
import { Order } from './pages/order/order';
import {FormsModule} from '@angular/forms';
import { Footer } from './components/footer/footer';
import { Navbar } from './components/navbar/navbar';
import { OrderNew } from './pages/order/order-new/order-new';
import { OrderUpdate } from './pages/order/order-update/order-update';
import { Toast } from './shared/toast/toast';
import {ToastrModule} from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    App,
    Customer,
    Order,
    Search,
    Footer,
    Navbar,
    OrderNew,
    OrderUpdate,
    Toast,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule

  ],
  providers: [
    provideBrowserGlobalErrorListeners()
  ],
  bootstrap: [App]
})
export class AppModule { }
