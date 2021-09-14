package at.kocmana.testservices.ecommerceservice.purchase.model.dto;

import lombok.Value;

@Value
public class PurchaseItemCreationRequest {

  Integer purchaseId;
  Integer productId;
  Integer amount;
  Float pricePerUnit;
  String currency;
}

