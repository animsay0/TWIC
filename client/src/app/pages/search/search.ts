import {Component, OnInit} from '@angular/core';
import {Api} from '../../services/api';
import {App} from '../../app';


@Component({
  selector: 'app-search',
  standalone: false,
  templateUrl: './search.html',
  styleUrl: './search.css'
})
export class Search implements OnInit{

  query = '';
  results: any[] = [];
  advice: string = '';
  noResults = false;

  page = 0;
  size = 10;
  totalPages = 0;

  constructor(private api: Api, private  app : App) {}

  ngOnInit(): void {
    this.api.getAdvice().subscribe(data => {
      this.advice = data?.slip?.advice || data;
      this.app.showAdvice(`${this.advice}`);
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
