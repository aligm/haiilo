import {Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {ProductListComponent} from './product/product-list.component';
import {ProductAddComponent} from './product/product-add.component';
import {ProductEditComponent} from './product/product-edit.component';
import {ErrorComponent} from './error/error.component';
import {OfferListComponent} from "./offer/offer-list.component";
import {OfferAddComponent} from "./offer/offer-add.component";
import {OfferEditComponent} from "./offer/offer-edit.component";


export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: $localize`:@@home.index.headline:Welcome to your new app!`
  },
  {
    path: 'products',
    component: ProductListComponent,
    title: $localize`:@@product.list.headline:Products`
  },
  {
    path: 'products/add',
    component: ProductAddComponent,
    title: $localize`:@@product.add.headline:Add Product`
  },
  {
    path: 'products/edit/:sku',
    component: ProductEditComponent,
    title: $localize`:@@product.edit.headline:Edit Product`
  },
  {
    path: 'offers',
    component: OfferListComponent,
    title: $localize`:@@offer.list.headline:Offers`
  },
  {
    path: 'offers/add',
    component: OfferAddComponent,
    title: $localize`:@@offer.add.headline:Add Offer`
  },
  {
    path: 'offers/edit/:id',
    component: OfferEditComponent,
    title: $localize`:@@offer.edit.headline:Edit Offer`
  },
  {
    path: 'error',
    component: ErrorComponent,
    title: $localize`:@@error.page.headline:Error`
  },
  {
    path: '**',
    component: ErrorComponent,
    title: $localize`:@@notFound.headline:Page not found`
  }
];
