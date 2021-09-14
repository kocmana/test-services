package at.kocmana.testservices.ecommerceservice.purchase.model.dto;

import at.kocmana.testservices.ecommerceservice.purchase.model.domain.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PurchaseResponse {

  private Integer id;
  private Integer customerId;
  private List<PurchaseItemResponse> items;
  private PaymentType paymentType;
}
