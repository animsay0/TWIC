import { Component } from '@angular/core';
import {Api} from '../../services/api';

class ApiService {
}

@Component({
  selector: 'app-footer',
  standalone: false,
  templateUrl: './footer.html',
  styleUrl: './footer.css'
})
export class Footer {
  version = '';

  constructor(private api: Api) {}

  ngOnInit(): void {
    this.api.getVersion().subscribe(data => this.version = data);
  }

}
