import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Search } from './pages/search/search';
import { Customer } from './pages/customer/customer';
import { Order } from './pages/order/order';
import { Test } from './test/test';
import {FormsModule} from '@angular/forms';
import { Footer } from './components/footer/footer';
import { Navbar } from './components/navbar/navbar';

@NgModule({
  declarations: [
    App,
    Customer,
    Order,
    Search,
    Footer,
    Navbar,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,

  ],
  providers: [
    provideBrowserGlobalErrorListeners()
  ],
  bootstrap: [App]
})
export class AppModule { }
