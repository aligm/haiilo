import {Component, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {InputRowComponent} from 'app/common/input-row/input-row.component';
import {OfferService} from 'app/offer/offer.service';
import {OfferDTO} from 'app/offer/offer.model';
import {ErrorHandler} from 'app/common/error-handler.injectable';


@Component({
  selector: 'app-offer-add',
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './offer-add.component.html'
})
export class OfferAddComponent {

  productService = inject(OfferService);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  addForm = new FormGroup({
    id: new FormControl(null),
    sku: new FormControl(null, [Validators.required, Validators.maxLength(255)]),
    name: new FormControl(null, [Validators.required, Validators.maxLength(255)]),
    type: new FormControl(null, [Validators.required]),
    discountPercentage: new FormControl(null),
    xForYX: new FormControl(null),
    xForYY: new FormControl(null),
    fixedPrice: new FormControl(null),
    description: new FormControl(null)
  }, { updateOn: 'submit' });

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      created: $localize`:@@offer.create.success:Offer was created successfully.`
    };
    return messages[key];
  }

  handleSubmit() {
    window.scrollTo(0, 0);
    this.addForm.markAllAsTouched();
    if (!this.addForm.valid) {
      return;
    }
    const data = new OfferDTO(this.addForm.value);
    this.productService.createOffer(data)
        .subscribe({
          next: () => this.router.navigate(['/offers'], {
            state: {
              msgSuccess: this.getMessage('created')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.addForm, this.getMessage)
        });
  }

}
