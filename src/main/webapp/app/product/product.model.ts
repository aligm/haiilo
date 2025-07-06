export class ProductDTO {

  constructor(data:Partial<ProductDTO>) {
    Object.assign(this, data);
  }

  sku?: string|null;
  name?: string|null;
  price?: number|null;

}
