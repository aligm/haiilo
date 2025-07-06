import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';
import { ErrorHandler } from 'app/common/error-handler.injectable';
import { OfferService } from 'app/offer/offer.service';
import { OfferDTO } from 'app/offer/offer.model';


@Component({
  selector: 'app-offer-list',
  imports: [CommonModule, RouterLink],
  templateUrl: './offer-list.component.html'})
export class OfferListComponent implements OnInit, OnDestroy {

  offerService = inject(OfferService);
  errorHandler = inject(ErrorHandler);
  router = inject(Router);
  offers?: OfferDTO[];
  navigationSubscription?: Subscription;

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      confirm: $localize`:@@delete.confirm:Do you really want to delete this element? This cannot be undone.`,
      deleted: $localize`:@@offer.delete.success:Offer was removed successfully.`    };
    return messages[key];
  }

  ngOnInit() {
    this.loadData();
    this.navigationSubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.loadData();
      }
    });
  }

  ngOnDestroy() {
    this.navigationSubscription!.unsubscribe();
  }

  loadData() {
    this.offerService.getAllOffers()
        .subscribe({
          next: (data) => this.offers = data,
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
  }

  confirmDelete(id: number) {
    if (!confirm(this.getMessage('confirm'))) {
      return;
    }
    this.offerService.deleteOffer(id)
        .subscribe({
          next: () => this.router.navigate(['/offers'], {
            state: {
              msgInfo: this.getMessage('deleted')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
  }

}
