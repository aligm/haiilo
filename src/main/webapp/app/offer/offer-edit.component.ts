import {Component, inject, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {InputRowComponent} from 'app/common/input-row/input-row.component';
import {OfferService} from 'app/offer/offer.service';
import {OfferDTO} from 'app/offer/offer.model';
import {ErrorHandler} from 'app/common/error-handler.injectable';
import {updateForm} from 'app/common/utils';


@Component({
  selector: 'app-offer-edit',
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './offer-edit.component.html'
})
export class OfferEditComponent implements OnInit {

  offerService = inject(OfferService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  currentId?: number;

  editForm = new FormGroup({
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
      updated: $localize`:@@offer.update.success:Offer was updated successfully.`
    };
    return messages[key];
  }

  ngOnInit() {
    this.currentId = this.route.snapshot.params['id'];
    this.offerService.getOffer(this.currentId!)
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
    const data = new OfferDTO(this.editForm.value);
    this.offerService.updateOffer(this.currentId!, data)
        .subscribe({
          next: () => this.router.navigate(['/offers'], {
            state: {
              msgSuccess: this.getMessage('updated')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.editForm, this.getMessage)
        });
  }

}
