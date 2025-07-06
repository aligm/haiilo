import {Component, inject, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {InputRowComponent} from 'app/common/input-row/input-row.component';
import {ProductService} from 'app/product/product.service';
import {ProductDTO} from 'app/product/product.model';
import {ErrorHandler} from 'app/common/error-handler.injectable';
import {updateForm} from 'app/common/utils';


@Component({
  selector: 'app-product-edit',
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './product-edit.component.html'
})
export class ProductEditComponent implements OnInit {

  productService = inject(ProductService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  currentSku?: string;

  editForm = new FormGroup({
    sku: new FormControl(null, [Validators.required, Validators.maxLength(255)]),
    name: new FormControl(null, [Validators.required, Validators.maxLength(255)]),
    price: new FormControl(null, [Validators.required, Validators.min(0)])
  }, { updateOn: 'submit' });

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      updated: $localize`:@@product.update.success:Product was updated successfully.`,
      PRODUCT_SKU_UNIQUE: $localize`:@@Exists.product.sku:This Sku is already taken.`
    };
    return messages[key];
  }

  ngOnInit() {
    this.currentSku = this.route.snapshot.params['sku'];
    this.productService.getProduct(this.currentSku!)
        .subscribe({
          next: (data) => updateForm(this.editForm, data),
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
  }

  handleSubmit() {
    window.scrollTo(0, 0);
    this.editForm.markAllAsTouched();
    if (!this.editForm.valid) {
      return;
    }
    const data = new ProductDTO(this.editForm.value);
    this.productService.updateProduct(this.currentSku!, data)
        .subscribe({
          next: () => this.router.navigate(['/products'], {
            state: {
              msgSuccess: this.getMessage('updated')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.editForm, this.getMessage)
        });
  }

}
