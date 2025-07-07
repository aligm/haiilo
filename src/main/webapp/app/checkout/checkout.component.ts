import {Component, inject, OnDestroy, OnInit} from "@angular/core";
import {CheckoutService} from "./checkout.service";
import {ErrorHandler} from 'app/common/error-handler.injectable';
import {ProductDTO} from "../product/product.model";
import {Subscription} from "rxjs";
import {NavigationEnd, Router, RouterLink} from "@angular/router";
import {ProductService} from "../product/product.service";
import {CommonModule} from "@angular/common";


@Component({
  selector: 'app-checkout',
  imports: [CommonModule, RouterLink],
  templateUrl: './checkout.component.html'
})
export class OfferListComponent implements OnInit, OnDestroy {

  checkoutService = inject(CheckoutService);
  productService = inject(ProductService);
  errorHandler = inject(ErrorHandler);
  router = inject(Router);
  private _products = new Array<ProductDTO>();
  totalPrice: number = 0;
  store?: Array<ProductDTO> = [];
  navigationSubscription?: Subscription;

  set products(products: Array<ProductDTO>) {
    this._products = products;
    this.calculateTotalPrice();
  }

  get products(): Array<ProductDTO> {
    return this._products;
  }

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      confirm: $localize`:@@delete.confirm:Do you really want to delete this element? This cannot be undone.`,
      deleted: $localize`:@@offer.delete.success:Offer was removed successfully.`
    };
    return messages[key];
  }

  ngOnInit() {
    this.clear();
    this.loadStore();
    this.navigationSubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.clear();
        this.loadStore();
      }
    });
  }

  ngOnDestroy() {
    this.navigationSubscription!.unsubscribe();
    this.clear();
  }

  loadStore() {
    this.productService.getAllProducts()
      .subscribe({
        next: (data) => this.store = data,
        error: (error) => this.errorHandler.handleServerError(error.error)
      });
  }

  scan(sku: string) {
    this.productService.getProduct(sku).subscribe({
      next: (product) => {
        if (product) {
          this.products = [...this.products, product];
        }
      },
      error: (error) => this.errorHandler.handleServerError(error.error)
    })
  }

  clear() {
    this.products = [];
  }

  confirmDelete(index: number) {
    if (!confirm(this.getMessage('confirm'))) {
      return;
    }
    this.products = this.products.filter((_, ix) => ix !== index);
  }

  calculateTotalPrice() {
    if (this.products.length === 0) {
      this.totalPrice = 0;
      return;
    }
    this.checkoutService.calculateTotalPrice(this.products).subscribe({
      next: (totalPrice: number) => {
        this.totalPrice = totalPrice ?? 0;
      },
      error: (error) => this.errorHandler.handleServerError(error.error)
    });
  }

}
