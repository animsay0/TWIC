import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class Api {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getCustomers(query: string, page: number, size: number): Observable<any> {
    const params = `?search=${query}&page=${page}&size=${size}`;
    return this.http.get(`${this.baseUrl}/customers${params}`);
  }


  getCustomer(accountNo: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/customers/${accountNo}`);
  }

  getCustomerOrders(accountNo: string, page: number, size: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/orders/customer/${accountNo}?page=${page}&size=${size}`);
  }

  getVersion(): Observable<any> {
    return this.http.get(`${this.baseUrl}/version`, { responseType: 'text' });
  }

  getAdvice(): Observable<any> {
    return this.http.get(`${this.baseUrl}/advice`);
  }

  getProducts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/products`);
  }

  createOrder(order: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/orders`, order);
  }

  getOrder(orderId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/orders/${orderId}`);
  }

  updateOrder(orderId: number, dto: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/orders/${orderId}`, dto);
  }

}
