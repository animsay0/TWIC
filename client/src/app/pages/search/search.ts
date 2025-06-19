import { Component } from '@angular/core';
import {Api} from '../../services/api';


@Component({
  selector: 'app-search',
  standalone: false,
  templateUrl: './search.html',
  styleUrl: './search.css'
})
export class Search {

  /*query = '';
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
  }*/

  /*query = '';
  results: any[] = [];
  advice: string = '';
  noResults = false;

  page = 0;
  size = 10;
  totalPages = 0;

  constructor(private api: Api) {}

  ngOnInit(): void {
    this.api.getAdvice().subscribe(data => {
      this.advice = data?.slip?.advice || data;
      alert(`💡 Advice : ${this.advice}`);
    });
  }

  search(page = 0): void {
    this.page = page;
    this.api.getCustomers(this.query, page, this.size).subscribe(data => {
      this.results = data.content;
      this.totalPages = data.totalPages;
      this.noResults = this.results.length === 0;
    });
  }

  previousPage(): void {
    if (this.page > 0) this.search(this.page - 1);
  }

  nextPage(): void {
    if (this.page + 1 < this.totalPages) this.search(this.page + 1);
  }*/

  query = '';
  results: any[] = [];
  advice: string = '';
  noResults = false;

  page = 0;
  size = 10;
  totalPages = 0;

  constructor(private api: Api) {}

  ngOnInit(): void {
    this.api.getAdvice().subscribe(data => {
      this.advice = data?.slip?.advice || data;
      alert(`💡 Advice : ${this.advice}`);
    });
  }

  search(page = 0): void {
    this.page = page;
    this.api.getCustomers(this.query, this.page, this.size).subscribe(data => {
      this.results = data.content;
      this.totalPages = data.totalPages;
      this.noResults = this.results.length === 0;
    });
  }

  previousPage(): void {
    if (this.page > 0) {
      this.search(this.page - 1);
    }
  }

  nextPage(): void {
    if (this.page + 1 < this.totalPages) {
      this.search(this.page + 1);
    }
  }

}
