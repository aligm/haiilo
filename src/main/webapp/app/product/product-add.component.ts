import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputRowComponent } from 'app/common/input-row/input-row.component';
import { ProductService } from 'app/product/product.service';
import { ProductDTO } from 'app/product/product.model';
import { ErrorHandler } from 'app/common/error-handler.injectable';


@Component({
  selector: 'app-product-add',
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './product-add.component.html'
})
export class ProductAddComponent {

  productService = inject(ProductService);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  addForm = new FormGroup({
    sku: new FormControl(null, [Validators.required, Validators.maxLength(255)]),
    name: new FormControl(null, [Validators.required, Validators.maxLength(255)]),
    price: new FormControl(null, [Validators.required, Validators.min(0)])
  }, { updateOn: 'submit' });

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      created: $localize`:@@product.create.success:Product was created successfully.`,
      PRODUCT_SKU_UNIQUE: $localize`:@@Exists.product.sku:This Sku is already taken.`
    };
    return messages[key];
  }

  handleSubmit() {
    window.scrollTo(0, 0);
    this.addForm.markAllAsTouched();
    if (!this.addForm.valid) {
      return;
    }
    const data = new ProductDTO(this.addForm.value);
    this.productService.createProduct(data)
        .subscribe({
          next: () => this.router.navigate(['/products'], {
            state: {
              msgSuccess: this.getMessage('created')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.addForm, this.getMessage)
        });
  }

}
