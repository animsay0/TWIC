import { Component } from '@angular/core';
import {Api} from '../../services/api';


@Component({
  selector: 'app-search',
  standalone: false,
  templateUrl: './search.html',
  styleUrl: './search.css'
})
export class Search {

  query = '';
  results: any[] = [];
  advice: any;
  noResults = false;

  constructor(private api: Api) {}

  search(): void {
    this.api.getCustomers(this.query).subscribe(data => {
      this.results = data.content || [];
      this.noResults = this.results.length === 0;
    });
  }

  ngOnInit(): void {
    this.api.getAdvice().subscribe(data => this.advice = data);
  }
}
