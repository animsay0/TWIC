import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class App {

  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getVersion(): Observable<string> {
    return this.http.get(`${this.baseUrl}/version`, { responseType: 'text' });
  }

}
