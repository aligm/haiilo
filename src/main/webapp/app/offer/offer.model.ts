export class OfferDTO {

  constructor(data:Partial<OfferDTO>) {
    Object.assign(this, data);
  }

  id?: number|null;
  sku?: string|null;
  name?: string|null;
  type?: string|null;
  discountPercentage?: number|null;
  xForYX?: number|null;
  xForYY?: number|null;
  fixedPrice?: number|null;
  description?: string|null;

}
