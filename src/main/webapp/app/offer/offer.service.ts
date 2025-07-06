import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import { OfferDTO } from 'app/offer/offer.model';


@Injectable({
  providedIn: 'root',
})
export class OfferService {

  http = inject(HttpClient);
  resourcePath = environment.apiPath + '/api/offers';

  getAllOffers() {
    return this.http.get<OfferDTO[]>(this.resourcePath);
  }

  getOffer(id: number) {
    return this.http.get<OfferDTO>(this.resourcePath + '/' + id);
  }

  createOffer(offerDTO: OfferDTO) {
    return this.http.post<string>(this.resourcePath, offerDTO);
  }

  updateOffer(id: number, offerDTO: OfferDTO) {
    return this.http.put<string>(this.resourcePath + '/' + id, offerDTO);
  }

  deleteOffer(id: number) {
    return this.http.delete(this.resourcePath + '/' + id);
  }

}
