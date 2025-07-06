import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import { ProductDTO } from 'app/product/product.model';


@Injectable({
  providedIn: 'root',
})
export class ProductService {

  http = inject(HttpClient);
  resourcePath = environment.apiPath + '/api/products';

  getAllProducts() {
    return this.http.get<ProductDTO[]>(this.resourcePath);
  }

  getProduct(sku: string) {
    return this.http.get<ProductDTO>(this.resourcePath + '/' + sku);
  }

  createProduct(productDTO: ProductDTO) {
    return this.http.post<string>(this.resourcePath, productDTO);
  }

  updateProduct(sku: string, productDTO: ProductDTO) {
    return this.http.put<string>(this.resourcePath + '/' + sku, productDTO);
  }

  deleteProduct(sku: string) {
    return this.http.delete(this.resourcePath + '/' + sku);
  }

}
