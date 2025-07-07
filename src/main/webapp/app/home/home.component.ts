import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { environment } from 'environments/environment';
import { RouterLink } from '@angular/router';
import {OfferListComponent} from "../checkout/checkout.component";


@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink, OfferListComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  environment = environment;

}
