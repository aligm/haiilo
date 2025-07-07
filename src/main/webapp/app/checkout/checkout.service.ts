import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import {ProductDTO} from "../product/product.model";


@Injectable({
  providedIn: 'root',
})
export class CheckoutService {

  http = inject(HttpClient);
  resourcePath = environment.apiPath + '/api/checkouts';
  totalPricePath = this.resourcePath + '/prices/total';

  calculateTotalPrice(products: Array<ProductDTO>) {
    return this.http.post<number>(this.totalPricePath, products)
  }

}
