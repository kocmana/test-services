package at.kocmana.testservices.ecommerceservice.purchase.model.dto;

import at.kocmana.testservices.ecommerceservice.purchase.model.domain.PaymentType;
import lombok.Value;

import java.util.List;

@Value
public class PurchaseCreationRequest {

  Integer customerId;
  List<PurchaseItemCreationRequest> items;
  PaymentType paymentType;
}
